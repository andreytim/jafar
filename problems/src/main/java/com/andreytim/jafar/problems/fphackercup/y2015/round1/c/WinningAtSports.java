package com.andreytim.jafar.problems.fphackercup.y2015.round1.c;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by shpolsky on 18.01.15.
 */
public class WinningAtSports {

    private static final int MODULO = 1_000_000_007;

    private static int stressFree(int x, int y) {
        int[][] dp = new int[x+1][y+1];
        for (int i = 1; i <= x; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= y && j < i; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MODULO;
            }
        }
        return dp[x][y];
    }

    private static int stressful(int x, int y) {
        int[][] dp = new int[y+1][y+1];
        for (int i = 0; i <= y; i++) dp[0][i] = 1;
        for (int i = 1; i <= y; i++) {
            for (int j = i; j <= y; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MODULO;
            }
        }
        return dp[y][y];
    }

    private static String solve(Scanner in) {
        String[] scores = in.next().split("-");
        return String.format("%d %d",
                stressFree(Integer.parseInt(scores[0]), Integer.parseInt(scores[1])),
                stressful(Integer.parseInt(scores[0]), Integer.parseInt(scores[1])));
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(WinningAtSports.class.getResourceAsStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }

}
