package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;

/**
 * Find a magic index (A[i] = i) in a sorted array.
 * CtCI, 9.3
 *
 * Created by shpolsky on 30.11.14.
 */
public class P716_MagicIndex {

    public int magicHelper(int[] arr, int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] == mid) return mid;
            else if (arr[mid] > mid) return magicHelper(arr, lo, mid-1);
            else return magicHelper(arr, mid+1, hi);
        }
        return -1;
    }

    public int findMagic(int[] arr) {
        return magicHelper(arr, 0, arr.length-1);
    }

    private static void test(int arr[]) {
        System.out.printf("Input: %s; Result: %d\n", Arrays.asList(arr),
                new P716_MagicIndex().findMagic(arr));
    }

    public static void main(String[] args) {
        test(new int[]{});
        test(new int[]{0});
        test(new int[]{1});
        test(new int[]{-8,1,6,7,10});
        test(new int[]{-8,2,6,7,10});
        test(new int[]{-8,0,1,3,7,10});
    }

}
