import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Qiu
 */
class Util {
    static void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        List<Point> set = new ArrayList<>();
        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException();
            if (!set.contains(point))
                set.add(point);
        }
        if (set.size() != points.length)
            throw new IllegalArgumentException();
    }
}
