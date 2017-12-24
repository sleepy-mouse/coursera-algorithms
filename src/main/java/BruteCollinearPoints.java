import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Qiu
 */
public class BruteCollinearPoints {
    private final List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        Util.validate(points);
        double s1, s2, s3;
        segments = new ArrayList<>(points.length);
        for (int i = 0; i + 3 < points.length; i = i + 4) {
            s1 = points[i].slopeTo(points[i + 1]);
            s2 = points[i].slopeTo(points[i + 2]);
            s3 = points[i].slopeTo(points[i + 3]);
            if (s1 == s2 && s1 == s3 && s2 == s3) {
                segments.add(new LineSegment(points[i], points[i + 3]));
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}
