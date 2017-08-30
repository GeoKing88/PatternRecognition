import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private int numberOfSegments = 0;
    private LineSegment[] segments;
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {

        for (int m = 0; m < points.length; m++) {
            if (points[m] == null) {
                throw new IllegalArgumentException();
            }
        }

        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsSegment = new Point[4];
        for (int i = 0; i < points.length - 3; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            pointsSegment[0] = points[i];
            for (int j = i + 1; j < points.length - 2; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
                pointsSegment[1] = points[j];
                double slope = points[i].slopeTo(points[j]);
                for (int h = j + 1; h < points.length - 1; h++) {
                    if (points[h] == null || points[i].compareTo(points[h]) == 0 || points[h].compareTo(points[j]) == 0) {
                        throw new IllegalArgumentException();
                    }
                    if (slope == points[j].slopeTo(points[h])) {
                        pointsSegment[2] = points[h];
                        for (int k = h + 1; k < points.length; k++) {
                            if (points[k] == null || points[k].compareTo(points[i]) == 0 || points[k].compareTo(points[j]) == 0 || points[k].compareTo(points[h]) == 0) {
                                throw new IllegalArgumentException();
                            }
                            if (slope == points[j].slopeTo(points[k])) {
                                pointsSegment[3] = points[k];
                                numberOfSegments++;
                                Arrays.sort(pointsSegment);
                                lineSegments.add(new LineSegment(pointsSegment[0], pointsSegment[3]));
                            }
                        }
                    }
                }
            }
        }
        segments = new LineSegment[lineSegments.size()];
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(segments);
    }


    public static void main(String[] args) {


        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

}



