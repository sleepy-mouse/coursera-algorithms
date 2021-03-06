package part1.week1;

import com.google.common.base.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static part1.week1.PercolationVisualizer.draw;

@Slf4j
public class PercolationTest {
    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 800;
    private static final String RESOURCE_PATH = "/part1/week1/percolation";

    private static URL getFileName(String fileName) {
        try {
            URI uri = Percolation.class.getResource(RESOURCE_PATH).toURI();
            Path path1 = Paths.get(uri);
            Path path = Paths.get(path1.toString(), fileName);
            return path.toUri().toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            log.error("", e);
            return null;
        }
    }

    private In getIn(final String fileName) {
        return new In(Percolation.class.getResource(RESOURCE_PATH + "/" + fileName));
    }

    private In getIn(URL url) {
        return new In(url);
    }

    private void singleTest(URL fileURL, boolean expected) {
        if (fileURL == null)
            return;
        try {
            log.info("File: {}", Paths.get(fileURL.toURI()).getFileName());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        In in = null;
        Percolation percolation;
        try {
            in = getIn(fileURL);
            int n = in.readInt();
            Stopwatch stopwatch = Stopwatch.createStarted();
            percolation = new Percolation(n);
            while (!in.isEmpty()) {
                int i = in.readInt(), j = in.readInt();
                percolation.open(i, j);
            }
            boolean percolates = percolation.percolates();
            Duration elapsed = stopwatch.elapsed();
            log.info("Time: {} ns", elapsed.getNano());
            assertThat(percolates).isEqualTo(expected);
        } finally {
            printDelimiter();
            if (in != null) {
                in.close();
            }
        }
    }

    private void printDelimiter() {
        System.out.println("*****************************************************************************************");
    }

    private Map<URL, Boolean> getTests() {
        try {
            Path path = Paths.get(Percolation.class.getResource(RESOURCE_PATH).toURI());
            Path testsFile = Paths.get(path.toString(), "tests.csv");
            return Files.lines(testsFile)
                    .map(s -> s.trim().split(","))
                    .collect(Collectors.toMap(
                            a -> getFileName(a[0] + ".txt"),
                            a -> Boolean.valueOf(a[1]),
                            (o, n) -> n,
                            LinkedHashMap::new));
        } catch (URISyntaxException | IOException e) {
            log.error("", e);
            return Collections.emptyMap();
        }
    }

    //    @Disabled
    @Test
    public void all() {
        getTests().forEach(this::singleTest);
    }

    //    @Disabled
    @Test
    public void visualise() {
        In in = getIn("input6.txt");
        int n = in.readInt();
        Percolation percolation = new Percolation(n);
        StdDraw.enableDoubleBuffering();
        draw(percolation, n);
        StdDraw.show();
        StdDraw.pause(DELAY);
        while (!in.isEmpty()) {
            int i = in.readInt(), j = in.readInt();
            percolation.open(i, j);
            draw(percolation, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
    }

    @Disabled
    @Test
    public void percolationStats() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        final int n = StdRandom.uniform(10) + 1;
        final int T = StdRandom.uniform(10) + 1;
        PercolationStats stats = new PercolationStats(n, T);
        String format = "%1$-23s = %2$s%n";
        log.info(String.format(format, "mean", stats.mean()));
        log.info(String.format(format, "stddev", stats.stddev()));
        log.info(String.format(format, "95% confidence interval", "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]"));
        log.info("Time: {} ns", stopwatch.elapsed().getNano());
    }

    @Disabled
    @Test
    public void testIndexMapping() {
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

    @Disabled
    @Test
    public void input1() {
        singleTest(getFileName("input1.txt"), true);
    }

    @Disabled
    @Test
    public void input2_no() {
        singleTest(getFileName("input2-no.txt"), false);
    }

    @Disabled
    @Test
    public void input3() {
        singleTest(getFileName("input3.txt"), true);
    }

    @Disabled
    @Test
    public void input4() {
        singleTest(getFileName("input4.txt"), true);
    }

    @Disabled
    @Test
    public void input5() {
        singleTest(getFileName("input5.txt"), true);
    }

    @Disabled
    @Test
    public void input6() {
        singleTest(getFileName("input6.txt"), true);
    }

    @Disabled
    @Test
    public void input7() {
        singleTest(getFileName("input7.txt"), true);
    }

    @Disabled
    @Test
    public void input8() {
        singleTest(getFileName("input8.txt"), true);
    }

    @Disabled
    @Test
    public void input8_dups() {
        singleTest(getFileName("input8-dups.txt"), true);
    }

    @Disabled
    @Test
    public void input8_no() {
        singleTest(getFileName("input8-no.txt"), false);
    }

    @Disabled
    @Test
    public void input10() {
        singleTest(getFileName("input10.txt"), true);
    }

    @Disabled
    @Test
    public void input10_no() {
        singleTest(getFileName("input10-no.txt"), false);
    }

    @Disabled
    @Test
    public void input20() {
        singleTest(getFileName("input20.txt"), true);
    }

    @Disabled
    @Test
    public void input50() {
        singleTest(getFileName("input50.txt"), true);
    }

    //    @Disabled
    @Test
    public void greeting57() {
        singleTest(getFileName("greeting57.txt"), false);
    }

    @Disabled
    @Test
    public void heart25() {
        singleTest(getFileName("heart25.txt"), false);
    }

    @Disabled
    @Test
    public void snake13() {
        singleTest(getFileName("snake13.txt"), true);
    }

    @Disabled
    @Test
    public void snake101() {
        singleTest(getFileName("snake101.txt"), true);
    }

    //    @Disabled
    @Test
    public void jerry47() {
        singleTest(getFileName("jerry47.txt"), true);
    }

    //    @Disabled
    @Test
    public void sedgewick60() {
        singleTest(getFileName("sedgewick60.txt"), true);
    }

    //    @Disabled
    @Test
    public void wayne98() {
        singleTest(getFileName("wayne98.txt"), true);
    }
}