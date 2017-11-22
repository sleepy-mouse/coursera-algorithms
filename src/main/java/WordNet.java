import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Chris Qiu
 */
public class WordNet {
    private final Map<String, Set<Integer>> words = new HashMap<>();
    private final Map<Integer, Synset> synsets = new HashMap<>();
    private final Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsetsFileName, String hypernymsFileName) {
        final String s = Util.checkEmptyString(synsetsFileName);
        final String h = Util.checkEmptyString(hypernymsFileName);
        readSynsetFile(s, words, synsets);
        graph = new Digraph(words.size());
        readHypernymFile(h, graph);
    }

    private void readSynsetFile(String synsetsFileName, Map<String, Set<Integer>> wordsMap, Map<Integer, Synset> synsetMap) {
        In in = new In(WordNet.class.getResource(Util.getResourceString(synsetsFileName)));
        while (in.hasNextLine()) {
            final String line = Util.nullToEmpty(in.readLine());
            if (Util.isNotEmptyString(line)) {
                Synset s = new Synset(line.split(","));
                for (String w : s.getWords()) {
                    Set<Integer> synsetIds = wordsMap.computeIfAbsent(w, k -> new HashSet<>(1));
                    synsetIds.add(s.getId());
                }
                synsetMap.putIfAbsent(s.getId(), s);
            }
        }
    }

    private void readHypernymFile(String hypernymFileName, Digraph digraph) {
        In in = new In(WordNet.class.getResource(Util.getResourceString(hypernymFileName)));
        while (in.hasNextLine()) {
            final String line = Util.nullToEmpty(in.readLine());
            if (Util.isNotEmptyString(line)) {
                final String[] tokens = line.trim().split(",");
                final int sid = Integer.parseInt(tokens[0]);
                for (int i = 1; i < tokens.length; i++) {
                    String token = tokens[i];
                    digraph.addEdge(sid, Integer.parseInt(token));
                }
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
        final Set<Integer> source = words.get(nounA);
        final Set<Integer> target = words.get(nounB);
        SAP sap = new SAP(graph);
        return sap.length(source, target);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Util.checkNoun(this, nounA, nounB);
        final Set<Integer> source = words.get(nounA);
        final Set<Integer> target = words.get(nounB);
        SAP sap = new SAP(graph);
        final int ancestor = sap.ancestor(source, target);
        return synsets.get(ancestor).getWordString();
    }
}
