package com.andreytim.jafar.problems.dp;

import java.math.BigInteger;
import java.util.Random;

/**
 * Imagine a robot sitting on the upper left corner of an X by Y grid.
 * The robot can only move in two directions: right and down.
 * How many possible paths are there for the robot to go from (0,0) to (X,Y)?
 * CtCI 9.2
 * 0 <= N, M <= 1000
 *
 * Created by shpolsky on 12.11.14.
 */
public class P83_SqrGridNumberOfWays {

    public BigInteger numWays(int N, int M) {
        if (N == 0 || M == 0) return BigInteger.ZERO;
        BigInteger[][] dpm = new BigInteger[N][M];
        for (int i = 0; i < N; i++) dpm[i][0] = BigInteger.ONE;
        for (int i = 0; i < M; i++) dpm[0][i] = BigInteger.ONE;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dpm[i][j] = dpm[i-1][j].add(dpm[i][j-1]);
            }
        }
        return dpm[N-1][M-1];
    }

    public void test(int N, int M) {
        System.out.printf("Number of ways for N=%d, M=%d: %d\n", N, M, numWays(N, M));
    }

    private static final Random RAND = new Random();

    public static void main(String[] args) {
        P83_SqrGridNumberOfWays numWays = new P83_SqrGridNumberOfWays();
        numWays.test(0,0);
        numWays.test(1,0);
        numWays.test(1,1);
        numWays.test(2,3);
        numWays.test(4,5);
        numWays.test(1000,1000);
        for (int i = 0; i < 10; i++) {
            numWays.test(RAND.nextInt(1001), RAND.nextInt(1001));
        }
    }

}
