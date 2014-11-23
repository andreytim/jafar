package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;

/**
 * Given an array of integers, write a method to find indices m and n such that
 * if you sort the elements m through n, the entire array would be sorted.
 * Minimize n - m (that is, find the smallest such sequence).
 * CtCI, 17.6
 *
 * Created by shpolsky on 23.11.14.
 */
public class P710_MinSubarrayToSort {

    // O(nlog(n)) solution, check the book for the one that linear but much longer
    public int[] minSubarrayToSort(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        int i = 0;
        while (i < arr.length && arr[i] == sorted[i]) i++;
        if (i == arr.length) return new int[]{0,0}; // sorted already
        int j = arr.length-1;
        while (i < arr.length && arr[j] == sorted[j]) j--;
        return new int[]{i,j};
    }

    private static void test(int[] arr) {
        System.out.printf("Input: %s; Result: %s\n", Arrays.toString(arr),
                Arrays.toString(new P710_MinSubarrayToSort().minSubarrayToSort(arr)));
    }

    public static void main(String[] args) {
        test(new int[]{ 1 });
        test(new int[]{ 1, 2 });
        test(new int[]{ 2, 1 });
        test(new int[]{ 1, 2, 3 });
        test(new int[]{ 1, 3, 2 });
        test(new int[]{ 2, 1, 3 });
        test(new int[]{ 1, 3, 2, 4 });
        test(new int[]{ 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 });
    }

}
