package com.andreytim.jafar.problems.arrstr;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
 * write a method to rotate the image by 90 degrees. Do it in-place.
 * CtCI, 1.6
 *
 * Created by shpolsky on 23.11.14.
 */
public class P216_MatrixRotate {

    public void rotate(int[][] M) {
        int N = M.length;
        for (int i = 0; i < (N+1)/2; i++) {
            for (int j = 0; j < N/2; j++) {
                int tmp = M[i][j];
                M[i][j] = M[N-j-1][i];
                M[N-j-1][i] = M[N-i-1][N-j-1];
                M[N-i-1][N-j-1] = M[j][N-i-1];
                M[j][N-i-1] = tmp;
            }
        }
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

    public static void test(int[][] M) {
        System.out.println("Input:");
        printMatrix(M);
        System.out.println("Result:");
        new P216_MatrixRotate().rotate(M);
        printMatrix(M);
        System.out.println();
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
                { 7, 8, 9 }
        });
        test(new int[][]{
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        });
    }

}
