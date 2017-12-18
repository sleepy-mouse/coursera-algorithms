import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;

@Slf4j
public class PointTest {
    @Test
    public void slopeTo() {
        Point p1 = new Point(299, 331), p2 = new Point(387, 123);
        final double slope1 = p1.slopeTo(p2), slope2 = p2.slopeTo(p1);
        Assertions.assertThat(Math.abs(slope1)).isEqualTo(Math.abs(slope2));
    }

    @Test
    public void compareTo() {
        Point p1 = new Point(299, 331), p2 = new Point(387, 123);
        Assertions.assertThat(Math.abs(p1.compareTo(p2))).isEqualTo(Math.abs(p2.compareTo(p1)));
    }

    @Test
    public void slopeOrder() {
        Point p = new Point(401, 120), q = new Point(132, 167);
        Point r = new Point(141, 423), s = new Point(311, 277);
        log.info("p.slopeOrder().compare(q, r)): {}", p.slopeOrder().compare(q, r));
        log.info("p.slopeOrder().compare(r, s)): {}", p.slopeOrder().compare(r, s));
        log.info("p.slopeOrder().compare(q, s)): {}", p.slopeOrder().compare(q, s));
    }
}
