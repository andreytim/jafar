package com.andreytim.jafar.problems.treesgraphs;

import com.andreytim.jafar.problems.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implement the "paint fill" function that one might see on many image editing programs.
 * That is, given a screen (represented by a two-dimensional array of colors),
 * a point, and a new color, fill in the surrounding area until the color changes from the original color.
 * CtCI, 9.7
 *
 * Created by shpolsky on 30.11.14.
 */
public class P511_PaintFill {

    public static enum Color { R, G, B }

    public static class Point {
        public final int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }

    public void fill(Color[][] grid, Point start, Color fillColor) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return;
        if (start.x < 0 || start.x >= grid.length) return;
        if (start.y < 0 || start.y >= grid[0].length) return;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        Color originColor = grid[start.x][start.y];
        grid[start.x][start.y] = fillColor;
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            for (Point p : getNeighbors(grid, curr, originColor)) {
                grid[p.x][p.y] = fillColor;
                queue.offer(p);
            }
        }
    }

    private Iterable<Point> getNeighbors(Color[][] grid, Point p, Color c) {
        List<Point> result = new ArrayList<>();
        if (p.x > 0 && grid[p.x-1][p.y] == c) result.add(new Point(p.x-1, p.y));
        if (p.y > 0 && grid[p.x][p.y-1] == c) result.add(new Point(p.x, p.y-1));
        if (p.x < grid.length-1 && grid[p.x+1][p.y] == c) result.add(new Point(p.x+1, p.y));
        if (p.y < grid[0].length-1 && grid[p.x][p.y+1] == c) result.add(new Point(p.x, p.y+1));
        return result;
    }

    private static void test(Color[][] grid, int x, int y, Color c) {
        System.out.println("Input:");
        Utils.printMatrix(grid,3);
        new P511_PaintFill().fill(grid, new Point(x, y), c);
        System.out.println("Result:");
        Utils.printMatrix(grid,3);
        System.out.println();
    }

    public static void main(String[] args) {
        test(new Color[][]{{ Color.R }}, 0, 0, Color.R);
        test(new Color[][]{{ Color.R }}, 0, 0, Color.G);
        test(new Color[][]{
                { Color.R, Color.G, Color.G, Color.R },
                { Color.R, Color.G, Color.R, Color.R },
                { Color.R, Color.G, Color.G, Color.R },
                { Color.R, Color.G, Color.R, Color.G }
        }, 1, 1, Color.B);
        test(new Color[][]{
                { Color.R, Color.G, Color.G, Color.R },
                { Color.R, Color.G, Color.R, Color.R },
                { Color.R, Color.G, Color.G, Color.R },
                { Color.R, Color.G, Color.R, Color.G }
        }, 3, 3, Color.B);
    }

}
