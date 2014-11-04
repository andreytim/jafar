package com.andreytim.jafar.problems.numeric;

/**
 * Implement power function efficiently.
 * Assumptions:
 * - Base is a floating point number.
 * - Exponent is an integer number.
 * - Negative exponent is allowed.
 *
 * More complex methods than below could be found here:
 * http://en.wikipedia.org/wiki/Exponentiation_by_squaring
 *
 * Created by shpolsky on 20.10.14.
 */
public class P11_FastPower {

    /*
     * Remarks:
     * - complexity O(log(p))
     * - recursive
     */
    public static double pow(double a, int exp) {
        if (exp < 0) {
            return pow(1/a, -exp);
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return a;
        } else if ((exp & 1) == 1) {
            return a * pow(a*a, exp/2);
        } else {
            return pow(a*a, exp/2);
        }
    }

    /*
     * Remarks:
     * - complexity O(log(p))
     * - iterative
     */
    public static double ipow(double a, int exp) {
        if (exp == 0) {
            return 1;
        }
        if (exp < 0) {
            a = 1/a;
            exp = -exp;
        }
        double res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res *= a;
            }
            exp >>= 1;
            a *= a;
        }
        return res;
    }


    public static void main(String[] args) {
        test(322, 0);
        test(1, 1);
        test(1, -1);
        test(2.0, -1);
        test(4.0, -10);
        test(5.0, 1);
        test(5.0, 2);
        test(2.0, 10);
        test(2.0, 9);
        test(12.4232, 6);
        test(5345.234, -6);
    }

    private static void test(double a, int p) {
        System.out.printf("pow: %.4f^%d=%.8f\n", a, p, pow(a, p));
        System.out.printf("ipow: %.4f^%d=%.8f\n", a, p, ipow(a, p));
    }
}
