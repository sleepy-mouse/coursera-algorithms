/**
 * @author Chris Qiu
 */
public class BruteCollinearPoints {
    private final Point[] points;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        Util.validate(points);
        this.points = points;
        segments = new LineSegment[points.length];
        for (int i = 0; i < points.length - 1; i++) {
            segments[i] = new LineSegment(points[i], points[i + 1]);
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
}
