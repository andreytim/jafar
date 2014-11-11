package com.andreytim.jafar.problems.dp;

import java.util.Arrays;
import java.util.Random;

/**
 * Check wikipedia for O(nlog(n)) solution. This one is simple DP with n^2 complexity.
 *
 * Created by shpolsky on 11.11.14.
 */
public class P81_LongestIncreasingSubsequence {

    public static int[] longestIncreasingSubsequence(int[] arr) {
        if (arr == null) return null;
        if (arr.length == 0) return new int[]{};

        int[] sizes = new int[arr.length];
        sizes[0] = 1;
        int[] prev = new int[arr.length];
        int lisSize = 1, lisEnd = 0;

        for (int i = 1; i < arr.length; i++) {
            sizes[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && (sizes[j] + 1) > sizes[i]) {
                    sizes[i] = sizes[j] + 1;
                    prev[i] = j;
                }
            }
            if (sizes[i] > lisSize) {
                lisSize = sizes[i];
                lisEnd = i;
            }
        }

        int i = lisEnd;
        int[] result = new int[sizes[i]];
        for (int j = result.length - 1; j >= 0; j--) {
            result[j] = arr[i];
            i = prev[i];
        }
        return result;
    }

    private static Random RAND = new Random();

    private static int[] randomArray(int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = RAND.nextInt();
        }
        return res;
    }

    private static void testCase(int[] array) {
        if (array.length <= 10) {
            System.out.printf("Input: %s; Longest Increasing Subsequence: %s\n",
                    Arrays.toString(array), Arrays.toString(longestIncreasingSubsequence(array)));
        } else {
            System.out.printf("Input too large (size = %d), just run ... ", array.length);
            longestIncreasingSubsequence(array);
            System.out.println("OK!");
        }
    }

    public static void main(String[] args) {
        testCase(new int[]{});
        testCase(new int[]{1});
        testCase(new int[]{1,2});
        testCase(new int[]{1,0});
        testCase(new int[]{1,2,3,4,5});
        testCase(new int[]{-5, -3, -4, -1, 1, -2, 5});
        testCase(new int[]{0, -5, -3, -4, -1, 1, -2, 5, 1});
        testCase(randomArray(10));
        testCase(randomArray(10));
        testCase(randomArray(10));
        testCase(randomArray(2000));
        testCase(randomArray(2000));
        testCase(randomArray(2000));
    }
}
