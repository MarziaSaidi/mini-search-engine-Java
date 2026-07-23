package tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tokenizer {

    private static final Set<String> STOP_WORDS = Set.of(
            "the", "is", "a", "and", "of"
    );

    public List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return tokens;
        }

        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9 ]", "");
        String[] words = cleaned.split("\\s+");

        for (String word : words) {
            if (word.isEmpty() || STOP_WORDS.contains(word)) {
                continue;
            }
            tokens.add(word);
        }

        return tokens;
    }

    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        System.out.println(tokenizer.tokenize("Java is Great!"));
        System.out.println(tokenizer.tokenize("Java is used for backend development"));
    }
}
