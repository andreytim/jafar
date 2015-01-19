package com.andreytim.jafar.problems.topcoder.SRM646Div2;

import java.util.*;

/**
 * Created by shpolsky on 20.01.15.
 */
public class TheGridDivTwo
{
    private static final int[] X = new int[]{ 1,0,0,-1 };
    private static final int[] Y = new int[]{ 0,1,-1,0 };

    public int find(int[] x, int[] y, int k) {
        int[][] grid = new int[2001][2001];
        for (int i = 0; i < x.length; i++) {
            grid[x[i]+1000][y[i]+1000] = -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1000); queue.offer(1000);
        int maxX = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int cx = queue.poll(), cy = queue.poll();
            maxX = Math.max(maxX, cx-1000);
            if (grid[cx][cy] < k) {
                for (int i = 0; i < 4; i++) {
                    int nx = cx + X[i], ny = cy + Y[i];
                    if (grid[nx][ny] == 0) {
                        grid[nx][ny] = grid[cx][cy] + 1;
                        queue.offer(nx); queue.offer(ny);
                    }
                }
            }
        }
        return maxX;
    }

    private static void test(int[] x, int[] y, int k) {
        System.out.printf("Input: x=%s, y=%s, k=%d; Output: %d\n", Arrays.toString(x), Arrays.toString(y),
                k, new TheGridDivTwo().find(x, y, k));
    }

    public static void main(String[] args) {
        test(new int[]{-1,0,0,1}, new int[]{0,-1,1,0}, 9);
        test(new int[]{1,1,1,1}, new int[]{-2,-1,0,1}, 4);
        test(new int[]{}, new int[]{}, 1000);
    }
}
