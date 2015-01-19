package com.andreytim.jafar.problems.fphackercup.y2015.round1.a;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by shpolsky on 18.01.15.
 */
public class Homework {

    private static final int MAX = 10_000_000;
    private static int[] sieve = sieve();

    public static int[] sieve() {
        int[] sieve = new int[MAX+1];
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i] == 0) {
                for (int k = i; k < sieve.length; k += i) {
                    sieve[k]++;
                }
            }
        }
        return sieve;
    }

    public static String solve(Scanner in) {
        int A = in.nextInt(), B = in.nextInt(), K = in.nextInt();
        int count = 0;
        for (int n = A; n <= B; n++) {
            if (sieve[n] == K) count++;
        }
        return String.valueOf(count);
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(Homework.class.getResourceAsStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }

}
