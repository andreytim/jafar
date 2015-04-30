package com.andreytim.jafar.problems.googlecodejam.y2015.qualification.A;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by tim on 11/04/15.
 */
public class StandingOvation {

    private static String solve(Scanner in) {
        int[] shyness = new int[in.nextInt()+1];
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            shyness[i] = str.charAt(i) - '0';
        }
        int res = 0, sum = shyness[0];
        for (int i = 1; i < shyness.length; i++) {
            if (sum < i) {
                res += i - sum;
                sum += i - sum;
            }
            sum += shyness[i];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(new FileInputStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }

}
