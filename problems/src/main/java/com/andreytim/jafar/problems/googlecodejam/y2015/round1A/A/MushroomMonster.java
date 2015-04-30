package com.andreytim.jafar.problems.googlecodejam.y2015.round1A.A;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by tim on 28/04/15.
 */
public class MushroomMonster {

    private static int[] solve(Scanner in) {
        int[] is = new int[in.nextInt()];
        int max = 0;
        int[] res = new int[2];
        for (int i = 0; i < is.length; i++) {
            is[i] = in.nextInt();
            if (i > 0 && is[i] < is[i-1]) {
                res[0] += is[i-1] - is[i];
                max = Math.max(max, is[i-1] - is[i]);
            }
        }
        for (int i = 0; i < is.length-1; i++) {
            res[1] += Math.min(max, is[i]);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new FileInputStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
//        PrintStream out = System.out;
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int[] res = solve(in);
            out.printf("Case #%d: %d %d\n", i+1, res[0], res[1]);
        }
        out.close();
    }

}
