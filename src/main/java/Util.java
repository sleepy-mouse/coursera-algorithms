import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author Chris Qiu
 */
class Util {
    static URL getFilePath(String fileName) {
        return Util.class.getResource(String.format("/wordnet/%s", fileName));
    }

    static Path toPath(String fileName) {
        try {
            final Path path = Paths.get(getFilePath(fileName).toURI());
            if (!path.toFile().exists())
                throw new IllegalArgumentException(fileName);
            return path;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static void validate(WordNet wordNet, String... nouns) {
        final Predicate<String> notInWordnet = wordNet::isNoun;
        if (Arrays.stream(nouns).anyMatch(notInWordnet))
            throw new IllegalArgumentException();
    }

    static int find(Digraph graph, int root, int aid, int bid) {
        DepthFirstDirectedPaths dfs1 = new DepthFirstDirectedPaths(graph, aid);
        DepthFirstDirectedPaths dfs2 = new DepthFirstDirectedPaths(graph, bid);
        List<Integer> vl1 = new ArrayList<>();
        Optional.ofNullable(dfs1.pathTo(root)).ifPresent(e -> e.forEach(vl1::add));
        List<Integer> vl2 = new ArrayList<>();
        Optional.ofNullable(dfs2.pathTo(root)).ifPresent(e -> e.forEach(vl2::add));
        final ListIterator<Integer> it1 = vl1.listIterator(vl1.size());
        final ListIterator<Integer> it2 = vl2.listIterator(vl2.size());
        int closestCommonAncestor = -1;
        while (it1.hasPrevious() && it2.hasPrevious()) {
            final Integer previous1 = it1.previous();
            final Integer previous2 = it2.previous();
            if (!previous1.equals(previous2)) {
                break;
            }
            closestCommonAncestor = previous1;
        }
        return closestCommonAncestor;
    }

    static int root(Digraph graph) {
        if (graph.V() <= 0)
            throw new IllegalArgumentException(Objects.toString(graph));
        final int start = 0;
        final Iterable<Integer> iterable = graph.adj(start);
        final Iterator<Integer> it = iterable.iterator();
        if (it.hasNext()) {
            return root(graph);
        }
        return start;
    }
}
