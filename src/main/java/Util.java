import java.util.HashSet;
import java.util.Set;

/**
 * @author Chris Qiu
 */
class Util {
    static void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        Set<Point> set = new HashSet<>();
        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException();
            set.add(point);
        }
        if (set.size() != points.length)
            throw new IllegalArgumentException();
    }
}
