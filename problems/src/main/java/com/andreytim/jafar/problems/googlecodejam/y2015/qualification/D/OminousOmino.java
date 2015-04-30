package com.andreytim.jafar.problems.googlecodejam.y2015.qualification.D;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by tim on 11/04/15.
 */
public class OminousOmino {

    private static final String R = "RICHARD";
    private static final String G = "GABRIEL";

    public static String solve(Scanner in) {
        int x = in.nextInt(), r = in.nextInt(), c = in.nextInt();
        if (r > c) {
            r ^= c;
            c ^= r;
            r ^= c;
        }
        if (x > r*c) return R;
        if (x == 1) return G;
        if (x == 2) {
            if (r * c % 2 != 0) return R;
        }
        if (x == 3) {
            if (r == 1) return R;
            if (r == 2 && c == 3) return G;
            if (r == 2) return R;
            if (r == 3) return G;
            if (r == 4) return R;
        }
        if (x == 4) {
            if (r*c % 4 != 0) return R;
            if (r == 1 || r == 2) return R;
        }
        return G;
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new FileInputStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out2.txt"));
//        PrintStream out = System.out;
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i + 1, solve(in));
        }
        out.close();
    }

}
