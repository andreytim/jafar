package com.andreytim.jafar.problems.leetcode;

import com.andreytim.jafar.problems.Utils;

/**
 * Created by shpolsky on 31.01.15.
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            boolean row = false, col = false;
            for (int i = 0; i < matrix.length; i++)
                if (matrix[i][0] == 0) col = true;
            for (int i = 0; i < matrix[0].length; i++)
                if (matrix[0][i] == 0) row = true;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
            if (row) {
                for (int i = 0; i < matrix[0].length; i++) matrix[0][i] = 0;
            }
            if (col) {
                for (int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
            }
        }
    }

    private static void test(int[][] matrix) {
        System.out.printf("Input:\n");
        Utils.printMatrix(matrix);
        new SetMatrixZeroes().setZeroes(matrix);
        System.out.printf("Result:\n");
        Utils.printMatrix(matrix);
        System.out.println();
    }

    public static void main(String[] args) {
        test(new int[][]{ {0,0,0,5}, {4,3,1,4}, {0,1,1,4}, {1,2,1,3}, {0,0,1,1} });
    }

}
