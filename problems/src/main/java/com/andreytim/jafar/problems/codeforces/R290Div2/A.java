package com.andreytim.jafar.problems.codeforces.R290Div2;

import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(A.class.getResourceAsStream("in.txt"));
        int N = in.nextInt(), M = in.nextInt();
        boolean right = true;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < M; j++) System.out.print('#');
                System.out.println();
            } else {
                if (right) {
                    for (int j = 0; j < M-1; j++) System.out.print('.');
                    System.out.println('#');
                    right = !right;
                } else {
                    System.out.print('#');
                    for (int j = 0; j < M-1; j++) System.out.print('.');
                    System.out.println();
                    right = !right;
                }
            }
        }
    }

}
