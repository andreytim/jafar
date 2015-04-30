package com.andreytim.jafar.problems.leetcode;

import com.andreytim.jafar.problems.Utils;

/**
 * Created by tim on 28/04/15.
 */
public class RotateImage {

    public void rotate(int[][] M) {
        for (int i = 0; i < (M.length + 1)/2; i++) {
            for (int j = 0; j < M.length/2; j++) {
                int tmp = M[i][j];
                M[i][j] = M[M.length-j-1][i];
                M[M.length-j-1][i] = M[M.length-i-1][M.length-j-1];
                M[M.length-i-1][M.length-j-1] = M[j][M.length-i-1];
                M[j][M.length-i-1] = tmp;
            }
        }
    }

    private static void test(int[][] M) {
        System.out.printf("Input:\n");
        Utils.printMatrix(M);
        new RotateImage().rotate(M);
        System.out.printf("Output:\n");
        Utils.printMatrix(M);
    }

    public static void main(String[] args) {
        test(new int[][]{{}});
        test(new int[][]{{1}});
        test(new int[][]{{1,2},{3,4}});
        test(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        test(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});
        test(new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}});
    }
}
