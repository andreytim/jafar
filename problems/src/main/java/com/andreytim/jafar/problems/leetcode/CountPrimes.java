package com.andreytim.jafar.problems.leetcode;

/**
 * Created by tim on 27/04/15.
 */
public class CountPrimes {

    public int countPrimes(int n) {
        int count = 1;
        boolean[] sieve = new boolean[n+1];
        for (int i = 3; i <= n; i += 2) {
            if (!sieve[i]) {
                for (int k = i; k <= n; k += i) sieve[k] = true;
                count++;
            }
        }
        return count;
    }

    public static void test(int n) {
        System.out.printf("Input: %d; Output: %d\n", n, new CountPrimes().countPrimes(n));
    }

    public static void main(String[] args) {
        test(9);
        test(100000);
        test(5000000);
    }

}
