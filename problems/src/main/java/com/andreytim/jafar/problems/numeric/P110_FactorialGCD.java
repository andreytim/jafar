package com.andreytim.jafar.problems.numeric;

import java.util.HashSet;
import java.util.Set;

/**
 * TopCoder:
 * Single Round Match 283 Round 1 - Division II, Level Three
 * http://community.topcoder.com/stat?c=problem_statement&pm=5963&rd=8080
 *
 * Created by shpolsky on 29.11.14.
 */
public class P110_FactorialGCD {

    public int factGCD(int a, int b) {
        if (a >= b) return b;
        int res = 1;
        Iterable<Integer> pf = primeFactors(b);
        for (int p : pf) {
            int countpina = 0;
            for (long i = p; a/i > 0; i *= p) {
                countpina += a/i;
            }
            int countpinb = 0, d = p;
            while (b % d == 0) {
                d *= p;
                countpinb++;
            }
            res *= Math.pow(p, Math.min(countpina, countpinb));
        }
        return res;
    }

    public Iterable<Integer> primeFactors(int b) {
        int p = 2;
        Set<Integer> res = new HashSet<>();
        while (b > 1 && p*p <= b) {
            if (b % p == 0) {
                res.add(p);
                b /= p;
            } else p++;
        }
        if (b > 1) res.add(b);
        return res;
    }

    public static void test(int a, int b) {
        System.out.printf("Input: a=%d, b=%d; Result: %d\n", a, b,
                new P110_FactorialGCD().factGCD(a, b));
    }

    public static void main(String[] args) {
        test(5, 20);
        test(4, 40);
        test(7, 5040);
        test(2097711064, 2147483646);
        test(29, 1073741824);
    }
}
