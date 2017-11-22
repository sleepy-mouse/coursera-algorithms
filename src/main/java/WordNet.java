import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris Qiu
 */
public class WordNet {
    private final Map<String, Synset> words = new HashMap<>();
    private final Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        final String s = Util.checkEmptyString(synsets);
        final String h = Util.checkEmptyString(hypernyms);
        readSynsetFile(s);
        graph = new Digraph(words.size());
        readHypernymFile(h);
    }

    private void readSynsetFile(String synsetsFileName) {
        In in = new In(WordNet.class.getResource(Util.getResourceString(synsetsFileName)));
        while (in.hasNextLine()) {
            final String line = in.readLine();
            Synset s = new Synset(line.split(","));
            for (String w : s.getWords()) {
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
        final String w = Util.checkNotNull(word);
        return words.containsKey(w);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        Util.checkNoun(this, nounA, nounB);
        final Synset source = words.get(nounA);
        final Synset target = words.get(nounB);
        BreadthFirstDirectedPaths directedPaths = new BreadthFirstDirectedPaths(graph, source.getId());
        if (directedPaths.hasPathTo(target.getId())) {
            return Math.toIntExact(Util.count(directedPaths.pathTo(target.getId())));
        }
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Util.checkNoun(this, nounA, nounB);
        final int aid = words.get(nounA).getId();
        final int bid = words.get(nounB).getId();
        final int root = Util.root(graph);
        final int id = Util.closestCommonAncestor(graph, root, aid, bid);
        return String.valueOf(id);
    }
}
