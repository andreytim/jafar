package com.andreytim.jafar.problems.leetcode;

public class PalindromePartitioningII {

    public int minCut(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int l = 0; l <= i; l++) {
                if (l == 0) dp[i][l] = 0;
                else if (s.charAt(i) == s.charAt(i-l)) {
                    dp[i][l] = (l == 1) ? 0 : dp[i-1][l-2];
                } else {
                    dp[i][l] = Integer.MAX_VALUE;
                    for (int k = 0; k <= l; k++) {
//                        dp[i][l] = Math.min(dp[i-k][l-k] + dp[i][]);
                    }
                }
            }
        }
        return dp[s.length()-1][s.length()-1];
    }

    private static void test(String s) {
        System.out.printf("Input: %s; Output: %d\n", s, new PalindromePartitioningII().minCut(s));
    }

    public static void main(String[] args) {
        test("ababbbabbaba");
    }

}
