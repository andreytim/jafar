package com.andreytim.jafar.problems.numeric;

/**
 * Created by shpolsky on 22.11.14.
 */
public class P17_BinaryOfAFloat {

    public String binary(double n) {
        if (n <= 0 || n >= 1) {
            return "ERROR";
        }
        StringBuilder result = new StringBuilder("0.");
        while (result.length() < 33 && n > 0) {
            n *= 2;
            int intPart = (int) Math.floor(n);
            result.append(String.valueOf(intPart));
            n -= intPart;
        }
        return (result.length() < 33) ? result.toString() : "ERROR";
    }

    public static void test(double n) {
        System.out.printf("Input: %.8f; Result: %s\n", n, new P17_BinaryOfAFloat().binary(n));
    }

    public static void main(String[] args) {
        test(0);
        test(1);
        test(0.5);
        test(0.75);
        test(0.6);
        test(0.625);
        test(0.1);
    }

}
