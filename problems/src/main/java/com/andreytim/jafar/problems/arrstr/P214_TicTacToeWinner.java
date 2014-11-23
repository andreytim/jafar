package com.andreytim.jafar.problems.arrstr;

/**
 * Return the winner of NxN TicTacToe game board.
 *
 * Created by shpolsky on 23.11.14.
 */
public class P214_TicTacToeWinner {

    private static enum State { X, O }

    public State winner(State[][] board) {
        int N = board.length;
        boolean diag = board[0][0] != null;
        boolean bdiag = board[0][N-1] != null;
        for (int i = 0; i < N; i++) {
            if (board[i][i] != board[0][0]) diag = false;
            if (board[i][N-i-1] != board[0][N-1]) bdiag = false;
            boolean row = board[i][0] != null;
            boolean col = board[0][i] != null;
            if (row || col) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != board[i][0]) row = false;
                    if (board[j][i] != board[0][i]) col = false;
                }
                if (row) return board[i][0];
                if (col) return board[0][i];
            }
        }
        if (diag) return board[0][0];
        else if (bdiag) return board[0][N-1];
        else return null;
    }

    private static void test(State[][] board) {
        System.out.println("Input:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] == null ? "." : board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        State winner = new P214_TicTacToeWinner().winner(board);
        if (winner != null) {
            System.out.println("The winner is " + winner.toString() + ".");
        } else {
            System.out.println("There is no winner.");
        }
    }

    public static void main(String[] args) {
        test(new State[][]{
                { null, State.X, State.O },
                { null, State.X, State.O },
                { null, State.X, null }
        });
        test(new State[][]{
                { State.X, null, State.O },
                { null, State.X, State.O },
                { null, null, State.X }
        });
        test(new State[][]{
                { null, State.X, State.O },
                { null, State.O, State.X },
                { State.O, null, null }
        });
        test(new State[][]{
                { null, null, State.O },
                { State.X, State.O, State.X },
                { State.O, null, null }
        });
        test(new State[][]{
                { null, null, State.O },
                { null, State.X, State.O },
                { null, State.X, null }
        });
    }

}
