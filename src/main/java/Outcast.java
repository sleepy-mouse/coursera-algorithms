import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author Chris Qiu
 */
public class Outcast {
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        return "";
    }

    // see test client below
    public static void main(String... args) {
        final String synsetsTxt;
        final String hypernymsTxt;
        final String[] outcastTxts;
        if (args.length > 2) {
            synsetsTxt = args[0];
            hypernymsTxt = args[1];
            outcastTxts = Arrays.copyOfRange(args, 2, args.length);
        } else {
            synsetsTxt = "synsets.txt";
            hypernymsTxt = "hypernyms.txt";
            outcastTxts = new String[] {"outcast5.txt", "outcast8.txt"};
        }
        WordNet wordnet = new WordNet(synsetsTxt, hypernymsTxt);
        Outcast outcast = new Outcast(wordnet);
        for (String outcastTxt : outcastTxts) {
            In in = new In(outcastTxt);
            String[] nouns = in.readAllStrings();
            StdOut.println(outcastTxt + ": " + outcast.outcast(nouns));
        }
    }
}
