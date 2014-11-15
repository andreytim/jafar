package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of times,
 * write code to find an element in the array.
 * You may assume that the array was originally sorted in increasing order.
 * It can contain duplicates also.
 *
 * CtCI, 11.3
 *
 * Created by shpolsky on 15.11.14.
 */
public class P77_FindInRotatedArray {

    /**
     * O(log(n)) for arrays of distinct numbers
     * but ony O(n) in the worst case when there are many duplicates.
     *
     * @param arr
     * @param k
     * @return
     */
    public int findRotated(int[] arr, int k) {
        int lo = 0, hi = arr.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] == k) {
                return mid;
            } else if (arr[lo] < arr[mid]) {
                if (arr[mid] > k && arr[lo] <= k) {
                   hi = mid - 1;
                } else {
                   lo = mid + 1;
                }
            } else if (arr[lo] > arr[mid]) {
                if (arr[mid] < k && arr[hi] >= k) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                if (arr[hi] != arr[mid]) lo = mid + 1;
                else {
                    lo++;
                    hi--;
                }
            }
        }
        return -1;
    }

    public static void test(int[] arr, int k) {
        System.out.printf("Input: arr=%s, k=%d; Result: %d\n", Arrays.toString(arr), k,
                new P77_FindInRotatedArray().findRotated(arr, k));
    }

    public static void main(String[] args) {
        test(new int[]{ 1 }, 1);
        test(new int[]{ 0 }, 1);
        test(new int[]{ 2, 1 }, 1);
        test(new int[]{ 1, 2 }, 1);
        test(new int[]{ 1, 1 }, 1);
        test(new int[]{ 0, 0 }, 1);

        // odd length
        test(new int[]{ 1, 2, 3, 4, 5, 6, 7 }, 3);
        test(new int[]{ 4, 5, 6, 7, 1, 2, 3 }, 3);
        test(new int[]{ 6, 7, 1, 2, 3, 4, 5 }, 3);
        test(new int[]{ 3, 4, 5, 6, 7, 1, 2 }, 3);
        test(new int[]{ 3, 3, 3, 3, 1, 2, 3 }, 3);
        test(new int[]{ 4, 4, 4, 4, 2, 3, 4 }, 3);
        test(new int[]{ 4, 4, 5, 6, 2, 3, 4 }, 3);
        test(new int[]{ 4, 4, 4, 4, 4, 4, 4 }, 3);
        test(new int[]{ 4, 4, 4, 4, 4, 4, 3 }, 3);
        test(new int[]{ 4, 4, 4, 4, 4, 3, 4 }, 3);
        test(new int[]{ 4, 4, 4, 4, 3, 4, 4 }, 3);
        test(new int[]{ 4, 4, 4, 3, 4, 4, 4 }, 3);
        test(new int[]{ 4, 4, 3, 4, 4, 4, 4 }, 3);
        test(new int[]{ 4, 3, 4, 4, 4, 4, 4 }, 3);
        test(new int[]{ 3, 4, 4, 4, 4, 4, 4 }, 3);

        // even length
        test(new int[]{ 2, 3, 4, 5, 6, 7 }, 3);
        test(new int[]{ 4, 5, 6, 7, 2, 3 }, 3);
        test(new int[]{ 6, 7, 2, 3, 4, 5 }, 3);
        test(new int[]{ 3, 4, 5, 6, 7, 2 }, 3);
        test(new int[]{ 3, 3, 3, 3, 2, 3 }, 3);
        test(new int[]{ 4, 4, 4, 2, 3, 4 }, 3);
        test(new int[]{ 4, 5, 6, 2, 3, 4 }, 3);
        test(new int[]{ 4, 4, 4, 4, 4, 4 }, 3);
        test(new int[]{ 4, 4, 4, 4, 4, 3 }, 3);
        test(new int[]{ 4, 4, 4, 4, 3, 4 }, 3);
        test(new int[]{ 4, 4, 4, 3, 4, 4 }, 3);
        test(new int[]{ 4, 4, 3, 4, 4, 4 }, 3);
        test(new int[]{ 4, 3, 4, 4, 4, 4 }, 3);
        test(new int[]{ 3, 4, 4, 4, 4, 4 }, 3);
    }

}
