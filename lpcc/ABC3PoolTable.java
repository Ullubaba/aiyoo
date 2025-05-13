import java.io.*;
import java.util.*;

public class ABC3PoolTable {

    static class LiteralEntry {
        String literal;
        int address = -1;

        LiteralEntry(String literal) {
            this.literal = literal;
        }
    }

    public static void main(String[] args) {
        String filePath = "B3.txt"; // Change path as needed
        List<LiteralEntry> literalTable = new ArrayList<>();
        List<Integer> poolTable = new ArrayList<>();
        Set<String> definedLiterals = new HashSet<>();

        int locationCounter = 100; // From START directive
        int currentPoolStart = 0;  // Index of the start of current literal pool

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean startFound = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split("[\\s,]+");

                // START directive
                if (tokens[0].equalsIgnoreCase("START")) {
                    locationCounter = Integer.parseInt(tokens[1]);
                    startFound = true;
                    continue;
                }

                if (!startFound) continue;

                // Handle literals
                for (String token : tokens) {
                    if (token.startsWith("='") && token.endsWith("'")) {
                        if (!definedLiterals.contains(token)) {
                            literalTable.add(new LiteralEntry(token));
                            definedLiterals.add(token);
                        }
                    }
                }

                // LTORG or END: Process current literal pool
                if (tokens[0].equalsIgnoreCase("LTORG") || tokens[0].equalsIgnoreCase("END")) {
                    poolTable.add(currentPoolStart);

                    for (int i = currentPoolStart; i < literalTable.size(); i++) {
                        if (literalTable.get(i).address == -1) {
                            literalTable.get(i).address = locationCounter++;
                        }
                    }

                    currentPoolStart = literalTable.size();
                } else {
                    locationCounter++; // Normal instruction
                }
            }

            // Safety check: add END pool if END was not explicitly processed
            if (currentPoolStart < literalTable.size()) {
                poolTable.add(currentPoolStart);
                for (int i = currentPoolStart; i < literalTable.size(); i++) {
                    if (literalTable.get(i).address == -1) {
                        literalTable.get(i).address = locationCounter++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Display Literal Table
        System.out.println("Literal Table:");
        System.out.println("--------------------");
        System.out.printf("%-10s | %-7s\n", "Literal", "Address");
        System.out.println("--------------------");
        for (LiteralEntry entry : literalTable) {
            System.out.printf("%-10s | %-7d\n", entry.literal, entry.address);
        }

        // Display Pool Table
        System.out.println("\nPool Table:");
        System.out.println("----------------------------");
        System.out.printf("%-10s | %-15s\n", "Pool Index", "Literal Address");
        System.out.println("----------------------------");
        for (int i = 0; i < poolTable.size(); i++) {
            int index = poolTable.get(i);
            if (index < literalTable.size()) {
                System.out.printf("%-10d | %-15d\n", i + 1, literalTable.get(index).address);
            }
        }
    }
}
