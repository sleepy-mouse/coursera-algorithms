import edu.princeton.cs.algs4.*;

/**
 * @author Chris Qiu
 */
public class SAP {
    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        DepthFirstDirectedPaths dfs1 = new DepthFirstDirectedPaths(graph, v);
        DepthFirstDirectedPaths dfs2 = new DepthFirstDirectedPaths(graph, w);
        final int ancestor = ancestor(v, w);
        if (ancestor > 0) {
            final Iterable<Integer> l1 = dfs1.pathTo(ancestor);
            final Iterable<Integer> l2 = dfs2.pathTo(ancestor);
            int count = 0;
            for (Integer i : l1) {
                count++;
            }
            for (Integer i : l2) {
                count++;
            }
            return count - 2;
        } else return -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        final int root = Util.root(graph);
        return Util.find(graph, root, v, w);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int min = Integer.MAX_VALUE;
        for (Integer i : v) {
            for (Integer j : w) {
                final int length = length(i, j);
                if (min > length)
                    min = length;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int min = Integer.MAX_VALUE, minV = -1, minW = -1;
        for (Integer i : v) {
            for (Integer j : w) {
                final int length = length(i, j);
                if (min > length) {
                    min = length;
                    minV = i;
                    minW = j;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return ancestor(minV, minW);
        }
    }

    // do unit testing of this class
    public static void main(String... args) {
        In in = args.length == 0 ? new In(Util.getFilePath("digraph1.txt")) : new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
