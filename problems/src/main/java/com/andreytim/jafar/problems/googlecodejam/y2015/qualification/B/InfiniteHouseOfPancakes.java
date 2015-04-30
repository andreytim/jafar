package com.andreytim.jafar.problems.googlecodejam.y2015.qualification.B;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by tim on 11/04/15.
 */
public class InfiniteHouseOfPancakes {

    private static int recur(int l, int[] d) {
        int max = d[0], mi = 0;
        for (int i = 1; i < d.length; i++) {
            if (d[i] > max) {
                max = d[i];
                mi = i;
            }
        }
        int min = l+max;
        if (max < 3) return min;
        int[] copy = new int[d.length + 1];
        System.arraycopy(d, 0, copy, 0, d.length);
        copy[mi] = (d[mi] + 1)/2;
        copy[copy.length - 1] = d[mi]/2;
        min = Math.min(min, recur(l+1, copy));
        if (d[mi] == 9) {
            copy[mi] = d[mi]/3;
            copy[copy.length - 1] = d[mi] - d[mi]/3;
            min = Math.min(min, recur(l+1, copy));
        }
        return min;
    }

    private static String solveBrute(Scanner in) {
        int[] d = new int[in.nextInt()];
        for (int i = 0; i < d.length; i++) {
            d[i] = in.nextInt();
        }
        return String.valueOf(recur(0, d));
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new FileInputStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out2.txt"));
//        PrintStream out = System.out;
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i + 1, solveBrute(in));
        }
        out.close();
    }

}
