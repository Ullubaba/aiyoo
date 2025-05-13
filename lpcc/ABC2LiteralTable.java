import java.io.*;
import java.util.*;

class Literal {
    String value;
    int address;

    Literal(String value) {
        this.value = value;
        this.address = -1;
    }
}

public class ABC2LiteralTable {

    static List<Literal> literalTable = new ArrayList<>();
    static int locationCounter = 100; // Starting location for assembly instructions

    public static void main(String[] args) throws IOException {

        // Hardcoded file path for input.txt
        String filePath = "B2.txt"; // Specify the path of your assembly code file

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found at: " + filePath);
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        // Pass 1: Detect all literals
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("[\\s,]+");  // Split on spaces or commas

            if (tokens[0].equals("START")) {
                locationCounter = Integer.parseInt(tokens[1]);
                continue;
            }

            // Process literals (e.g., = '50')
            for (String token : tokens) {
                if (token.contains("='") || token.contains("=’")) {
                    // Replace curly quotes with normal straight quotes
                    token = token.replace("’", "'").replace("‘", "'");

                    // Extract the literal value (after the =' and before the closing quote)
                    int start = token.indexOf("='") + 2;
                    int end = token.lastIndexOf("'");
                    if (start < end) {
                        String literalVal = token.substring(start, end);
                        addLiteral(literalVal);
                    }
                }
            }

            locationCounter++;
        }

        br.close();

        // Pass 2: Assign addresses to the literals
        for (Literal lit : literalTable) {
            lit.address = locationCounter++;
        }

        // Print the Literal Table
        System.out.println("\nLiteral Table:");
        System.out.println("--------------------");
        System.out.printf("%-10s | %s\n", "Literal", "Address");
        System.out.println("--------------------");
        for (Literal l : literalTable) {
            System.out.printf("%-10s | %d\n", l.value, l.address);
        }
    }

    static void addLiteral(String value) {
        // Add literal if not already present in the table
        for (Literal l : literalTable) {
            if (l.value.equals(value)) return;
        }
        literalTable.add(new Literal(value));
    }
}
