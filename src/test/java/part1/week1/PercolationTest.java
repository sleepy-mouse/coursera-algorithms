package part1.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;

import static part1.week1.PercolationVisualizer.draw;

@Slf4j
public class PercolationTest {
    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 100;

    @Test
    public void visualise() {
        URL filePath = Percolation.class.getResource("/part1/week1/percolation/input1.txt");
        In in = new In(filePath);
        int n = in.readInt();                   // n-by-n percolation system
        StdDraw.enableDoubleBuffering();        // turn on animation mode
        Percolation perc = new Percolation(n);  // repeatedly read in sites to open and draw resulting system
        draw(perc, n);
        StdDraw.show();
        StdDraw.pause(DELAY);
        while (!in.isEmpty()) {
            int i = in.readInt(), j = in.readInt();
            perc.open(i, j);
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
    }

    @Test
    public void percolationStats() {
        Stopwatch stopwatch = new Stopwatch();
        final int n = StdRandom.uniform(10) + 1;
        final int T = StdRandom.uniform(10) + 1;
        PercolationStats stats = new PercolationStats(n, T);
        String format = "%1$-23s = %2$s%n";
        log.info(String.format(format, "mean", stats.mean()));
        log.info(String.format(format, "stddev", stats.stddev()));
        log.info(String.format(format, "95% confidence interval", "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]"));
        final double elapsedTime = stopwatch.elapsedTime();
        log.info("elapsedTime: {}", elapsedTime);
    }

    @Test
    public void t() {
//        log.info(String.valueOf(Percolation.transform(4, 4, 1)));
//        Assert.assertEquals(1, Percolation.transform(3, 1, 1));
//        Assert.assertEquals(6, Percolation.transform(3, 2, 3));
//        Assert.assertEquals(7, Percolation.transform(3, 3, 1));
//        Assert.assertEquals(9, Percolation.transform(3, 3, 3));
//        Assert.assertEquals(5, Percolation.transform(3, 2, 2));
//        Assert.assertEquals(3, Percolation.transform(3, 1, 3));
//        Assert.assertEquals(16, Percolation.transform(4, 4, 4));
//        Assert.assertEquals(9, Percolation.transform(4, 3, 1));
    }
}