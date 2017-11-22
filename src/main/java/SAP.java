import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

/**
 * @author Chris Qiu
 */
public class SAP {
    private final Digraph graph;
    private final BreadthFirstDirectedPaths[] cache;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = Util.checkNotNull(G);
        cache = new BreadthFirstDirectedPaths[graph.V()];
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int V, int W) {
        final int v = Util.checkVertex(graph, V);
        final int w = Util.checkVertex(graph, W);
        BreadthFirstDirectedPaths dfs1 = getBFS(v), dfs2 = getBFS(w);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (dfs1.distTo(i) != Integer.MAX_VALUE && dfs2.distTo(i) != Integer.MAX_VALUE) {
                final int sum = dfs1.distTo(i) + dfs2.distTo(i);
                if (min > sum) {
                    min = sum;
                }
            }
        }
        return min != Integer.MAX_VALUE ? min : -1;
    }

    private BreadthFirstDirectedPaths getBFS(int v) {
        BreadthFirstDirectedPaths bfs = cache[v];
        if (bfs == null) {
            bfs = new BreadthFirstDirectedPaths(graph, v);
            cache[v] = bfs;
        }
        return bfs;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int V, int W) {
        final int v = Util.checkVertex(graph, V);
        final int w = Util.checkVertex(graph, W);
        final int root = Util.root(graph);
        return Util.closestCommonAncestor(graph, root, v, w);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> V, Iterable<Integer> W) {
        final Iterable<Integer> v = Util.checkNotNull(V);
        final Iterable<Integer> w = Util.checkNotNull(W);
        int[] r = f(v, w);
        return r[0] == Integer.MAX_VALUE ? -1 : r[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> V, Iterable<Integer> W) {
        final Iterable<Integer> v = Util.checkNotNull(V);
        final Iterable<Integer> w = Util.checkNotNull(W);
        final int[] result = f(v, w);
        if (result[0] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return ancestor(result[1], result[2]);
        }
    }

    private int[] f(Iterable<Integer> v, Iterable<Integer> w) {
        int min = Integer.MAX_VALUE, minV = -1, minW = -1;
        for (int i : v) {
            for (int j : w) {
                final int length = length(i, j);
                if (min > length) {
                    min = length;
                    minV = i;
                    minW = j;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return new int[] {Integer.MAX_VALUE, -1, -1};
        } else {
            return new int[] {min, minV, minW};
        }
    }
}
