package com.andreytim.jafar.problems.dp;

/**
 * From TopCoder:
 * http://community.topcoder.com/stat?c=problem_statement&pm=2829&rd=5072
 * Single Round Match 197 Round 1 - Division II, Level Three
 *
 * Created by shpolsky on 12.11.14.
 */
public class P82_QuickSums {

    public static int minSums(String numbers, int sum) {
        int[][] M = new int[numbers.length()][sum + 1];
        for (int i = 0; i < numbers.length(); i++) {
            for (int s = 0; s <= sum; s++) {
                int minOps = Long.parseLong(numbers.substring(0, i+1)) == s ? 0 : Integer.MAX_VALUE;
                if (minOps != 0) {
                    for (int j = 1; j < i+1; j++) {
                        long currSum = s - Long.parseLong(numbers.substring(j, i+1));
                        if (currSum >= 0 && M[j-1][(int)currSum] != -1) {
                            minOps = Math.min(minOps, M[j-1][(int)currSum]+1);
                        }
                    }
                }
                M[i][s] = (minOps == Integer.MAX_VALUE ? -1 : minOps);
            }
        }
        return M[numbers.length()-1][sum];
    }

    public static void main(String[] args) {
        System.out.println(minSums("0", 0));
        System.out.println(minSums("0", 1));
        System.out.println(minSums("303", 6));
        System.out.println(minSums("303", 303));
        System.out.println(minSums("382834", 100));
        System.out.println(minSums("1000000000", 100));
    }
}
