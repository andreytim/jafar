package com.andreytim.jafar.problems.fphackercup.y2015.qualification.b;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by shpolsky on 10.01.15.
 */
public class NewYearsResolution {

    private static class Triplet {
        public int p, c, f;
        public Triplet(int p, int c, int f) {
            this.p = p; this.c = c; this.f = f;
        }
    }

    private static String solve(Scanner in) {
        String res = "no";
        Triplet goal = new Triplet(in.nextInt(), in.nextInt(), in.nextInt());
        Triplet[] foods = new Triplet[in.nextInt()];
        for (int i = 0; i < foods.length; i++) {
            foods[i] = new Triplet(in.nextInt(), in.nextInt(), in.nextInt());
        }
        for (int i = 1; i < (1 << foods.length); i++) {
            Triplet sum = new Triplet(0,0,0);
            for (int j = 0; j < foods.length; j++) {
                if ((i & (1 << j)) != 0) {
                    sum.p += foods[j].p;
                    sum.c += foods[j].c;
                    sum.f += foods[j].f;
                }
            }
            if (sum.p == goal.p && sum.c == goal.c && sum.f == goal.f) {
                res = "yes";
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(NewYearsResolution.class.getResourceAsStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }

}
