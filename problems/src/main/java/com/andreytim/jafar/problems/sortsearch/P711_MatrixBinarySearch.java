package com.andreytim.jafar.problems.sortsearch;

import com.andreytim.jafar.problems.Utils;

import java.util.Arrays;

/**
 * Given an MxN matrix in which each row and each column is sorted in ascending order,
 * write a method to find an element.
 * CtCI, 11.6
 *
 * Created by shpolsky on 24.11.14.
 */
public class P711_MatrixBinarySearch {

    private int[] findHelper(int[][] M, int k, int tli, int tlj, int bri, int brj) {
        if (tli > bri || tlj > brj || (tli == bri && tlj == brj && M[tli][tlj] != k)) {
            return null;
        }

        int midi = tli + (bri - tli)/2;
        int midj = tlj + (brj - tlj)/2;

        int[] res;
        if (M[midi][midj] == k) {
            res = new int[]{ midi, midj };
        } else if (M[midi][midj] < k) {
            res = findHelper(M, k, midi+1, midj+1, bri, brj);
        } else {
            res = findHelper(M, k, tli, tlj, midi, midj);
        }

        res = (res == null) ? findHelper(M, k, tli, midj+1, midi, brj) : res;
        res = (res == null) ? findHelper(M, k, midi+1, tlj, bri, midj) : res;
        return res;
    }

    public int[] find(int[][] M, int k) {
        if (M == null || M.length == 0 || M[0].length == 0) return null;
        return findHelper(M, k, 0, 0, M.length-1, M[0].length-1);
    }

    public static void test(int[][] M, int k) {
        System.out.printf("Input: k=%d, matrix=\n", k);
        Utils.printMatrix(M);
        int[] res = new P711_MatrixBinarySearch().find(M, k);
        System.out.printf("Result: %s\n\n", (res != null) ? Arrays.toString(res) : "no such element");
    }

    public static void main(String[] args) {
        test(new int[][]{{1}}, 1);
        test(new int[][]{{1}}, 2);
        test(new int[][]{
                { 1, 2 },
                { 3, 4 }
        }, 2);
        test(new int[][]{
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        }, 5);
        test(new int[][]{
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        }, 9);
        test(new int[][]{
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        }, 14);
    }

}
