import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Synset {
    private final int id;
    private final List<String> words;

    Synset(String id, String words) {
        this.id = Integer.parseInt(id);
        this.words = Arrays.asList(words.split("\\s+"));
    }

    Synset(String[] tokens) {
        this(tokens[0], tokens[1]);
    }

    public int getId() {
        return id;
    }

    public List<String> getWords() {
        return Collections.unmodifiableList(words);
    }
}
