package com.andreytim.jafar.problems.arrstr;

import java.util.Arrays;

/**
 * TopCoder:
 * Single Round Match 575 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=12499
 *
 * Created by shpolsky on 21.11.14.
 */
public class P210_TheSwapsDivTwo {

    public int find(int[] seq) {
        int[] counts = new int[50];
        int result = 0;
        boolean duplicate = false;
        for (int i = 0; i < seq.length; i++) {
            result += i - counts[seq[i]];
            counts[seq[i]]++;
            if (counts[seq[i]] == 2) duplicate = true;
        }
        return result + (duplicate ? 1 : 0);
    }

    private static void test(int[] seq) {
        System.out.printf("Input: %s; Result: %d\n", Arrays.toString(seq), new P210_TheSwapsDivTwo().find(seq));
    }

    public static void main(String[] args) {
        test(new int[]{4, 7, 4, 4, 7});
        test(new int[]{1, 47});
        test(new int[]{9, 9, 9, 9});
        test(new int[]{22, 16, 36, 35, 14, 9, 33, 6, 28, 12, 18, 14, 47, 46, 29, 22, 14, 17, 4, 15, 28, 6, 39, 24, 47, 37});
    }

}
