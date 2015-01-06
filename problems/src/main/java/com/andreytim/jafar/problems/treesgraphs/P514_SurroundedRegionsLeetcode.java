package com.andreytim.jafar.problems.treesgraphs;

import com.andreytim.jafar.problems.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://oj.leetcode.com/problems/surrounded-regions/
 *
 * Created by shpolsky on 07.01.15.
 */
public class P514_SurroundedRegionsLeetcode {

    private static class Point {
        public final int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }

    public List<Point> neighbors(Point p, char[][] board) {
        List<Point> neighbors = new LinkedList<>();
        if (p.x > 0 && board[p.x-1][p.y] == 'O') neighbors.add(new Point(p.x-1, p.y));
        if (p.x < board.length-1 && board[p.x+1][p.y] == 'O') neighbors.add(new Point(p.x+1, p.y));
        if (p.y > 0 && board[p.x][p.y-1] == 'O') neighbors.add(new Point(p.x, p.y-1));
        if (p.y < board[0].length-1 && board[p.x][p.y+1] == 'O') neighbors.add(new Point(p.x, p.y+1));
        return neighbors;
    }

    public void bfsFill(char[][] board, int i, int j) {
        if (board[i][j] == 'O') {
            Queue<Point> queue = new LinkedList<>();
            queue.offer(new Point(i, j));
            board[i][j] = '#';
            while (!queue.isEmpty()) {
                for (Point p : neighbors(queue.poll(), board)) {
                    board[p.x][p.y] = '#';
                    queue.offer(p);
                }
            }
        }
    }

    public void solve(char[][] board) {
        if (board.length < 2 || board[0].length < 2) return;
        for (int i = 0; i < board.length; i++) {
            bfsFill(board, i, 0);
            bfsFill(board, i, board[0].length-1);
        }
        for (int i = 1; i < board[0].length-1; i++) {
            bfsFill(board, 0, i);
            bfsFill(board, board.length-1, i);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '#') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public static void test(String[] board) {
        char[][] bc = new char[board.length][board[0].length()];
        int i = 0;
        for (String s : board) {
            bc[i++] = s.toCharArray();
        }
        System.out.printf("Input: \n");
        Utils.printMatrix(bc);
        new P514_SurroundedRegionsLeetcode().solve(bc);
        System.out.printf("Output: \n");
        Utils.printMatrix(bc);
    }

    public static void main(String[] args) {
        test(new String[]{ "XXXX", "XOOX", "XXOX", "XOXX" });
        test(new String[]{ "XOOOOOOOOOOOOOOOOOOO","OXOOOOXOOOOOOOOOOOXX","OOOOOOOOXOOOOOOOOOOX","OOXOOOOOOOOOOOOOOOXO","OOOOOXOOOOXOOOOOXOOX","XOOOXOOOOOXOXOXOXOXO","OOOOXOOXOOOOOXOOXOOO","XOOOXXXOXOOOOXXOXOOO","OOOOOXXXXOOOOXOOXOOO","XOOOOXOOOOOOXXOOXOOX","OOOOOOOOOOXOOXOOOXOX","OOOOXOXOOXXOOOOOXOOO","XXOOOOOXOOOOOOOOOOOO","OXOXOOOXOXOOOXOXOXOO","OOXOOOOOOOXOOOOOXOXO","XXOOOOOOOOXOXXOOOXOO","OOXOOOOOOOXOOXOXOXOO","OOOXOOOOOXXXOOXOOOXO","OOOOOOOOOOOOOOOOOOOO","XOOOOXOOOXXOOXOXOXOO" });
    }
}
