package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;

/**
 * Given an array of n first zeroes and m last ones.
 * Find the position of the last zero.
 * -1 in case of all ones.
 *
 * Created by shpolsky on 15.11.14.
 */
public class P76_LastZeroPosition {

    public int bsFindLastEqual(int[] arr, int k) {
        int lo = 0, hi = arr.length-1;
        while (lo < hi) {
            int mid = lo + (hi-lo+1)/2;
            if (arr[mid] > k) hi = mid-1;
            else lo = mid;
        }
        if (arr[lo] > k) return -1;
        return lo;
    }

    public int bsFindFirstEqual(int[] arr, int k) {
        int lo = 0, hi = arr.length-1;
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] >= k) hi = mid;
            else lo = mid+1;
        }
        if (arr[lo] < k) return -1;
        return lo;
    }

    public int findLastZeroPosition(int[] arr) {
        return bsFindLastEqual(arr, 0);
    }

    public int findFirstOnePosition(int[] arr) {
        return bsFindFirstEqual(arr, 1);
    }

    public static void test(int[] arr) {
        System.out.printf("Input: %s; Result: last_zero_idx=%d, first_one_idx=%d\n", Arrays.toString(arr),
                new P76_LastZeroPosition().findLastZeroPosition(arr),
                new P76_LastZeroPosition().findFirstOnePosition(arr));
    }

    public static void main(String[] args) {
        test(new int[]{ 1 });
        test(new int[]{ 0 });
        test(new int[]{ 1, 1 });
        test(new int[]{ 0, 0 });
        test(new int[]{ 0, 1 });
        test(new int[]{ 0, 0, 0, 0, 1, 1, 1 });
        test(new int[]{ 0, 0, 0, 0 });
        test(new int[]{ 1, 1, 1 });
    }

}
