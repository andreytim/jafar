package com.andreytim.jafar.problems.arrstr;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Write an algorithm to print all ways of arranging eight queens
 * on an 8x8 chess board so that none of them share the same row,
 * column or diagonal. In this case, "diagonal" means all diagonals,
 * not just the two that bisect the board.
 * CtCI, 9.9 (The solution in the book is much better! O(n^2) vs. O(n^4) in time, O(n) vs. O(n^2) in memory)
 *
 * Created by shpolsky on 01.12.14.
 */
public class P220_EightQueens {

    private static final int BOARD_SIZE = 8;

    public void printWays() {
        printWays(new int[BOARD_SIZE][BOARD_SIZE], new LinkedList<String>());
    }

    private void printWays(int[][] board, Deque<String> currWay) {
        if (board.length == 0 || board[0].length == 0 ||
                board[0].length != board.length) throw new IllegalArgumentException();
        if (currWay.size() == BOARD_SIZE) {
            System.out.println(currWay.toString());
        } else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        fillQueen(board, i, j, 1);
                        currWay.push("" + (char)(j + 'a') + (i+1));
                        printWays(board, currWay);
                        currWay.pop();
                        fillQueen(board, i, j, -1);
                    }
                }
            }
        }
    }

    private void fillQueen(int[][] board, int i, int j, int step) {
        int tmp = board[i][j];
        for (int k = 0; k < board.length; k++) { board[k][j] += step; board[i][k] += step; }
        for (int r = i, c = j; r < board.length && c < board.length; r++, c++) board[r][c] += step;
        for (int r = i, c = j; r < board.length && c >= 0; r++, c--) board[r][c] += step;
        for (int r = i, c = j; r >= 0 && c < board.length; r--, c++) board[r][c] += step;
        for (int r = i, c = j; r >= 0 && c >= 0; r--, c--) board[r][c] += step;
        board[i][j] = tmp + step;
    }

    public static void main(String[] args) {
        new P220_EightQueens().printWays();
    }

}
