package com.andreytim.jafar.problems;

/**
 * Created by shpolsky on 24.11.14.
 */
public class Utils {

    public static void printMatrix(int[][] M) {
        if (M.length == 0 || M[0].length == 0) return;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.printf("%5d", M[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMatrix(boolean[][] M) {
        if (M.length == 0 || M[0].length == 0) return;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.printf("%5d", M[i][j] ? 1 : 0);
            }
            System.out.println();
        }
    }

    public static void printMatrix(Object[][] M, int padding) {
        if (M.length == 0 || M[0].length == 0) return;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.printf("%" + padding + "s", M[i][j].toString());
            }
            System.out.println();
        }
    }

}
