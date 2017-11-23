package part2.week1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class Synset {
    private final int id;
    private final List<String> words;
    private final String wordString;

    Synset(String id, String words) {
        this.id = Integer.parseInt(id);
        this.words = Arrays.asList(words.split("\\s+"));
        this.wordString = words;
    }

    Synset(String[] tokens) {
        this(tokens[0].trim(), tokens[1].trim());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Synset synset = (Synset) obj;
        return id == synset.id &&
                Objects.equals(words, synset.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public List<String> getWords() {
        return Collections.unmodifiableList(words);
    }

    public String getWordString() {
        return wordString;
    }
}
