package com.andreytim.jafar.problems.leetcode;

import com.andreytim.jafar.problems.Utils;

import java.util.Deque;
import java.util.LinkedList;

public class MaximalRectangle {

    private int maxHistRectangle(int[] hist) {
        int[] h = new int[hist.length+1];
        System.arraycopy(hist, 0, h, 0, hist.length);
        Deque<Integer> st = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < h.length; i++) {
            while (!st.isEmpty() && h[st.peek()] > h[i])
                res = Math.max(h[st.pop()] * (st.isEmpty() ? i : (i - st.peek() - 1)), res);
            st.push(i);
        }
        return res;
    }

    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        if (matrix.length > 0 && matrix[0].length > 0) {
            int[][] hm = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == '0') hm[i][j] = 0;
                    else hm[i][j] = 1 + ((i == 0) ? 0 : hm[i-1][j]);
                }
                res = Math.max(res, maxHistRectangle(hm[i]));
            }
        }
        return res;
    }

    private static void test(char[][] matrix) {
        System.out.println("Input:");
        Utils.printMatrix(matrix);
        System.out.printf("Output: %d\n", new MaximalRectangle().maximalRectangle(matrix));
    }

    public static void main(String[] args) {
        test(new char[][]{{'1','1'},{'1','1'}});
    }

}
