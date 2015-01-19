package com.andreytim.jafar.problems.fphackercup.y2015.round1.d;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by shpolsky on 18.01.15.
 */
public class CorporateGifting {

    private static int[] generateSeq(int n) {
        int[] seq = new int[n+1];
        seq[1] = 0;
        for (int i = 2; i < seq.length; i++) seq[i] = 1;
        return seq;
    }

    private static int[] generateSeq2(int n) {
        int[] seq = new int[n+1];
        seq[1] = 0;
        for (int i = 2; i < seq.length; i++) seq[i] = i-1;
        return seq;
    }

    private static String solve(Scanner in) {
        int[] ps = new int[in.nextInt()+1];
        for (int i = 1; i < ps.length; i++) {
            ps[i] = in.nextInt();
        }
        int[][] dp = new int[ps.length][3];
        Map<Integer, Set<Integer>> children = new HashMap<>();
        for (int i = ps.length-1; i > 0; i--) {
            if (!children.containsKey(ps[i])) {
                children.put(ps[i], new HashSet<Integer>());
            }
            children.get(ps[i]).add(i);
            if (!children.containsKey(i)) {
                dp[i][0] = 1; dp[i][1] = 2; dp[i][2] = 3;
            } else {
                dp[i][0] = 1;
                for (int child : children.get(i)) {
                    dp[i][0] += Math.min(dp[child][1], dp[child][2]);
                }
                dp[i][1] = 2;
                for (int child : children.get(i)) {
                    dp[i][1] += Math.min(dp[child][0], dp[child][2]);
                }
                dp[i][2] = 3;
                for (int child : children.get(i)) {
                    dp[i][2] += Math.min(dp[child][0], dp[child][1]);
                }
            }
        }
        return String.valueOf(Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]));
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(CorporateGifting.class.getResourceAsStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }


}
