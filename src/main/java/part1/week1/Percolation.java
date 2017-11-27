package part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final Site[] sites;
    private final int n;
    private int numberOfOpenSites;
    private final int[] topRowIndices;
    private final int[] bottomRowIndices;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("numberOfElements must be greater than zero.");
        this.n = n;
        int numberOfElements = transform(n, n, n);
        uf = new WeightedQuickUnionUF(numberOfElements + 1);
        sites = new Site[numberOfElements + 1];
        topRowIndices = new int[n];
        for (int i = 0, j = 1; i < topRowIndices.length && j <= n; i++, j++) {
            topRowIndices[i] = j;
        }
        bottomRowIndices = new int[n];
        for (int i = 0, j = numberOfElements; i < bottomRowIndices.length && j > numberOfElements - n; i++, j--) {
            bottomRowIndices[i] = j;
        }
    }

    private static int transform(int n, int row, int col) {
        return n * (row - 1) + col;
    }

    private int checkIndex(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException("Invalid row " + row);
        if (col < 1 || col > n) throw new IllegalArgumentException("Invalid col " + col);
        return transform(n, row, col);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        final int i = checkIndex(row, col);
        Site site = openSite(row, col, i);
        checkNeighbour(site);
    }

    private void checkNeighbour(Site site) {
        for (Site s : sites) {
            if (Site.isNeighbour(site, s) && s.isOpen() && !uf.connected(site.getI(), s.getI())) {
                uf.union(site.getI(), s.getI());
            }
        }
    }

    private Site openSite(int row, int col, int i) {
        Site site = sites[i];
        if (site == null) {
            site = new Site(row, col, i);
            sites[i] = site;
        }
        if (!site.isOpen()) {
            site.setOpen(true);
            numberOfOpenSites++;
        }
        return site;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        final int i = checkIndex(row, col);
        return isOpen(i);
    }

    private boolean isOpen(int index) {
        Site site = sites[index];
        return site != null && site.isOpen();
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean isFull(int row, int col) {
        int i = checkIndex(row, col);
        return isOpen(i) && isFull(i);
    }

    private boolean isFull(int siteIndex) {
        for (int j : topRowIndices) {
            if (isOpen(j) && uf.connected(siteIndex, j)) {
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

    private static class Site {
        private final int x;
        private final int y;
        private final int i;
        private boolean open;

        Site(int x, int y, int i) {
            this.x = x;
            this.y = y;
            this.i = i;
        }

        boolean isOpen() {
            return open;
        }

        void setOpen(boolean open) {
            this.open = open;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getI() {
            return i;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Site site = (Site) obj;

            if (x != site.x) return false;
            if (y != site.y) return false;
            if (i != site.i) return false;
            return open == site.open;
        }

        @Override
        public int hashCode() {
            return i;
        }

        static boolean isNeighbour(Site s1, Site s2) {
            if (s1 == null || s2 == null)
                return false;
            if (s1.equals(s2))
                return false;
            if (s1.x == s2.x) {
                return Math.abs(s1.y - s2.y) == 1;
            } else
                return s1.y == s2.y && Math.abs(s1.x - s2.x) == 1;
        }
    }
}
