package com.andreytim.jafar.problems.dp;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents),
 * nickels (5 cents) and pennies (1 cent), write code to calculate
 * the number of ways of representing n cents.
 * CtCI, 9.8
 *
 * Created by shpolsky on 20.11.14.
 */
public class P86_CoinsRepresentation {

    private static enum Coin {
        PENNIE(1), NICKEL(5), DIME(10), QUARTER(25);
        public final int nom;
        private Coin(int nom) { this.nom = nom; }
    }

    public long countWays(int n) {
        if (n < 1) return 0;
        long[] dpa = new long[n+1];
        dpa[0] = 1;
        for (int i = 1; i < dpa.length; i++) {
            long count = 0;
            for (Coin coin : Coin.values()) {
                if (i - coin.nom >= 0) {
                    count += dpa[i - coin.nom];
                }
            }
            dpa[i] = count;
        }
        return dpa[n];
    }

    public static void test(int n) {
        System.out.printf("Input: %d; Result: %d\n", n, new P86_CoinsRepresentation().countWays(n));
    }

    public static void main(String[] args) {
        test(0);
        test(1);
        test(3);
        test(5);
        test(10);
        test(25);
        test(100);
        test(10000);
    }

}
