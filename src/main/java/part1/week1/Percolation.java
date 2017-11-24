package part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int[][] grid;
    private final int n;
    private final WeightedQuickUnionUF uf;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        this.n = n;
        grid = new int[n + 1][n + 1];
        uf = new WeightedQuickUnionUF(n);
    }

    void checkIndex(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException("Invalid row " + row);
        if (col < 1 || col > n) throw new IllegalArgumentException("Invalid col " + col);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkIndex(row, col);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        // TODO
        return false;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        // TODO
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        // TODO Not Implemented
        throw new UnsupportedOperationException();
    }

    // does the system percolate?
    public boolean percolates() {
        // TODO Not Implemented
        throw new UnsupportedOperationException();
    }
}
