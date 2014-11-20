package com.andreytim.jafar.problems.arrstr;

/**
 * TopCoder:
 * Single Round Match 595 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=12794
 *
 * Created by shpolsky on 21.11.14.
 */
public class P29_LittleElephantAndBallsAgain {

    public int getNumber(String S) {
        int maxCount = 1;
        int currCount = 1;
        for (int i = 0; i < S.length()-1; i++) {
            if (S.charAt(i) != S.charAt(i+1)) {
                maxCount = Math.max(maxCount, currCount);
                currCount = 1;
            } else {
                currCount++;
            }
        }
        return S.length() - Math.max(maxCount, currCount);
    }

    public static void test(String s) {
        System.out.printf("Input: \"%s\"; Result: %d\n", s, new P29_LittleElephantAndBallsAgain().getNumber(s));
    }

    public static void main(String[] args) {
        test("RRGGBB");
        test("R");
        test("RGBRGB");
        test("RGGGBB");
        test("RGBRBRGRGRBBBGRBRBRGBGBBBGRGBBBBRGBGRRGGRRRGRBBBBR");
    }
}
