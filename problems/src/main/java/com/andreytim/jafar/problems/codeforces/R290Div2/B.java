package com.andreytim.jafar.problems.codeforces.R290Div2;

import java.util.*;

public class B {

    private static final int[][] dir = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};

    private static boolean bfs(char[][] board, int x, int y) {
        char COLOR = board[x][y];
        Set<Integer> pending = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x); queue.offer(y);
        while (!queue.isEmpty()) {
            int cx = queue.poll(), cy = queue.poll();
            board[cx][cy] = '#';
            pending.remove(cx*board[0].length + cy);
            for (int[] d : dir) {
                int nx = cx + d[0], ny = cy + d[1];
                if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length
                        && board[nx][ny] == COLOR) {
                    if (pending.add(nx*board[0].length + ny)) {
                        queue.offer(nx); queue.offer(ny);
                    } else return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCycle(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '#' && bfs(board, i, j)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(A.class.getResourceAsStream("in.txt"));
        int N = in.nextInt(), M = in.nextInt();
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = in.next();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        System.out.println(hasCycle(board) ? "Yes" : "No");
    }

}
