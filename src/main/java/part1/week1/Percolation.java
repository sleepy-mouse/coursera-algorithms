package part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final boolean[] sites;
    private final int n;
    private int openSiteCount;
    private final int elementCount;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than zero.");
        this.n = n;
        elementCount = transform(n, n, n);
        uf = new WeightedQuickUnionUF(elementCount + 1);
        sites = new boolean[elementCount + 1];
    }

    // Get the unique incremented number standing for the site, so it can be used for Union-Find algorithm
    private static int transform(int n, int row, int col) {
        return n * (row - 1) + col;
    }

    private int checkIndex(int row, int col) {
        if (invalidIndex(row, col))
            throw new IllegalArgumentException("Invalid: " + row + ", " + col);
        return transform(n, row, col);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        final int i = checkIndex(row, col);
        openSite(i);
        mergeOpenNeighbour(row, col, i);
    }

    private void mergeOpenNeighbour(int x, int y, int i) {
        int left = findOpenNeighbour(x - 1, y);
        int right = findOpenNeighbour(x + 1, y);
        int top = findOpenNeighbour(x, y + 1);
        int bottom = findOpenNeighbour(x, y - 1);
        unionSites(left, i);
        unionSites(right, i);
        unionSites(top, i);
        unionSites(bottom, i);
    }

    private boolean invalidIndex(int row, int col) {
        return row < 1 || row > n || col < 1 || col > n;
    }

    private int findOpenNeighbour(int row, int col) {
        if (invalidIndex(row, col)) return -1;
        final int index = transform(n, row, col);
        return isOpen(index) ? index : -1;
    }

    private void unionSites(int neighbourIndex, int siteIndex) {
        if (neighbourIndex > 0) {
            uf.union(neighbourIndex, siteIndex);
        }
    }

    private void openSite(int i) {
        boolean s = sites[i];
        // Closed site
        if (!s) {
            sites[i] = true;
            openSiteCount++;
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        final int i = checkIndex(row, col);
        return isOpen(i);
    }

    private boolean isOpen(int index) {
        return sites[index];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean isFull(int row, int col) {
        int i = checkIndex(row, col);
        return isOpen(i) && isFull(i);
    }

    private boolean isFull(int siteIndex) {
        for (int i = 1; i <= n; i++) {
            boolean openSite = sites[i];
            if (openSite && uf.connected(i, siteIndex)) {
                return true;
            }
        }
        return false;
    }

    public boolean percolates() {
        for (int k = 0, i = elementCount; k < n; k++, i = elementCount - k) {
            if (!sites[i])
                continue;
            if (isFull(i)) {
                return true;
            }
        }
        return false;
    }
}
