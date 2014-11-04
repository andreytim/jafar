package com.andreytim.jafar.problems.geometric;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Given set of n points on 2D plane (Xi, Yi),
 * write a function to find K nearest points to the given point P.
 * Clarification:
 * - what algorithm complexity is sufficient here? (linear)
 * Let's analyse all the approaches.
 * Brute-force: sort all point by distance to P and choose top-K - O(n*log(n))
 * Heap-for-top: for each point calc the distance and put it to the min heap of size K - O(n*log(k))
 *         Good for the cases when K is much smaller than N.
 *         Tiny optimization: no need of sqrt in distance computation,
 *                 as we are not interested in the values - just in order.
 * Quick-select: compute the distances (O(n)). Find k-th smallest distance with quick-select algorithm (O(n)).
 *         Find all points with the distance less then the found one the previous step (O(n)).
 *         So, the overall complexity here is O(n).
 *
 * More complex approaches: KD-Tree, LSH (probabilistic but the most optimal for high-dimensional spaces).
 *
 * Created by shpolsky on 22.10.14.
 */
public class PGeom1_KNN2D {

    /**
     * Auxiliary class for 2D points.
     * Could be with getters and setters,
     * but such way is easier and much more concise.
     */
    private static class Point {
        public final double x;
        public final double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override public String toString() {
            return String.format("(%.2f;%.2f)", x, y);
        }
        public double sqrDistance(Point p) {
            return (p.x - x)*(p.x - x) + (p.y - y)*(p.y - y);
        }
    }

    /**
     * Top-heap approach using TreeMap as a heap. Quite concise. O(n*log(k))
     *
     * @param points
     * @param k
     * @param p
     * @return
     */
    public static Point[] kNearestNeighborsTreeMap(Point[] points, int k, Point p) {
        if (k == 0) return new Point[]{};
        TreeMap<Double, Point> heap = new TreeMap<>(); // asc key order by default
        for (Point op : points) { // O(n)
            heap.put(p.sqrDistance(op), op); // O(log(k))
            if (heap.size() > k) {
                heap.pollLastEntry(); // O(log(k))
            }
        }
        return heap.values().toArray(new Point[heap.size()]); // the array of size k makes sure that there won't be
    }                                                         // unnecessary array creations

    public static Point[] kNearestNeighborsPriorityQueue(Point[] points, int k, final Point p) {
        if (k == 0) return new Point[]{};
        PriorityQueue<Point> top = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override public int compare(Point p1, Point p2) {
                double dst1 = p.sqrDistance(p1);
                double dst2 = p.sqrDistance(p2);
                return (int) Math.signum(dst1 - dst2);
            }
        });
        for (Point op : points) {
            top.offer(op);
            if (top.size() > k) {
                top.poll();
            }
        }
        return top.toArray(new Point[top.size()]);
    }

    public static Point[] kNearestNeighborsQuickselect(Point[] points, int k, Point p) {
        return null; // TODO
    }

    public static void main(String[] args) {
        test(new double[]{23.32, 12.21, 432.2343, -2, 0, -324.4},
             new double[]{756, 5, -567.76, 56, 2, 564},
             0, new Point(1,1));
        test(new double[]{23.32, 12.21, 432.2343, -2, 0, -324.4},
                new double[]{756, 5, -567.76, 56, 2, 564},
                1, new Point(1,1));
        test(new double[]{23.32, 12.21, 432.2343, -2, 0, -324.4},
                new double[]{756, 5, -567.76, 56, 2, 564},
                3, new Point(1,1));
    }

    private static void test(double[] xs, double[] ys, int k, Point p) {
        Point[] points = new Point[xs.length];
        for (int i = 0; i < xs.length; i++) {
            points[i] = new Point(xs[i], ys[i]);
        }
        System.out.printf("Input: points=%s, k=%s, p=%s\n" +
                        "Output_TreeMap: %s\nOutput_PriorityQueue: %s\nOutput_QuickSelect: %s\n",
                Arrays.toString(points), k, p.toString(),
                Arrays.toString(kNearestNeighborsTreeMap(points, k, p)),
                Arrays.toString(kNearestNeighborsPriorityQueue(points, k, p)), "TODO");
    }
}
