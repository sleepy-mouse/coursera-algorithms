import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
        if (nouns.length == 0 || Arrays.stream(nouns).noneMatch(wordNet::isNoun))
            throw new IllegalArgumentException();
    }

    static int closestCommonAncestor(Digraph graph, int root, int aid, int bid) {
        int closest = -1, shortest = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths dfs1 = new BreadthFirstDirectedPaths(graph, aid),
                dfs2 = new BreadthFirstDirectedPaths(graph, bid);
        final boolean b1 = dfs1.hasPathTo(bid), b2 = dfs2.hasPathTo(aid);
        if (b1) {
            int[] a = getClosest(bid, closest, shortest, dfs1);
            closest = a[0];
            shortest = a[1];
        }
        if (b2) {
            int[] a = getClosest(aid, closest, shortest, dfs2);
            closest = a[0];
            shortest = a[1];
        }
        if (root >= 0) {
            List<Integer> vl1 = new ArrayList<>(), vl2 = new ArrayList<>();
            Optional.ofNullable(dfs1.pathTo(root)).ifPresent(e -> e.forEach(vl1::add));
            Optional.ofNullable(dfs2.pathTo(root)).ifPresent(e -> e.forEach(vl2::add));

            for (int i = vl1.size() - 1, j = vl2.size() - 1; i >= 0 && j >= 0; i--, j--) {
                final int current1 = vl1.get(i), current2 = vl2.get(j);
                if (current1 != current2) {
                    int length = i + j + 2;
                    if (length < shortest) {
                        shortest = length;
                        closest = vl1.get(i + 1);
                    }
                    break;
                }
            }
        }
        return closest;
    }

    private static int[] getClosest(int id, int closest, int shortest, BreadthFirstDirectedPaths dfs) {
        final Iterator<Integer> it = dfs.pathTo(id).iterator();
        int length = -1;
        while (it.hasNext()) {
            length++;
            it.next();
        }
        if (length < shortest) {
            shortest = length;
            closest = id;
        }
        return new int[] {closest, shortest};
    }

    static int root(Digraph graph) {
        if (graph.V() <= 0)
            throw new IllegalArgumentException(Objects.toString(graph));
        for (int i = 0; i < graph.V(); i++) {
            if (!graph.adj(i).iterator().hasNext() && graph.indegree(i) > 0)
                return i;
        }
        return -1;
    }

    static String getResourceString(String fileName) {
        return String.format("/wordnet/%s", fileName);
    }

    static long count(Iterable<Integer> integers) {
        long count = 0;
        Iterator<Integer> it = integers.iterator();
        while (it.hasNext()) {
            count++;
            it.next();
        }
        return count;
    }

    static String checkEmptyString(String hypernyms) {
        if (hypernyms == null || hypernyms.trim().isEmpty())
            throw new IllegalArgumentException(hypernyms);
        return hypernyms.trim();
    }

    static boolean isNotEmptyString(String line) {
        return line != null && !line.isEmpty();
    }

    public static String nullToEmpty(String s) {
        return s == null ? "" : s.trim();
    }
}
