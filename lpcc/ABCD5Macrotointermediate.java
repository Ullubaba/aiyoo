import java.io.*;
import java.util.*;

public class ABCD5Macrotointermediate {

    static class Macro {
        String name;
        List<String> params;
        List<String> body;

        Macro(String name, List<String> params) {
            this.name = name;
            this.params = params;
            this.body = new ArrayList<>();
        }
    }

    static Map<String, Macro> MNT = new HashMap<>();
    static List<String> intermediateCode = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // ====== 🔁 Replace with your file path here ======
        File file = new File("A5.txt"); // <-- Add your .txt file path here
        // =================================================

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            lines.add(line.trim());
        }

        passOne(lines);
        passTwo(lines);

        // Output intermediate code
        System.out.println("\nIntermediate Code:");
        for (String code : intermediateCode) {
            System.out.println(code);
        }
    }

    // Pass 1: Process and store macros
    static void passOne(List<String> lines) {
        boolean inMacro = false;
        Macro currentMacro = null;

        for (String line : lines) {
            if (line.startsWith("MACRO")) {
                inMacro = true;
                String[] header = line.split("\\s+");
                String macroLine = lines.get(lines.indexOf(line) + 1).trim();
                String[] parts = macroLine.split("\\s+|,");
                String name = parts[0];
                List<String> params = new ArrayList<>();

                for (int i = 1; i < parts.length; i++) {
                    params.add(parts[i]);
                }

                currentMacro = new Macro(name, params);
                MNT.put(name, currentMacro);
                continue;
            }

            if (inMacro) {
                if (line.equals("MEND")) {
                    inMacro = false;
                    continue;
                }
                currentMacro.body.add(line);
            }
        }

        System.out.println("Macro Name Table (MNT):");
        for (String macroName : MNT.keySet()) {
            System.out.println("  " + macroName + " -> " + MNT.get(macroName).params);
        }

        System.out.println("\nMacro Definition Table (MDT):");
        for (Macro macro : MNT.values()) {
            System.out.println("Macro: " + macro.name);
            for (String bodyLine : macro.body) {
                System.out.println("  " + bodyLine);
            }
        }
    }

    // Pass 2: Expand macros and generate intermediate code
    static void passTwo(List<String> lines) {
        boolean skipMacro = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.startsWith("MACRO")) {
                skipMacro = true;
                continue;
            }
            if (line.equals("MEND")) {
                skipMacro = false;
                continue;
            }

            if (skipMacro) {
                continue;
            }

            String[] tokens = line.split("\\s+|,");
            String opcode = tokens[0];

            if (MNT.containsKey(opcode)) {
                Macro macro = MNT.get(opcode);
                Map<String, String> paramMap = new HashMap<>();

                for (int j = 0; j < macro.params.size(); j++) {
                    if (j + 1 < tokens.length) {
                        paramMap.put(macro.params.get(j), tokens[j + 1]);
                    }
                }

                for (String bodyLine : macro.body) {
                    String expanded = bodyLine;
                    for (String param : macro.params) {
                        if (paramMap.containsKey(param)) {
                            expanded = expanded.replace(param, paramMap.get(param));
                        }
                    }
                    intermediateCode.add(expanded);
                }
            } else {
                intermediateCode.add(line);
            }
        }
    }
}
