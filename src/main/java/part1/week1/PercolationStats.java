package part1.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] openSitesFraction;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        final double numberOfSite = n * n;
        int x, y;
        openSitesFraction = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                x = StdRandom.uniform(n) + 1;
                y = StdRandom.uniform(n) + 1;
                p.open(x, y);
            }
            openSitesFraction[i] = p.numberOfOpenSites() / numberOfSite;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSitesFraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openSitesFraction);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdRandom.uniform();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdRandom.uniform();
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        if (args.length != 2)
            return;
        final int n = Integer.parseInt(args[0]);
        final int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, T);
        String format = "%1$-23s = %2$s%n";
        StdOut.printf(format, "mean", stats.mean());
        StdOut.printf(format, "stddev", stats.stddev());
        StdOut.printf(format, "95% confidence interval", "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        final double elapsedTime = stopwatch.elapsedTime();
        System.out.println("elapsedTime: " + elapsedTime);
    }
}
