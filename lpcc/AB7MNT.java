import java.io.*;
import java.util.*;

public class AB7MNT {

    public static void main(String[] args) {
        // 🔧 Set the path to your assembly code text file here
        String filePath = "A7.txt"; // <-- Change this to your actual file path

        List<String[]> MNT = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // ✅ Check if the line starts with MACRO
                if (line.startsWith("MACRO")) {
                    // Remove the keyword "MACRO" and get the rest
                    String macroLine = line.substring(5).trim(); // skip "MACRO" (5 letters)

                    if (!macroLine.isEmpty()) {
                        // Split into macro name and parameters
                        String[] parts = macroLine.split("\\s+", 2);
                        String macroName = parts[0];
                        String params = (parts.length > 1) ? parts[1].trim() : "";

                        MNT.add(new String[]{macroName, params});
                    }

                    // Skip macro body lines until MEND
                    while ((line = br.readLine()) != null && !line.trim().equalsIgnoreCase("MEND")) {
                        // Do nothing
                    }
                }
            }

            // ✅ Print the Macro Name Table (MNT)
            System.out.println("Macro Name Table (MNT):");
            System.out.println("Index\tMacro Name\tParameters");
            for (int i = 0; i < MNT.size(); i++) {
                String[] entry = MNT.get(i);
                System.out.printf("%d\t%-12s\t%s\n", i, entry[0], entry[1]);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
