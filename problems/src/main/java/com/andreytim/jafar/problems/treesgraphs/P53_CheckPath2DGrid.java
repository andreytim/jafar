package com.andreytim.jafar.problems.treesgraphs;

import java.util.*;

/**
 * Imagine that you are given a rectangular NxM grid with cells.
 * There is a robot in a starting cell.
 * The robot can pass through some of the cells while others are occupied
 * and it cannot go through them. There is a cell where it must reach.
 * Given the whole grid, the start and end cells, write a program,
 * which determines if the robot can reach its goal.
 *
 * Fails (too long) on big grids (N, M >= 10K). Need something like A*.
 *
 * Created by shpolsky on 19.11.14.
 */
public class P53_CheckPath2DGrid {

    private static class Point {
        public final int x;
        public final int y;
        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
        @Override public boolean equals(Object p) {
            if (p == null) return false;
            if (p instanceof Point) {
                return this.x == ((Point)p).x && this.y == ((Point)p).y;
            }
            return false;
        }
        @Override public int hashCode() {
            return (x << 16) + y;
        }
    }

    public boolean checkPath(boolean[][] grid, Point start, Point end) {
        if (grid == null || grid.length < 1 || grid[0].length < 1 ||
                !inRange(grid, start) || !inRange(grid, end) || !grid[start.x][start.y]) {
            return false;
        }
        Set<Point> visited = new HashSet<>();
        visited.add(start);
        Deque<Point> stack = new LinkedList<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Point pos = stack.pop();
            if (pos.equals(end)) return true;
            for (Point p : next(grid, pos)) {
                if (!visited.contains(p)) {
                    visited.add(p);
                    stack.push(p);
                }
            }
        }
        return false;
    }

    private boolean inRange(boolean[][] grid, Point p) {
        return p.x >= 0 && p.x < grid.length && p.y >= 0 && p.y < grid[0].length;
    }

    // permits only horizontal ot vertical steps, can by simply changed for another case
    private List<Point> next(boolean[][] grid, Point p) {
        List<Point> res = new ArrayList<>();
        if (p.x > 0 && grid[p.x-1][p.y]) res.add(new Point(p.x-1, p.y));
        if (p.x < grid.length-1 && grid[p.x+1][p.y]) res.add(new Point(p.x+1, p.y));
        if (p.y > 0 && grid[p.x][p.y-1]) res.add(new Point(p.x, p.y-1));
        if (p.y < grid[0].length-1 && grid[p.x][p.y+1]) res.add(new Point(p.x, p.y+1));
        return res;
    }

    private static final boolean[][] TEST_GRID = new boolean[][]{
            { true, true, true, true, true, true },
            { true, true, true, true, true, true },
            { true, true, false, true, true, true },
            { true, true, true, true, true, true },
            { true, true, true, true, false, false },
            { true, true, true, true, false, true },
            { true, true, true, true, false, true }
    };

    public static void test(boolean[][] grid, int x1, int y1, int x2, int y2) {
        System.out.printf("Input: grid_sizes=%dx%d, start=[%d, %d], end=[%d, %d]; ",
                grid.length, grid[0].length, x1, y1, x2, y2);
        System.out.printf("Result: %b\n", new P53_CheckPath2DGrid().checkPath(grid,
                new Point(x1, y1), new Point(x2, y2)));
    }

    public static boolean[][] generateHugeGrid(int N, int M) {
        boolean[][] grid = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], true);
        }
        return grid;
    }

    public static void main(String[] args) {
        test(new boolean[][]{{true}}, 0, 0, 0, 0);
        test(new boolean[][]{{false}}, 0, 0, 0, 0);
        test(TEST_GRID, 0, 0, 6, 5);
        test(TEST_GRID, 0, 0, 6, 3);
        test(TEST_GRID, 6, 3, 2, 2);
        test(TEST_GRID, 6, 5, 5, 5);
        test(generateHugeGrid(1_000, 2_000), 0, 0, 999, 1_999);
    }
}
