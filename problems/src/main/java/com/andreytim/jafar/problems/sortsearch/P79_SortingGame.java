package com.andreytim.jafar.problems.sortsearch;

import java.util.*;

/**
 * TopCoder:
 * Single Round Match 397 Round 1 - Division I, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=8745
 *
 * Created by shpolsky on 22.11.14.
 */
public class P79_SortingGame {

    private String toString(int[] board) {
        StringBuilder str = new StringBuilder();
        for (int i : board) {
            str.append(String.valueOf(i));
        }
        return str.toString();
    }

    private boolean sorted(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    private int[] reverse(int[] arr, int lo, int hi) {
        int[] res = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i <= (hi-lo)/2; i++) {
            if (lo+i != hi-i) {
                res[lo+i] ^= res[hi-i];
                res[hi-i] ^= res[lo+i];
                res[lo+i] ^= res[hi-i];
            }
        }
        return res;
    }

    public int fewestMoves(int[] board, int k) {
        Map<String, Integer> level = new HashMap<String, Integer>();
        level.put(toString(board), 0);
        Set<String> visited = new HashSet<>();
        visited.add(toString(board));
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(board);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (sorted(curr)) return level.get(toString(curr));
            for (int i = 0; i < curr.length-k+1; i++) {
                int[] next = reverse(curr, i, i+k-1);
                if (!visited.contains(toString(next))) {
                    visited.add(toString(next));
                    level.put(toString(next), level.get(toString(curr)) + 1);
                    queue.offer(next);
                }
            }
        }
        return -1;
    }

    public static void test(int[] board, int k) {
        System.out.printf("Input: board=%s, k=%d; Result: %d\n", Arrays.toString(board), k,
                new P79_SortingGame().fewestMoves(board, k));
    }

    public static void main(String[] args) {
        test(new int[]{1,2,3}, 3);
        test(new int[]{3,2,1}, 3);
        test(new int[]{5,4,3,2,1}, 2);
        test(new int[]{3,2,4,1,5}, 4);
        test(new int[]{7,2,1,6,8,4,3,5}, 4);
    }

}
