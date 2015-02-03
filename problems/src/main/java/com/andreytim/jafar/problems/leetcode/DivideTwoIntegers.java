package com.andreytim.jafar.problems.leetcode;

public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        long dd = dividend, ds = divisor;
        if (Math.abs(dd) < Math.abs(ds)) return 0;
        if (ds == 0) return Integer.MAX_VALUE;
        int sign = 1;
        if ((dd > 0 && ds < 0) || (dd < 0 && ds > 0)) sign = -1;
        dd = Math.abs(dd);
        ds = Math.abs(ds);
        long res = 0;
        while (dd >= ds) {
            long count = 1, sub = ds;
            while ((sub << 1) < dd) { sub <<= 1; count <<= 1; }
            dd -= sub;
            res += count;
        }
        return (int) Math.min(res*sign, Integer.MAX_VALUE);
    }

    private static void test(int dividend, int divisor) {
        System.out.printf("Input: %d, %d; Result: %d\n", dividend, divisor,
                new DivideTwoIntegers().divide(dividend, divisor));
    }

    public static void main(String[] args) {
        test(-999511578, -2147483648);
    }

}
