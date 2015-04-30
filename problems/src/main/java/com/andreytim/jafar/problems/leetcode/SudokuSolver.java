package com.andreytim.jafar.problems.leetcode;

import com.andreytim.jafar.problems.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tim on 24/04/15.
 */
public class SudokuSolver {

    private static class Cell {
        public final int x, y;
        public final Set<Integer> possibilities;
        public Cell(int x, int y, Set<Integer> possibilities) {
            this.x = x; this.y = y; this.possibilities = possibilities;
        }
    }

    private static Set<Integer> getPossibilities(char[][] board, int x, int y) {
        Set<Integer> res = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < 9; i++) res.remove(board[x][i] - '0');
        for (int i = 0; i < 9; i++) res.remove(board[i][y] - '0');
        for (int i = 0; i < 9; i++) res.remove(board[3*(x/3) + i/3][3*(y/3) + i%3] - '0');
        return res;
    }

    private static Cell findBestEmptyCell(char[][] board) {
        Cell res = null;
        int minPos = Integer.MAX_VALUE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    Set<Integer> pos = getPossibilities(board, i, j);
                    if (pos.size() < minPos) {
                        res = new Cell(i, j, pos);
                        minPos = pos.size();
                    }
                }
            }
        }
        return res;
    }

    public static boolean recSolve(char[][] board) {
        Cell best = findBestEmptyCell(board);
        if (best != null) {
            if (!best.possibilities.isEmpty()) {
                for (int p : best.possibilities) {
                    board[best.x][best.y] = (char) (p + '0');
                    if (recSolve(board)) return true;
                }
                board[best.x][best.y] = '.';
            }
            return false;
        }
        return true;
    }

    public static void solveSudoku(char[][] board) {
        recSolve(board);
    }

    public static void test(String[] b) {
        char[][] board = new char[b.length][b.length];
        for (int i = 0; i < b.length; i++) {
            board[i] = b[i].toCharArray();
        }
        System.out.printf("Input board:\n");
        Utils.printMatrix(board);
        solveSudoku(board);
        System.out.printf("Output board:\n");
        Utils.printMatrix(board);
    }

    public static void main(String[] args) {
        test(new String[]{"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."});
    }

}
