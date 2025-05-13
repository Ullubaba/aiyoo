import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class CDE11BuiltInFunctionEvaluator {

    public static void main(String[] args) {
        // Create scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user to input the expression to evaluate
        System.out.println("Enter a built-in function expression (e.g., 'u = sqrt(36)'):");
        String input = scanner.nextLine();
        
        // Evaluate the expression
        String result = evaluateExpression(input);
        
        // Print the result
        System.out.println("Result: " + result);
        
        // Close the scanner
        scanner.close();
    }

    // Function to evaluate the given expression
    private static String evaluateExpression(String input) {
        // Removing spaces for easier parsing
        input = input.replaceAll("\\s+", "");

        // Regex for detecting and parsing the functions
        Pattern pattern = Pattern.compile("([a-zA-Z]+)=([a-zA-Z]+\\([^)]+\\))");
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.matches()) {
            String variable = matcher.group(1);  // The variable name
            String functionExpression = matcher.group(2);  // The function part (e.g., sqrt(36))

            // Determine the function and calculate the result
            return variable + " = " + evaluateFunction(functionExpression);
        } else {
            return "Invalid input.";
        }
    }

    // Function to evaluate built-in functions
    private static String evaluateFunction(String functionExpression) {
        // Checking for different built-in functions and evaluating accordingly

        if (functionExpression.startsWith("sqrt(")) {
            // sqrt() function
            String value = functionExpression.substring(5, functionExpression.length() - 1);  // Extract the number
            double result = Math.sqrt(Double.parseDouble(value));
            return String.format("%.2f", result);
        } 
        else if (functionExpression.startsWith("strlen(")) {
            // strlen() function
            String value = functionExpression.substring(7, functionExpression.length() - 1);  // Extract the string
            int result = value.length();
            return String.valueOf(result);
        } 
        else if (functionExpression.startsWith("sin(")) {
            // sin() function
            String value = functionExpression.substring(4, functionExpression.length() - 1);  // Extract the number
            double result = Math.sin(Math.toRadians(Double.parseDouble(value)));  // Convert to radians
            return String.format("%.2f", result);
        } 
        else if (functionExpression.startsWith("cos(")) {
            // cos() function
            String value = functionExpression.substring(4, functionExpression.length() - 1);  // Extract the number
            double result = Math.cos(Math.toRadians(Double.parseDouble(value)));  // Convert to radians
            return String.format("%.2f", result);
        }
        else if (functionExpression.startsWith("pow(")) {
            // pow() function
            String values = functionExpression.substring(4, functionExpression.length() - 1);  // Extract the values
            String[] parts = values.split(",");
            double base = Double.parseDouble(parts[0]);
            double exponent = Double.parseDouble(parts[1]);
            double result = Math.pow(base, exponent);
            return String.format("%.2f", result);
        }
        else if (functionExpression.startsWith("log(")) {
            // log() function
            String value = functionExpression.substring(4, functionExpression.length() - 1);  // Extract the value
            double result = Math.log(Double.parseDouble(value));
            return String.format("%.2f", result);
        }
        
        return "Function not supported.";
    }
}
