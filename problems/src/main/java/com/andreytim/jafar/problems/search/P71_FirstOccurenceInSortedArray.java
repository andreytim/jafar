package com.andreytim.jafar.problems.search;

/**
 *  Design an efficient algorithm that takes a sorted array A and a key k,
 *  and finds the index of the first occurrence of an element k.
 *  First occurrence of the element larger than k.
 *
 * Created by shpolsky on 10.11.14.
 */
public class P71_FirstOccurenceInSortedArray {

    public static int firstOccurrence(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr[arr.length-1] < k) return -1;
        int lo = 0, hi = arr.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (arr[mid] >= k) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (arr[lo] != k) return -1;
        return lo;
    }

    public static int firstLargerOccurrence(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr[arr.length-1] <= k) return -1;
        int lo = 0, hi = arr.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (arr[mid] > k) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstOccurrence(new int[]{ 1, 1, 1, 1, 1 }, 1));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 4, 6, 7 }, 6));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 4, 5 }, 5));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 4 }, 4));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 4, 5 }, 2));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 4, 5 }, 1));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 5 }, 10));
        System.out.println(firstOccurrence(new int[]{ 1, 2, 4, 4, 5 }, 3));
        System.out.println(firstOccurrence(new int[]{ }, 3));
        System.out.println();
        System.out.println(firstLargerOccurrence(new int[]{ 1, 1, 1, 1, 1 }, 1));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 4, 6, 7 }, 6));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 4, 5 }, 5));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 4 }, 4));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 4, 5 }, 2));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 4, 5 }, 1));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 5 }, 10));
        System.out.println(firstLargerOccurrence(new int[]{ 1, 2, 4, 4, 5 }, 3));
        System.out.println(firstLargerOccurrence(new int[]{ }, 3));
    }

}
