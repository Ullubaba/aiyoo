import java.io.*;
import java.util.*;

class Symbol {
    String symbol;
    int address;

    Symbol(String symbol, int address) {
        this.symbol = symbol;
        this.address = address;
    }
}

public class ABC1symbolTable {

    static List<Symbol> symbolTable = new ArrayList<>();
    static int locationCounter = 0;

    public static void main(String[] args) throws IOException {

        // 📌 Hardcoded file path
        String filePath = "A1.txt";  // You can replace this with your full path if needed

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found at: " + filePath);
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        // Pass 1: Build symbol table
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");

            // START directive
            if (tokens[0].equals("START")) {
                locationCounter = Integer.parseInt(tokens[1]);
                continue;
            }

            // Detect label and handle DS (Declare Storage)
            if (!tokens[0].equals("READ") && !tokens[0].equals("MOVER") &&
                !tokens[0].equals("COMP") && !tokens[0].equals("BC") &&
                !tokens[0].equals("SUB") && !tokens[0].equals("STOP") &&
                !tokens[0].equals("END")) {

                String label = tokens[0];
                addSymbol(label, locationCounter);

                if (tokens.length > 1 && tokens[1].equals("DS")) {
                    locationCounter += Integer.parseInt(tokens[2]);
                    continue;
                }
            }

            // Increment for each instruction
            locationCounter++;
        }

        br.close();

        // Output symbol table
        System.out.println("\nSymbol Table:");
        System.out.println("-------------------");
        System.out.printf("%-10s | %s\n", "Symbol", "Address");
        System.out.println("-------------------");
        for (Symbol s : symbolTable) {
            System.out.printf("%-10s | %d\n", s.symbol, s.address);
        }
    }

    static void addSymbol(String label, int address) {
        for (Symbol s : symbolTable) {
            if (s.symbol.equals(label)) return;
        }
        symbolTable.add(new Symbol(label, address));
    }
}
