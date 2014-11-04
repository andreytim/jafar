package com.andreytim.jafar.problems.numeric;

/**
 * Created by shpolsky on 27.10.14.
 */
public class P12_Sqrt {

    public static final double EPSILON = 0.0001;

    /**
     * Binary search approach. O(log(n))
     *
     * @param n
     * @param epsilon
     * @return
     */
    public static double sqrt(double n, double epsilon) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        double lo = 0.0, hi = n;
        while (hi - lo > epsilon) {
            double m = lo + (hi - lo) / 2;
            if (m * m == n) return m;
            else if (m * m > n) hi = m;
            else lo = m;
        }
        return lo + (hi - lo) / 2;
    }

    /**
     * Babylonian method.
     * http://www.geeksforgeeks.org/square-root-of-a-perfect-square/
     *
     * @param n
     * @param epsilon
     * @return
     */
    public static double sqrtBab(double n, double epsilon) {
        double y = n;
        double x = 1;
        while (y - x > epsilon) {
            y = x + (y - x)/2;
            x = n/y;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.printf("Sqrt of %.2f: bs = %.4f, bab = %.4f\n", 4D, sqrt(4, EPSILON), sqrtBab(4, EPSILON));
        System.out.printf("Sqrt of %.2f: bs = %.4f, bab = %.4f\n", 1024D, sqrt(1024, EPSILON), sqrtBab(1024, EPSILON));
        System.out.printf("Sqrt of %.2f: bs = %.4f, bab = %.4f\n", 25D, sqrt(25, EPSILON), sqrtBab(25, EPSILON));
        System.out.printf("Sqrt of %.2f: bs = %.4f, bab = %.4f\n", 144D, sqrt(144, EPSILON), sqrtBab(144, EPSILON));
        System.out.printf("Sqrt of %.2f: bs = %.4f, bab = %.4f\n", 1D, sqrt(1, EPSILON), sqrtBab(1, EPSILON));
        System.out.printf("Sqrt of %.2f: bs = %.4f, bab = %.4f\n", 0D, sqrt(0, EPSILON), sqrtBab(0, EPSILON));
    }
}
