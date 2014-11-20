package com.andreytim.jafar.problems.dp;

/**
 * TopCoder:
 * Single Round Match 585 Round 1 - Division II, Level Two
 * http://community.topcoder.com/stat?c=problem_statement&pm=12697
 *
 * Created by shpolsky on 21.11.14.
 */
public class P87_TrafficCongestionDivTwo {

    public long theMinCars(int treeHeight) {
        long[] dpa = new long[treeHeight+2];
        dpa[0] = 1;
        dpa[1] = 1;
        for (int i = 2; i < dpa.length; i++) {
            dpa[i] = 1;
            for (int j = 0; j < i-1; j++) {
                dpa[i] += 2*dpa[j];
            }
        }
        return dpa[treeHeight];
    }

    private static void test(int h) {
        System.out.printf("Input: %d; Result: %d\n", h, new P87_TrafficCongestionDivTwo().theMinCars(h));
    }

    public static void main(String[] args) {
        test(0);
        test(1);
        test(2);
        test(3);
        test(10);
        test(60);
    }
}
