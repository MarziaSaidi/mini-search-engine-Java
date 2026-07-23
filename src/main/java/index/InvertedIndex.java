package index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Document;
import tokenizer.Tokenizer;

public class InvertedIndex {

    private final Map<String, List<Integer>> index = new HashMap<>();
    private final Tokenizer tokenizer;

    public InvertedIndex(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void addDocument(Document doc) {
        List<String> tokens = tokenizer.tokenize(doc.getContent());
        for (String token : tokens) {
            List<Integer> postings = index.computeIfAbsent(token, key -> new ArrayList<>());
            if (!postings.contains(doc.getId())) {
                postings.add(doc.getId());
            }
        }
    }

    public List<Integer> search(String word) {
        List<String> tokens = tokenizer.tokenize(word);
        if (tokens.isEmpty()) {
            return Collections.emptyList();
        }
        String term = tokens.get(0);
        return index.getOrDefault(term, Collections.emptyList());
    }

    public static void main(String[] args) {
        InvertedIndex index = new InvertedIndex(new Tokenizer());

        index.addDocument(new Document(1, "Doc 1", "java backend"));
        index.addDocument(new Document(2, "Doc 2", "java spring"));

        System.out.println("java    -> " + index.search("java"));
        System.out.println("backend -> " + index.search("backend"));
        System.out.println("spring  -> " + index.search("spring"));
    }
}
