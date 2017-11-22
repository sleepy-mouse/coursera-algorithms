import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.Iterator;

/**
 * @author Chris Qiu
 */
public class SAP {
    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = Util.checkNotNull(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int V, int W) {
        final int v = Util.checkVertex(graph, V);
        final int w = Util.checkVertex(graph, W);
        BreadthFirstDirectedPaths dfs1 = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths dfs2 = new BreadthFirstDirectedPaths(graph, w);
        final int ancestor = ancestor(v, w);
        if (ancestor >= 0) {
            final Iterable<Integer> l1 = dfs1.pathTo(ancestor);
            final Iterable<Integer> l2 = dfs2.pathTo(ancestor);
            int count = 0;
            Iterator<Integer> it1 = l1.iterator();
            while (it1.hasNext()) {
                count++;
                it1.next();
            }
            Iterator<Integer> it2 = l2.iterator();
            while (it2.hasNext()) {
                count++;
                it2.next();
            }
            return count - 2;
        } else return -1;
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
            return new int[]{Integer.MAX_VALUE, -1, -1};
        } else {
            return new int[]{min, minV, minW};
        }
    }
}
