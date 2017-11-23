package part2.week1;

/**
 * @author Chris Qiu
 */
public class Outcast {
    private final WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = Util.checkNotNull(wordnet);
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(final String[] nouns) {
        Util.checkNoun(wordnet, nouns);
        int maxIndex = 0, maxSum = 0;
        for (int i = 0; i < nouns.length; i++) {
            int sum = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i == j)
                    continue;
                sum += wordnet.distance(nouns[i], nouns[j]);
            }
            if (maxSum < sum) {
                maxSum = sum;
                maxIndex = i;
            }
        }
        return nouns[maxIndex];
    }
}
