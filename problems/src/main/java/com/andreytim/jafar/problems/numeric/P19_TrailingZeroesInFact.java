package com.andreytim.jafar.problems.numeric;

import java.math.BigInteger;

/**
 * Write an algorithm which computes the number of trailing zeros in n factorial.
 * CtCI, 17.3
 *
 * Created by shpolsky on 29.11.14.
 */
public class P19_TrailingZeroesInFact {

    public int trailingZeroesInFact(int n) {
        int count = 0;
        for (int p = 5; p <= n; p *= 5) count += n/p;
        return count;
    }

    private static String fact(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++) res = res.multiply(BigInteger.valueOf(i));
        return res.toString();
    }

    public static void test(int n) {
        System.out.printf("Input: %d; Result: %d (Check: %s)\n", n,
                new P19_TrailingZeroesInFact().trailingZeroesInFact(n), fact(n));
    }

    public static void main(String[] args) {
        test(5);
        test(10);
        test(13);
        test(25);
        test(43);
    }

}
