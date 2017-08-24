import javax.sound.sampled.Line;

public class BruteCollinearPoints {

    private int numberOfSegments = 0;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

        segments = new LineSegment[points.length - 3];
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; i < points.length - 2; j++) {
                double slope = points[i].slopeTo(points[j]);
                for (int h = j + 1; h < points.length - 1; h++) {
                    if (slope == points[j].slopeTo(points[h])) {
                        for (int k = h + 1; k < points.length; k++) {
                            if (slope == points[j].slopeTo(points[h])) {
                                numberOfSegments++;
                                segments[i] = new LineSegment(points[i], points[k]);
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return segments;
    }


    public static void main(String args[]) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(3, 3);

        Point[] points = new Point[4];

        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        int numberOfSegments = bruteCollinearPoints.numberOfSegments;
        System.out.println("O numero de segmentos e: "+numberOfSegments+"\n");





    }

}
