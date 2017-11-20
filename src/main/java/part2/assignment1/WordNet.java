package part2.assignment1;

import java.util.Objects;

/**
 * @author Chris Qiu
 */
public class WordNet {
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (Objects.isNull(synsets) || Objects.isNull(hypernyms))
            throw new IllegalArgumentException();
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (Objects.isNull(word))
            throw new IllegalArgumentException();
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (Objects.isNull(nounA) || Objects.isNull(nounB))
            throw new IllegalArgumentException();
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (Objects.isNull(nounA) || Objects.isNull(nounB))
            throw new IllegalArgumentException();
        return "";
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
