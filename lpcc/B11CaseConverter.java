import java.util.Scanner;

public class B11CaseConverter {

    public static void main(String[] args) {
        // Create scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user to input a string
        System.out.println("Enter a string to convert case (e.g., 'Pune' or 'pUNE'):");
        String input = scanner.nextLine();
        
        // Call the function to convert case
        String result = convertCase(input);
        
        // Print the result
        System.out.println("Converted string: " + result);

        // Close the scanner
        scanner.close();
    }

    // Function to convert case of the input string
    private static String convertCase(String input) {
        // Initialize a StringBuilder to store the converted string
        StringBuilder convertedString = new StringBuilder();
        
        // Iterate over each character in the input string
        for (char ch : input.toCharArray()) {
            // If the character is lowercase, convert to uppercase
            if (Character.isLowerCase(ch)) {
                convertedString.append(Character.toUpperCase(ch));
            } 
            // If the character is uppercase, convert to lowercase
            else if (Character.isUpperCase(ch)) {
                convertedString.append(Character.toLowerCase(ch));
            } 
            // If it's neither (like digits or special characters), leave it unchanged
            else {
                convertedString.append(ch);
            }
        }

        // Return the converted string
        return convertedString.toString();
    }
}
