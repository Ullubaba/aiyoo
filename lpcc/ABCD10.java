import java.util.*;

public class ABCD10 {
    private String input;
    private int pos = -1;
    private int ch;

    public ABCD10(String input) {
        this.input = input;
        nextChar();
    }

    private void nextChar() {
        ch = (++pos < input.length()) ? input.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public double parse() {
        double result = parseExpression();
        if (pos < input.length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return result;
    }

    // Grammar:
    // expression = term | expression + term | expression - term
    // term = factor | term * factor | term / factor
    // factor = + factor | - factor | number | ( expression )

    private double parseExpression() {
        double x = parseTerm();
        while (true) {
            if (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        while (true) {
            if (eat('*')) x *= parseFactor(); // multiplication
            else if (eat('/')) x /= parseFactor(); // division
            else return x;
        }
    }

    private double parseFactor() {
        if (eat('+')) return parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) {
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException("Missing ')'");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(input.substring(startPos, this.pos));
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        return x;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression:");
        String expression = scanner.nextLine();

        try {
            ABCD10 evaluator = new ABCD10(expression);
            double result = evaluator.parse();
            System.out.println("= " + result);
        } catch (Exception e) {
            System.err.println("Error evaluating expression: " + e.getMessage());
        }
    }
}