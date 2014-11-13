package com.andreytim.jafar.problems.sorting;

import java.util.Arrays;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B.Write a method to merge B into A in sorted order.
 * CtCI 11.1
 *
 * Created by shpolsky on 13.11.14.
 */
public class P93_MergeTwoSortedInPlace {

    public void merge(int[] A, int[] B, int endA) {
        if (A.length - endA < B.length) return;
        int idxA = endA - 1, idxB = B.length - 1;
        int idx = idxA + idxB + 1;
        while (idxA >= 0 && idxB >= 0) {
            if (A[idxA] > B[idxB]) {
                A[idx--] = A[idxA--];
            } else {
                A[idx--] = B[idxB--];
            }
        }
        while (idxB >= 0) A[idx--] = B[idxB--];
    }

    public void test(int[] A, int[] B, int endA) {
        System.out.printf("Input: A=%s, B=%s, endA=%d; Output: ", Arrays.toString(A), Arrays.toString(B), endA);
        merge(A, B, endA);
        System.out.printf("%s\n", Arrays.toString(A));
    }

    public static void main(String[] args) {
        P93_MergeTwoSortedInPlace instance = new P93_MergeTwoSortedInPlace();
        instance.test(new int[]{1}, new int[]{2}, 1);
        instance.test(new int[]{1,2,0}, new int[]{5,6}, 2);
        instance.test(new int[]{1,3,5,7,9,0,0,0,0}, new int[]{2,4,6,8}, 5);
    }

}
