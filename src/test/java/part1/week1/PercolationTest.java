package part1.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import org.junit.Test;

import java.net.URL;

import static part1.week1.PercolationVisualizer.draw;

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
}
