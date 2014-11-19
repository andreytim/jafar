package com.andreytim.jafar.problems.dp;

import java.math.BigInteger;

/**
 * Count the number of ways to paint the fence consisting of N posts with K colors
 * when there could be no more than 2 posts of the same color.
 *
 * Created by shpolsky on 19.11.14.
 */
public class P84_FencePainting {

    public BigInteger count(int n, int k) {
        if (n < 1) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        BigInteger[] dpa = new BigInteger[n+1];
        dpa[0] = BigInteger.valueOf(k);
        dpa[1] = BigInteger.valueOf(k*k);
        for (int i = 2; i < n; i++) {
            dpa[i] = BigInteger.valueOf(k-1).multiply(dpa[i-1].add(dpa[i-2]));
        }
        return dpa[n-1];
    }

    public static void test(int n, int k) {
        System.out.printf("Input: n=%d, k=%d; Result: %d\n", n, k, new P84_FencePainting().count(n, k));
    }

    public static void main(String[] args) {
        test(1, 100);
        test(100, 1);
        test(2, 1);
        test(3, 1);
        test(1, 1);
        test(2, 1000);
        test(1, 1);
        test(3, 2);
        test(10, 2);
        test(20, 2);
        test(50, 2);
        test(100, 2);
        test(1000, 2);
        test(10000, 3);
        test(10000, 2);
    }
}
