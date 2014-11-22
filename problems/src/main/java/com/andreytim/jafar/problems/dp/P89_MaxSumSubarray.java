package com.andreytim.jafar.problems.dp;

import java.util.Arrays;

/**
 * Return the sum of the contiguous sequence with the largest sum.
 * CtCI, 17.8
 *
 * Created by shpolsky on 22.11.14.
 */
public class P89_MaxSumSubarray {

    public int contiguousMaxSum(int[] arr) {
        int maxSum = arr[0], currSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            currSum = Math.max(currSum + arr[i], arr[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    public static void test(int[] arr) {
        System.out.printf("Input: %s; Result: %d\n", Arrays.toString(arr),
                new P89_MaxSumSubarray().contiguousMaxSum(arr));
    }

    public static void main(String[] args) {
        test(new int[]{ -1 });
        test(new int[]{ 235 });
        test(new int[]{ -5, -8, -2, -1224, -3 });
        test(new int[]{ -5, -8, -2, 2, -1224, -3 });
        test(new int[]{ -5, -8, -2, 0, -1224, -3 });
        test(new int[]{ -5, -8, 5, -2, 3, -1224, -3 });
        test(new int[]{ 1, 2, 3, 4, 5 });
    }

}
