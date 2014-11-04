package com.andreytim.jafar.problems.dp;

import java.util.Arrays;

/**
 * Detailed description of the approach:
 * http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
 * Created by shpolsky on 27.10.14.
 */
public class PDP3_LongestArithmeticProgressionInArr {

    public static int getLLAP(int[] arr) {
        if (arr.length <= 2) return arr.length;
        Arrays.sort(arr);
        int[][] mL = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            mL[i][arr.length-1] = 2;
        }
        int maxLength = 2;
        for (int j = arr.length-2; j > 0; j--) {
            int i = j-1, k = j+1;
            while (i >= 0 && k <= arr.length-1) {
                if (arr[i] + arr[k] == 2 * arr[j]) {
                    mL[i][j] = mL[j][k] + 1;
                    maxLength = Math.max(maxLength, mL[i][j]);
                    i--; k++;
                } else if (arr[i] + arr[k] > 2 * arr[j]) {
                    mL[i][j] = 2;
                    i--;
                } else {
                    k++;
                }
            }
            while (i >= 0) mL[i--][j] = 2;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        test(new int[]{1, 7, 10, 13, 14, 19});
        test(new int[]{1, 7, 10, 15, 27, 29});
        test(new int[]{2, 4, 6, 8, 10});
    }

    private static void test(int[] arr) {
        System.out.printf("Input array: %s, length of LAP: %d\n", Arrays.toString(arr), getLLAP(arr));
    }
}
