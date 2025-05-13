import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A11VariableNameEvaluator {

    // Regular expression for a valid variable name:
    // A valid variable name starts with a letter or an underscore and can be followed by letters, digits, or underscores.
    private static final String VALID_VARIABLE_NAME_REGEX = "^[a-zA-Z_][a-zA-Z0-9_]*$";

    public static void main(String[] args) {
        // Create scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user to input a variable name
        System.out.println("Enter a variable name to evaluate:");
        String input = scanner.nextLine();
        
        // Call the function to check if the input is a valid variable name
        if (isValidVariableName(input)) {
            System.out.println(input + " is a valid variable name.");
        } else {
            System.out.println(input + " is an invalid variable name.");
        }

        // Close the scanner
        scanner.close();
    }

    // Function to check if the given string is a valid variable name
    private static boolean isValidVariableName(String input) {
        // Compile the regex and check for a match
        Pattern pattern = Pattern.compile(VALID_VARIABLE_NAME_REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}

