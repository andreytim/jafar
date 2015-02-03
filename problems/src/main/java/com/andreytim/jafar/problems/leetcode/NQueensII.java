package com.andreytim.jafar.problems.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shpolsky on 02.02.15.
 */
public class NQueensII {

    private int queensHelper(int[] rows, int currRow) {
        if (currRow == rows.length) return 1;
        int res = 0;
        Set<Integer> busyCols = new HashSet<>();
        for (int r : rows) busyCols.add(r);
        for (int col = 0; col < rows.length; col++) {
            if (!busyCols.contains(col)) {
                boolean free = true;
                for (int i = currRow-1, j = 0; i >= 0 && j >= 0; i--, j++) {
                    if (rows[i] == col-j || rows[i] == col-j) {
                        free = false;
                        break;
                    }
                }
                if (free) {
                    rows[currRow] = col;
                    res += queensHelper(rows, currRow+1);
                    rows[currRow] = -1;
                }
            }
        }
        return res;
    }

    public int totalNQueens(int n) {
        int[] rows = new int[n];
        Arrays.fill(rows, -1);
        return queensHelper(rows, 0);
    }

}
