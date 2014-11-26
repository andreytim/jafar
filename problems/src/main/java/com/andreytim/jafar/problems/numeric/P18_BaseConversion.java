package com.andreytim.jafar.problems.numeric;

/**
 * Convert the number in a base A to the number in a base B.
 * Numbers are represented as strings.
 *
 * EoPI, 5.7
 *
 * Created by shpolsky on 27.11.14.
 */
public class P18_BaseConversion {

    // Assuming that the string is a proper baseIn-number and bases are within [2,36]
    public String convert(int baseIn, String str, int baseOut) {
        if (str == null || str.isEmpty()) return str;
        int base10 = 0;
        for (char ch : str.toCharArray()) {
            base10 = base10*baseIn + (Character.isDigit(ch) ? ch - '0' : ch - 'A' + 10);
        }
        String result = "";
        while (base10 > 0) {
            int rem = base10 % baseOut;
            result = (rem < 10 ? (char)(rem + '0') : (char)(rem - 10 + 'A')) + result;
            base10 /= baseOut;
        }
        return result.equals("") ? "0" : result;
    }

    public static void test(int baseIn, String str, int baseOut) {
        System.out.printf("Input: baseIn=%d, num=%s, baseOut=%d; Result: %s\n", baseIn, str, baseOut,
                new P18_BaseConversion().convert(baseIn, str, baseOut));
    }

    public static void main(String[] args) {
        test(2, "1001001", 10);
        test(10, "567", 2);
        test(10, "0", 2);
        test(2, "0", 10);
        test(8, "6757", 16);
        test(2, "1110101", 16);
        test(10, "6757", 16);
        test(16, "67A57D", 10);
    }

}
