package com.andreytim.jafar.problems.dp;

import java.util.Arrays;

/**
 * Find maximum sum sub-array in an array.
 *
 * Clarifying questions could be:
 * - are there negative numbers in array (if no then the answer is the whole array)
 * - which numbers are there? integers? floats? (actually, doesn't matter)
 * - do we need to keep track of start and end positions or just sum? (just sum is more concise in the sense of coding)
 * - what complexity do we want? (brute-force - O(n^2), best - O(n))
 *
 * Created by shpolsky on 20.10.14.
 */
public class PDP1_MaxSumSubarray {

    /**
     * Dynamic programming approach.
     * Keeps track of max sum subarray ending in each of the cell.
     * If we know the max sum subarray for previous cell,
     * then for current cell it will be max(curr_el, prev_sum + curr_el).
     * Complexity: O(n)
     * Also known as Kadane's algorithm: http://en.wikipedia.org/wiki/Maximum_subarray_problem
     *
     * @param arr
     * @return
     */
    public static int[] maxSumSubarray(int[] arr) {
        if (arr.length == 0) return new int[]{};
        int maxSum = arr[0], currMaxSum = arr[0];
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > currMaxSum + arr[i]) { // if max sum array ending here is just the last element
                start = i;
                currMaxSum = arr[i];
            } else {
                currMaxSum += arr[i];
            }
            if (currMaxSum > maxSum) {
                maxSum = currMaxSum;
                end = i;
            }
        }
        return new int[]{start, end, maxSum};
    }

    public static void main(String[] args) {
        test(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        test(new int[]{});
        test(new int[]{-2, -3, -5, -1});
        test(new int[]{-2, -3, -5, -1, 5});
    }

    private static void test(int[] arr) {
        int[] result = maxSumSubarray(arr);
        if (result.length == 0) {
            System.out.printf("%s: empty result\n", Arrays.toString(arr));
        } else {
            System.out.printf("%s: start=%d, end=%d, max_sum=%d\n",
                    Arrays.toString(arr), result[0], result[1], result[2]);
        }
    }
}
