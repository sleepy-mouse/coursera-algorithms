import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        if (w < 0 || w >= graph.V())
            throw new IllegalArgumentException(String.valueOf(w));
        return w;
    }

    static void checkNoun(WordNet wordNet, String... nouns) {
        if (nouns.length == 0 || Arrays.stream(nouns).anyMatch(n -> !wordNet.isNoun(n)))
            throw new IllegalArgumentException();
    }

    static String checkEmptyString(String hypernyms) {
        if (hypernyms == null) {
            throw new IllegalArgumentException("null string");
        }
        final String s = hypernyms.trim();
        if (s.isEmpty())
            throw new IllegalArgumentException("empty string");
        return s;
    }

    static String nullToEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    static List<Integer> checkVertices(Digraph graph, Iterable<Integer> vertices) {
        final List<Integer> list = new ArrayList<>();
        final Iterator<Integer> it = vertices.iterator();
        while (it.hasNext()) {
            final int v = it.next();
            if (v < 0 || v >= graph.V())
                throw new IllegalArgumentException("invalid vertex: " + v);
            list.add(v);
        }
        return list;
    }
}
