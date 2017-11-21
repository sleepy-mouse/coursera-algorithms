import java.util.Arrays;
import java.util.List;

class Synset {
    final int id;
    final List<String> words;

    Synset(String id, String words) {
        this.id = Integer.parseInt(id);
        this.words = Arrays.asList(words.split("\\s+"));
    }

    Synset(String[] tokens) {
        this(tokens[0], tokens[1]);
    }
}
