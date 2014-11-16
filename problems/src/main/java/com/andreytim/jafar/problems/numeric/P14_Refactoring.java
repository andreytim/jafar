package com.andreytim.jafar.problems.numeric;

import java.util.*;

/**
 * Refactoring
 * TopCoder:
 * Single Round Match 216 Round 1 - Division I, Level Two
 * http://community.topcoder.com/stat?c=problem_statement&pm=2986&rd=5862
 *
 * Created by shpolsky on 16.11.14.
 */
public class P14_Refactoring {

    public int countCombs(int n, int factor, String currComb) {
        if (n < 3) return 0;
        int res = 0;
        for (int i = factor; i*i <= n; i++) {
            if (n % i == 0) {
                // System.out.println(currComb + "," + i + "," + (n/i));
                res += countCombs(n/i, i, currComb + "," + i) + 1;
            }
        }
        return res;
    }

    public List<Integer> primeFact(int n) {
        int tmp = n;
        int p = 2;
        int pmax = (int) Math.sqrt(n);
        List<Integer> result = new ArrayList<>();
        while (n > 1 && p <= pmax) {
            if (n % p == 0) {
                result.add(p);
                n /= p;
            } else {
                p++;
            }
        }
        if (n < tmp && n > pmax) result.add(n);
        return result;
    }

    public int refactor(int n) {
        return countCombs(n, 2, "");
    }

    public static void test(int n) {
        System.out.printf("Input: %d; Prime factorization: %s; Result: %d\n",
                n, new P14_Refactoring().primeFact(n).toString(), new P14_Refactoring().refactor(n));
    }

    public static void main(String[] args) {
        test(24);
        test(9973);
        test(9240);
        test(1916006400);
    }

}
