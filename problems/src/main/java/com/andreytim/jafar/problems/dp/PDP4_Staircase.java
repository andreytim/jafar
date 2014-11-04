package com.andreytim.jafar.problems.dp;

/**
 * Created by shpolsky on 28.10.14.
 */
public class PDP4_Staircase {

    /**
     * 1, 2 or 3 steps at a time.
     *
     * @param n
     * @return
     */
    public static int numberOfWays(int n) {
        if (n == 1 || n == 2) {
            return n;
        } else if (n == 3) {
            return 4;
        }
        int n1 = 1, n2 = 2, n3 = 4;
        for (int i = 3; i < n; i++) {
            int tmp = n1 + n2 + n3;
            n2 = n3;
            n1 = n2;
            n3 = tmp;
        }
        return n3;
    }

    public static void main(String[] args) {
        System.out.println(numberOfWays(4));
        System.out.println(numberOfWays(5));
        System.out.println(numberOfWays(10));
        System.out.println(numberOfWays(1));
        System.out.println(numberOfWays(20));

    }

}
