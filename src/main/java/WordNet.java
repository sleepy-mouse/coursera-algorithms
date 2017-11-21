import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Chris Qiu
 */
public class WordNet {

    private final Map<String, Synset> words = new HashMap<>();
    private final Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        readSynsetFile(synsets);
        graph = new Digraph(words.size());
        readHypernymFile(hypernyms);
    }

    private void readSynsetFile(String synsetsFileName) {
        In in = new In(WordNet.class.getResource(Util.getResourceString(synsetsFileName)));
        while (in.hasNextLine()) {
            final String line = in.readLine();
            Synset s = new Synset(line.split(","));
            for (String w : s.words) {
                words.put(w, s);
            }
        }
    }

    private void readHypernymFile(String hypernymFileName) {
        In in = new In(WordNet.class.getResource(Util.getResourceString(hypernymFileName)));
        while (in.hasNextLine()) {
            final String line = in.readLine();
            final String[] tokens = line.split(",");
            final int sid = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                String token = tokens[i];
                graph.addEdge(sid, Integer.parseInt(token));
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return words.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (Objects.isNull(word))
            throw new IllegalArgumentException();
        return words.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        Util.validate(this, nounA, nounB);
        final Synset source = words.get(nounA);
        final Synset target = words.get(nounB);
        DepthFirstDirectedPaths directedPaths = new DepthFirstDirectedPaths(graph, source.id);
        if (directedPaths.hasPathTo(target.id)) {
            return Math.toIntExact(Util.count(directedPaths.pathTo(target.id)));
        }
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Util.validate(this, nounA, nounB);
        final int aid = words.get(nounA).id;
        final int bid = words.get(nounB).id;
        final int root = Util.root(graph);
        final int id = Util.find(graph, root, aid, bid);
        return String.valueOf(id);
    }

    // do unit testing of this class
    public static void main(String... args) {
    }
}
