package com.andreytim.jafar.algo.sort;

/**
 * Created by shpolsky on 27.10.14.
 */
public class Selection {

    public static int selectKthLargest(int[] arr, int k) {
        if (k >= arr.length) throw new IndexOutOfBoundsException("K should be within the array!");
        return selectKthRecursive(arr, 0, arr.length-1, k-1);
    }

    private static int selectKthRecursive(int[] arr, int lo, int hi, int k) {
        if (lo == hi) return arr[lo];
        int i = lo, j = hi;
        int pivotIdx = getPivot(i, j, arr);
        int pivotValue = arr[pivotIdx];
        while (i <= j) {
            while (arr[i] < pivotValue) i++;
            while (arr[j] > pivotValue) j--;
            if (i <= j) {
                swap(i, j, arr);
                i++; j--;
            }
        }
        if (pivotIdx == k) return arr[pivotIdx];
        else if (pivotIdx > k) return selectKthRecursive(arr, lo, pivotIdx-1, k);
        else return selectKthRecursive(arr, pivotIdx+1, hi, k-pivotIdx);
    }

    private static int getPivot(int lo, int hi, int[] arr) {
        return lo + (hi - lo)/2;
    }

    private static void swap(int i, int j, int[] arr) {
        if (i != j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

    public static void main(String[] args) {
        System.out.println(selectKthLargest(new int[]{ 1, 2, 3, 6, 4, 9, 0, 32 }, 4));
        System.out.println(selectKthLargest(new int[]{ 1, 2, 3, 6, 4, 9, 0, 32 }, 6));
        System.out.println(selectKthLargest(new int[]{ 1, 2, 3, 6, 4, 9, 0, 32 }, 1));
        System.out.println(selectKthLargest(new int[]{ 1, 2, 3, 6, 4, 9, 0, 32 }, 7));
    }
}
