
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {

    private LineSegment[] lineSegments;
    private ArrayList<LineSegment> arrayListLineSegments = new ArrayList<>();
    private int numberOfSegments = 0;

    public FastCollinearPoints(Point[] points) {

        checkNullArguments(points);

        Point[] aux = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(aux);
            Arrays.sort(aux, p.slopeOrder());

            int min = 0;
            while (min < aux.length && p.slopeTo(aux[min]) == Double.NEGATIVE_INFINITY) {
                min++;
            }
            if (min != 1) {
                throw new IllegalArgumentException();

            }
            int max = min;
            while (min < aux.length) {
                while (max < aux.length && p.slopeTo(aux[max]) == p.slopeTo(aux[min])) {
                    max++;
                }
                if (max - min >= 3) {
                    Point pointMin;
                    if (aux[min].compareTo(p) < 0) {
                        pointMin = aux[min];
                    } else {
                        pointMin = p;
                    }
                    Point pointMax;
                    if (aux[max - 1].compareTo(p) > 0) {
                        pointMax = aux[max - 1];
                    } else {
                        pointMax = p;
                    }
                    if (p == pointMin) {
                        arrayListLineSegments.add(new LineSegment(pointMin, pointMax));
                    }
                }
                min = max;
            }
        }
        lineSegments = new LineSegment[arrayListLineSegments.size()];
    }


    private void checkNullArguments(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
    }


    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return arrayListLineSegments.toArray(lineSegments);
    }


    public static void main(String args[]) {

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

}
