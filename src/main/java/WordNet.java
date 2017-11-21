import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author Chris Qiu
 */
public class WordNet {

    private final Map<String, Synset> words = new HashMap<>(50);
    private final Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        final Path synsetsPath = Util.toPath(synsets);
        final Path hypernymsPath = Util.toPath(hypernyms);
        readFile(synsetsPath, tokens -> {
            Synset synset = new Synset(tokens);
            synset.set.forEach(e -> words.put(e, synset));
        });
        graph = new Digraph(words.size());
        readFile(hypernymsPath, tokens -> {
            final Integer sid = Integer.valueOf(tokens[0]);
            Arrays.stream(tokens).skip(1).forEach(e -> graph.addEdge(sid, Integer.valueOf(e)));
        });
    }

    private void readFile(Path synsetsPath, Consumer<String[]> consumer) {
        readFile(synsetsPath, ",", consumer);
    }

    private void readFile(Path path, String delimiter, Consumer<String[]> consumer) {
        try (Stream<String> stream = Files.lines(path)) {
            stream.map(s -> s.split(delimiter)).forEach(consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            return Math.toIntExact(Stream.of(directedPaths.pathTo(target.id)).count());
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

class Synset {
    final int id;
    final List<String> set;
    final String gloss;

    Synset(String id, String set, String gloss) {
        this.id = Integer.valueOf(id);
        this.set = Arrays.asList(set.split("\\s+"));
        this.gloss = gloss;
    }

    Synset(String[] tokens) {
        this(tokens[0], tokens[1], tokens[2]);
    }
}
