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

    public static int sqrt(int x) {
        if (x == 0 || x == 1) return x;
        double res = 2.0;
        while (Math.abs(x - res*res) > EPSILON) {
            res = (x/res + res)/2;
        }
        return (int) res;
    }

    public static void test(int n) {
        System.out.printf("Input: %d, Output: %d\n", n, sqrt(n));
    }

    public static void main(String[] args) {
        test(4);
        test(2);
        test(9);
        test(16);
        test(2147395599);
        test(3);
    }
}
