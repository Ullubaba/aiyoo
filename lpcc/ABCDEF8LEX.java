import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class ABCDEF8LEX {

    // A basic set of nouns, verbs, etc. (for demonstration)
    private static final Set<String> NOUNS = new HashSet<>();
    private static final Set<String> VERBS = new HashSet<>();
    private static final Set<String> ADJECTIVES = new HashSet<>();
    private static final Set<String> PRONOUNS = new HashSet<>();
    private static final Set<String> PREPOSITIONS = new HashSet<>();
    private static final Set<String> PUNCTUATION = new HashSet<>();

    static {
        // Predefined word sets for POS tagging
        NOUNS.add("Destiny");
        NOUNS.add("time");
        NOUNS.add("life");
        
        VERBS.add("Dread");
        VERBS.add("Run");
        VERBS.add("arrives");
        
        ADJECTIVES.add("same");
        ADJECTIVES.add("new");
        ADJECTIVES.add("old");
        
        PRONOUNS.add("it");
        PRONOUNS.add("he");
        PRONOUNS.add("she");
        
        PREPOSITIONS.add("from");
        PREPOSITIONS.add("in");
        PREPOSITIONS.add("on");
        
        PUNCTUATION.add(".");
        PUNCTUATION.add(",");
        PUNCTUATION.add("!");
        PUNCTUATION.add("?");
        PUNCTUATION.add(";");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input sentence
        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();
        
        // Split the sentence into words
        String[] words = sentence.split("\\s+");

        // Analyze each word
        for (String word : words) {
            String pos = getPartOfSpeech(word);
            System.out.printf("%-15s => %s%n", word, pos);
        }
        
        scanner.close();
    }

    // Method to determine part of speech for each word
    public static String getPartOfSpeech(String word) {
        word = word.replaceAll("[^a-zA-Z]", ""); // Remove punctuation
        
        if (NOUNS.contains(word)) {
            return "Noun";
        } else if (VERBS.contains(word)) {
            return "Verb";
        } else if (ADJECTIVES.contains(word)) {
            return "Adjective";
        } else if (PRONOUNS.contains(word)) {
            return "Pronoun";
        } else if (PREPOSITIONS.contains(word)) {
            return "Preposition";
        } else if (PUNCTUATION.contains(word)) {
            return "Punctuation";
        } else {
            return "Unknown";
        }
    }
}

