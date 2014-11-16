package com.andreytim.jafar.problems.numeric;

/**
 * TopCoder:
 * Single Round Match 304 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=6195&rd=9825
 *
 * Created by shpolsky on 17.11.14.
 */
public class P15_RugSizes {

    public int rugCount(int area) {
        int count = 1;
        for (int i = 2; i*i <= area; i++) {
            if (area % i == 0 && (i*i == area || (i & 1) != 0 || (area/i & 1) != 0)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.printf("Input: %d; Result: %d\n", 4, new P15_RugSizes().rugCount(4));
        System.out.printf("Input: %d; Result: %d\n", 8, new P15_RugSizes().rugCount(8));
        System.out.printf("Input: %d; Result: %d\n", 100_000, new P15_RugSizes().rugCount(100_000));
        System.out.printf("Input: %d; Result: %d\n", 124123, new P15_RugSizes().rugCount(124123));
    }

}
