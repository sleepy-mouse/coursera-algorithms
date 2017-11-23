import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Chris Qiu
 */
public class SAP {
    private final Digraph graph;
    private final BreadthFirstDirectedPaths[] bfsCache1;
    private final Map<Integer, BreadthFirstDirectedPaths> bfsCache2;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = Util.checkNotNull(G);
        bfsCache1 = new BreadthFirstDirectedPaths[graph.V()];
        bfsCache2 = new HashMap<>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int V, int W) {
        final int v = Util.checkVertex(graph, V);
        final int w = Util.checkVertex(graph, W);
        return getCCA(v, w).getLength();
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int V, int W) {
        final int v = Util.checkVertex(graph, V);
        final int w = Util.checkVertex(graph, W);
        return getCCA(v, w).getAncestor();
    }

    public int length(Iterable<Integer> V, Iterable<Integer> W) {
        final List<Integer> v = Util.checkVertices(graph, V);
        final List<Integer> w = Util.checkVertices(graph, W);
        return getCCA(v, w).getLength();
    }

    public int ancestor(Iterable<Integer> V, Iterable<Integer> W) {
        final List<Integer> v = Util.checkVertices(graph, V);
        final List<Integer> w = Util.checkVertices(graph, W);
        return getCCA(v, w).getAncestor();
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    private CCA getCCA(List<Integer> v, List<Integer> w) {
        final int hash1 = Objects.hash(v), hash2 = Objects.hash(w);
        BreadthFirstDirectedPaths bfs1 = getBFSFromCache2(hash1, v), bfs2 = getBFSFromCache2(hash2, v);
        return calculate(bfs1, bfs2);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    private CCA getCCA(int v, int w) {
        BreadthFirstDirectedPaths bfs1 = getBFSFromCache(v), bfs2 = getBFSFromCache(w);
        return calculate(bfs1, bfs2);
    }

    private CCA calculate(BreadthFirstDirectedPaths bfs1, BreadthFirstDirectedPaths bfs2) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (bfs1.distTo(i) != Integer.MAX_VALUE && bfs2.distTo(i) != Integer.MAX_VALUE) {
                final int sum = bfs1.distTo(i) + bfs2.distTo(i);
                if (min > sum) {
                    min = sum;
                    minIndex = i;
                }
            }
        }
        return new CCA(minIndex, min);
    }

    private BreadthFirstDirectedPaths getBFSFromCache(int v) {
        BreadthFirstDirectedPaths bfs = bfsCache1[v];
        if (bfs == null) {
            bfs = new BreadthFirstDirectedPaths(graph, v);
            bfsCache1[v] = bfs;
        }
        return bfs;
    }

    private BreadthFirstDirectedPaths getBFSFromCache2(int hash, Iterable<Integer> v) {
        return bfsCache2.computeIfAbsent(hash, k -> new BreadthFirstDirectedPaths(graph, v));
    }

    private static class CCA {
        private final int ancestor;
        private final int length;

        CCA(int ancestor, int length) {
            this.ancestor = ancestor;
            this.length = length != Integer.MAX_VALUE ? length : -1;
        }

        int getAncestor() {
            return ancestor;
        }

        int getLength() {
            return length;
        }
    }
}
