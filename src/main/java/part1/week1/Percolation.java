package part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int numberOfElements;
    private final WeightedQuickUnionUF uf;
    private final boolean[] sites;
    private final int n;
    private int numberOfOpenSites;
    private final int[] topRowIndices;
    private final int[] bottomRowIndices;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("numberOfElements must be greater than zero.");
        this.n = n;
        this.numberOfElements = transform(n, n, n);
        uf = new WeightedQuickUnionUF(this.numberOfElements + 1);
        sites = new boolean[this.numberOfElements + 1];
        topRowIndices = new int[n];
        for (int i = 0, j = 1; i < topRowIndices.length && j <= n; i++, j++) {
            topRowIndices[i] = j;
        }
        bottomRowIndices = new int[n];
        for (int i = 0, j = numberOfElements; i < bottomRowIndices.length && j > numberOfElements - n; i++, j--) {
            bottomRowIndices[i] = j;
        }
    }

    static int transform(int n, int row, int col) {
        return n * (row - 1) + col;
    }

    private void checkIndex(int row, int col) {
        if (row < 1 || row > numberOfElements) throw new IllegalArgumentException("Invalid row " + row);
        if (col < 1 || col > numberOfElements) throw new IllegalArgumentException("Invalid col " + col);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkIndex(row, col);
        final int coord = transform(n, row, col);
        sites[coord] = true;                        // true means open
        numberOfOpenSites++;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        final int coord = transform(n, row, col);
        return isOpen(coord);
    }

    private boolean isOpen(int index) {
        return sites[index];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        final int index = transform(n, row, col);
        return isOpen(index) && isFull(index);
    }

    private boolean isFull(int siteIndex) {
        for (int j : topRowIndices) {
            if (uf.connected(siteIndex, j)) {
                return true;
            }
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i : bottomRowIndices) {
            if (isOpen(i) && isFull(i)) {
                return true;
            }
        }
        return false;
    }
}
