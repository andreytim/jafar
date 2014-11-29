package com.andreytim.jafar.problems.arrstr;

import com.andreytim.jafar.problems.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Imagine you have a square matrix, where each cell (pixel) is either black or white.
 * Design an algorithm to find the maximum sub-square such that all four borders are filled with black pixels.
 * CtCI, 18.11
 *
 * Not optimal solution, check out the book for optimized one.
 *
 * Created by shpolsky on 29.11.14.
 */
public class P218_MaxBlackSubsquare {

    public static class Subsquare {
        public final int tli, tlj, size;
        public Subsquare(int tli, int tlj, int size) {
            this.tli = tli; this.tlj = tlj; this.size = size;
        }
    }

    public Subsquare getMax(boolean[][] M) {
        if (M.length == 0) return null;
        return recursiveHelper(M, 0, 0, M[0].length);
    }

    public Subsquare recursiveHelper(boolean[][] M, int i, int j, int n) {
        if (n == 0) return null;
        else {
            Subsquare res = null;
            if (M[i][j]) {
                res = new Subsquare(i, j, 1);
                for (int s = n; s > 1; s--) {
                    if (checkSq(M, i, j, s)) {
                        res = new Subsquare(i, j, s);
                        break;
                    }
                }
            }
            List<Subsquare> others = Arrays.asList(
                    recursiveHelper(M, i + 1, j, n - 1),
                    recursiveHelper(M, i, j + 1, n - 1),
                    recursiveHelper(M, i + 1, j + 1, n - 1)
            );
            for (Subsquare other : others) {
                if (res == null) res = other;
                if (other != null && other.size > res.size) res = other;
            }
            return res;
        }
    }

    public boolean checkSq(boolean[][] M, int i, int j, int s) {
        for (int k = 0; k < s; k++) {
            if (M[i][j] != M[i][j+k]) return false;
            if (M[i][j] != M[i+k][j]) return false;
            if (M[i][j] != M[i+s-1][j+k]) return false;
            if (M[i][j] != M[i+k][j+s-1]) return false;
        }
        return true;
    }

    public static void test(boolean[][] M) {
        System.out.printf("Input:\n");
        Utils.printMatrix(M);
        Subsquare ssq = new P218_MaxBlackSubsquare().getMax(M);
        if (ssq != null) {
            System.out.printf("Result: top-left=[%d,%d], size=%d\n", ssq.tli, ssq.tlj, ssq.size);
        } else {
            System.out.printf("Result: null\n");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test(new boolean[][]{{}});
        test(new boolean[][]{{true}});
        test(new boolean[][]{{false}});
        test(new boolean[][]{
                {false, false},
                {false, false}});
        test(new boolean[][]{
                {false, true},
                {false, false}});
        test(new boolean[][]{
                {true, true},
                {true, true}});
        test(new boolean[][]{
                {true, false, true, true, true},
                {true, false, true, true, true},
                {true, false, true, true, true},
                {true, false, true, false, true},
                {true, false, true, true, true}});
    }

}
