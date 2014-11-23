package com.andreytim.jafar.problems.dp;

/**
 * Given an NxN matrix of positive and negative integers,
 * write code to find the sub-matrix with the largest possible sum.
 * CtCI, 18.12
 *
 * Created by shpolsky on 23.11.14.
 */
public class P810_MaxSumSubmatrix {

    private int[][] calcPartials(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                res[i][j] = M[i][j];
                if (i > 0) res[i][j] += res[i-1][j];
            }
        }
        return res;
    }

    public int[] maxSumSubmatrix(int[][] M) {
        if (M.length == 0 || M[0].length == 0) throw new IllegalArgumentException();
        int[][] partials = calcPartials(M);
        int[] result = new int[5]; // { tl.x, tl.y, br.x, br.y, sum }
        for (int srow = 0; srow < M.length; srow++) {
            for (int erow = srow; erow < M.length; erow++) {
                int currMax = Integer.MIN_VALUE;
                int startCol = 0;
                for (int col = 0; col < M[0].length; col++) {
                    int currSum = partials[erow][col];
                    if (srow > 0) currSum -= partials[srow-1][col];
                    if (currSum > currMax + currSum) {
                        startCol = col;
                        currMax = currSum;
                    } else {
                        currMax += currSum;
                    }
                    if (currMax > result[4]) {
                        result[0] = srow; result[1] = startCol;
                        result[2] = erow; result[3] = col;
                        result[4] = currMax;
                    }
                }
            }
        }
        return result;
    }

    // utils

    public static void printMatrix(int[][] M) {
        if (M.length == 0 || M[0].length == 0) return;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.printf("%5d", M[i][j]);
            }
            System.out.println();
        }
    }

    private static void test(int[][] M) {
        System.out.println("Input:");
        printMatrix(M);
        int[] res = new P810_MaxSumSubmatrix().maxSumSubmatrix(M);
        System.out.printf("Result: top-left=[%d, %d], bottom-right=[%d, %d], sum=%d\n",
                res[0], res[1], res[2], res[3], res[4]);
    }

    public static void main(String[] args) {
        test(new int[][]{{1}});
        test(new int[][]{
                { 1, 2 },
                { 3, 4 }
        });
        test(new int[][]{
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, -30 }
        });
        test(new int[][]{
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, -100, 12 },
                { 13, 14, 15, 16 }
        });
    }
}
