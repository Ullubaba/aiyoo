import java.io.*;
import java.util.*;

public class AB6MDT {

    public static void main(String[] args) {
        // Specify the path to the input assembly code file here
        String filePath = "A6.txt"; // <-- PUT YOUR FILE PATH HERE

        List<String> MDT = new ArrayList<>();
        boolean isMacroDef = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Start of a macro definition
                if (line.startsWith("MACRO")) {
                    isMacroDef = true;
                    continue; // Skip the MACRO line
                }

                // End of a macro definition
                if (line.equals("MEND")) {
                    MDT.add("MEND");
                    isMacroDef = false;
                    continue;
                }

                // If currently inside macro definition, store lines in MDT
                if (isMacroDef) {
                    MDT.add(line);
                }
            }

            // Output the MDT
            System.out.println("Macro Definition Table (MDT):");
            for (int i = 0; i < MDT.size(); i++) {
                System.out.println(i + "\t" + MDT.get(i));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
