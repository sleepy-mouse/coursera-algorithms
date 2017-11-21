import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Chris Qiu
 */
class Util {
    static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new IllegalArgumentException();
        }
        return reference;
    }

    static int checkVertex(Digraph graph, int w) {
        if (w < 0 || w > graph.V() - 1)
            throw new IllegalArgumentException(String.valueOf(w));
        return w;
    }

    static void checkNoun(WordNet wordNet, String... nouns) {
        if (Arrays.stream(nouns).anyMatch(wordNet::isNoun))
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
            final int previous1 = it1.previous();
            final int previous2 = it2.previous();
            if (previous1 != previous2) {
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
        return internalRoot(graph, start);
    }

    private static int internalRoot(Digraph graph, int v) {
        final Iterable<Integer> neighbours = graph.adj(v);
        Iterator<Integer> it = neighbours.iterator();
        int current = -1;
        while (it.hasNext()) {
            current = it.next();
            it = graph.adj(current).iterator();
        }
        return current;
    }

    static String getResourceString(String fileName) {
        return String.format("/wordnet/%s", fileName);
    }

    static long count(Iterable<Integer> integers) {
        long count = 0;
        for (Iterator<Integer> it = integers.iterator(); it.hasNext();) {
            count++;
        }
        return count;
    }
}
