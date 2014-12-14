package com.andreytim.jafar.problems.arrstr;

import com.andreytim.jafar.problems.Utils;

import java.util.*;

/**
 * Given a NxN matrix which contains all distinct 1 to n^2 numbers,
 * write code to print sequence of increasing adjacent sequential numbers.
 * ex: [[1 5 9],[2 3 8],[4 6 7]], should print 6 7 8 9.
 *
 * Created by shpolsky on 14.12.14.
 */
public class P221_IncreasingSeqInMatrixGoogle {

    public List<Integer> find(int i, int j, int step, int[][] m) {
        List<Integer> res = new ArrayList<>();
        res.add(m[i][j]);
        if (i > 0 && m[i-1][j] == m[i][j] + step) res.addAll(find(i-1, j, step, m));
        if (j > 0 && m[i][j-1] == m[i][j] + step) res.addAll(find(i, j-1, step, m));
        if (i < m.length-1 && m[i+1][j] == m[i][j] + step) res.addAll(find(i+1, j, step, m));
        if (j < m[0].length-1 && m[i][j+1] == m[i][j] + step) res.addAll(find(i, j+1, step, m));
        return res;
    }

    public void findSeq(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return;
        Set<Integer> found = new HashSet<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (found.add(m[i][j])) {
                    List<Integer> inc = find(i, j, 1, m);
                    List<Integer> dec = find(i, j, -1, m);
                    if (inc.size() > 1 || dec.size() > 1) {
                        List<Integer> seq = new LinkedList<>();
                        for (int k = dec.size()-1; k > 0; k--) seq.add(dec.get(k));
                        seq.add(m[i][j]);
                        for (int k = 1; k < inc.size(); k++) seq.add(inc.get(k));
                        found.addAll(seq);
                        System.out.println(seq.toString());
                    }
                }
            }
        }
    }

    private static void test(int[][] m) {
        System.out.printf("Input:\n");
        Utils.printMatrix(m);
        System.out.printf("Output:\n");
        new P221_IncreasingSeqInMatrixGoogle().findSeq(m);
    }

    public static void main(String[] args) {
        test(new int[][]{{1}});
        test(new int[][]{{1,5,9},{2,3,8},{4,6,7}});
        test(new int[][]{{1,2,3},{5,10,4},{6,7,8}});
        test(new int[][]{{2,3,10},{1,7,6},{9,8,0}});
    }

}
