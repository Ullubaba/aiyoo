import java.io.*;
import java.util.*;

public class ABC4IntermediateCode {
    static Map<String, String> opcodeTable = new HashMap<>();
    static Map<String, Integer> symbolTable = new LinkedHashMap<>();
    static int locationCounter = 0;

    public static void main(String[] args) {
        String filePath = "A4.txt"; // Make sure this file has your source code

        opcodeTable.put("START", "AD");
        opcodeTable.put("END", "AD");
        opcodeTable.put("READ", "IS");
        opcodeTable.put("MOVER", "IS");
        opcodeTable.put("SUB", "IS");
        opcodeTable.put("STOP", "IS");
        opcodeTable.put("DS", "DL");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Intermediate Code:");

            while ((line = reader.readLine()) != null) {
                line = line.trim().replaceAll("\\s+", " ");
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");

                if (opcodeTable.containsKey(parts[0])) {
                    handleInstruction(parts);
                } else if (parts.length > 1 && opcodeTable.containsKey(parts[1])) {
                    // Label with instruction
                    if (!symbolTable.containsKey(parts[0]) || symbolTable.get(parts[0]) == -1) {
                        symbolTable.put(parts[0], locationCounter);
                    }
                    handleInstruction(Arrays.copyOfRange(parts, 1, parts.length));
                } else if (parts.length == 3 && parts[1].equals("DS")) {
                    if (!symbolTable.containsKey(parts[0]) || symbolTable.get(parts[0]) == -1) {
                        symbolTable.put(parts[0], locationCounter);
                    }
                    System.out.println("(DL,01) (C," + parts[2] + ")");
                    locationCounter += Integer.parseInt(parts[2]);
                } else if (parts[0].equals("END")) {
                    System.out.println("(AD,02)");
                }
            }

            System.out.println("\nSymbol Table:");
            System.out.println("Symbol\tAddress");
            for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void handleInstruction(String[] parts) {
        String opcode = parts[0];

        switch (opcode) {
            case "START":
                locationCounter = Integer.parseInt(parts[1]);
                System.out.println("(AD,01) (C," + parts[1] + ")");
                break;

            case "READ":
                if (parts.length >= 2) {
                    System.out.println("(IS,01) " + getSymbolCode(parts[1]));
                    locationCounter++;
                }
                break;

            case "MOVER":
            case "SUB":
                if (parts.length >= 3) {
                    String reg = parts[1].replace(",", "");
                    String operand = parts[2];
                    System.out.println("(IS," + getOpcodeIndex(opcode) + ") (" + getRegisterCode(reg) + ") " + getSymbolCode(operand));
                    locationCounter++;
                }
                break;

            case "STOP":
                System.out.println("(IS,00)");
                locationCounter++;
                break;
        }
    }

    static int getOpcodeIndex(String mnemonic) {
        switch (mnemonic) {
            case "READ": return 1;
            case "MOVER": return 4;
            case "SUB": return 2;
            case "STOP": return 0;
            default: return 0;
        }
    }

    static String getRegisterCode(String register) {
        switch (register.toUpperCase()) {
            case "AREG": return "1";
            case "BREG": return "2";
            case "CREG": return "3";
            default: return "0";
        }
    }

    static String getSymbolCode(String symbol) {
        // Placeholder -1 for symbols seen before definition
        if (!symbolTable.containsKey(symbol)) {
            symbolTable.put(symbol, -1);
        }
        return "(S," + symbol + ")";
    }
}
        