package com.andreytim.jafar.problems.fphackercup.y2015.qualification.a;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by shpolsky on 09.01.15.
 */
public class CookingTheBooks {

    private static void swap(char[] arr, int i, int j) {
        if (arr[i] != arr[j]) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

    private static String solve(Scanner in) {
        char[] n = in.next().toCharArray();
        int maxi = 0, mini = 0;
        char maxd = n[0], mind = n[0];
        for (int i = 1; i < n.length; i++) {
            if (n[i] >= maxd) {
                maxd = n[i];
                maxi = i;
            }
            if (n[i] != '0' && n[i] <= mind) {
                mind = n[i];
                mini = i;
            }
        }
        char[] min = Arrays.copyOf(n, n.length);
        if (mini > 0 && mini < n.length) swap(min, 0, mini);
        char[] max = Arrays.copyOf(n, n.length);
        if (maxi > 0 && maxi < n.length) swap(max, 0, maxi);
        return String.format("%s %s", new String(min), new String(max));
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(CookingTheBooks.class.getResourceAsStream("cooking_the_books.txt"));
        PrintStream out = System.out;
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
    }

}
