package part2.week1;

import com.google.common.base.Strings;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@Slf4j
public class WordnetTest {

    private static String getResourceString(String fileName) {
        return "/part2/week1/" + fileName;
    }

    private Path path(String fileName) {
        try {
            return Paths.get(WordNet.class.getResource(String.format("/wordnet/%s", fileName)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Path path() throws URISyntaxException {
        final Path path = Paths.get(WordNet.class.getResource("/wordnet").toURI());
        log.info("path: {}", path);
        return path;
    }

    @Test
    public void testOutcast() throws Exception {
        WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast oc = new Outcast(w);
        try (Stream<Path> pathStream = Files.find(path(), 1,
                (p, a) -> p.getFileName().toString().startsWith("outcast") && p.getFileName().toString().endsWith("txt"))) {
            final List<String> ocList = pathStream.limit(8)
//                    .filter(d -> d.getFileName().toString().equals("outcast2.txt"))
                    .map(o -> o.getFileName().toString())
                    .map(f -> {
                        log.info("{}", f);
                        try (Stream<String> lines = Files.lines(path(f))) {
                            return lines.filter(e -> !Strings.isNullOrEmpty(e)).toArray(String[]::new);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .map(oc::outcast).collect(Collectors.toList());
            ocList.forEach(log::info);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private In in(String fileName) {
        return new In(SAP.class.getResource(getResourceString(fileName)));
    }

    @Rule
    public final ExpectedException illegalArgument = ExpectedException.none();

    @Test
    public void digraph1() {
        SAP s1 = new SAP(new Digraph(in("digraph1.txt")));
        assertEquals(1, s1.ancestor(3, 11));
        assertEquals(4, s1.length(3, 11));

        assertEquals(5, s1.ancestor(9, 12));
        assertEquals(3, s1.length(9, 12));

        assertEquals(0, s1.ancestor(7, 2));
        assertEquals(4, s1.length(7, 2));

        assertEquals(-1, s1.ancestor(1, 6));
        assertEquals(-1, s1.length(1, 6));

        assertEquals(6, s1.ancestor(6, 6));
        assertEquals(0, s1.length(6, 6));

        illegalArgument.expect(IllegalArgumentException.class);
        List<Integer> v = Arrays.asList(2, 7), w = Arrays.asList(-1, 1, 4, 6, 10, 11);
        assertEquals(6, s1.ancestor(v, w));
    }

    @Test
    public void digraph2() {
        SAP s = new SAP(new Digraph(in("digraph2.txt")));
        assertEquals(3, s.ancestor(1, 3));
        assertEquals(2, s.length(1, 3));

        assertEquals(0, s.ancestor(1, 5));
        assertEquals(2, s.length(1, 5));
    }

    @Test
    public void digraph3() {
        SAP s = new SAP(new Digraph(in("digraph3.txt")));
        assertEquals(3, s.ancestor(1, 3));
        assertEquals(2, s.length(1, 3));

        assertEquals(2, s.ancestor(2, 6));
        assertEquals(2, s.length(2, 6));
    }

    @Test
    public void digraph5() {
        SAP s = new SAP(new Digraph(in("digraph5.txt")));
        assertEquals(10, s.ancestor(11, 9));
        assertEquals(4, s.length(11, 9));
    }

    @Test
    public void digraph9() {
        SAP s = new SAP(new Digraph(in("digraph9.txt")));
        assertEquals(4, s.ancestor(5, 3));
        assertEquals(2, s.length(5, 3));
    }

    @Test
    public void digraphWordnet() {
        SAP s = new SAP(new Digraph(in("digraph-wordnet.txt")));
//        assertEquals(0, s.ancestor(35083, 48629));
        assertEquals(4, s.length(35083, 48629));
    }
}
