package com.andreytim.jafar.problems.leetcode;

import java.util.Arrays;

/**
 * Created by shpolsky on 31.01.15.
 */
public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int total = 1, prev = 1, countDown = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i-1]) {
                if (countDown > 0) {
                    total += countDown*(countDown+1)/2;
                    if (countDown >= prev) total += countDown - prev + 1;
                    countDown = 0;
                    prev = 1;
                }
                prev = ratings[i] == ratings[i-1] ? 1 : prev+1;
                total += prev;
            } else countDown++;
        }
        if (countDown > 0) {
            total += countDown*(countDown+1)/2;
            if (countDown >= prev) total += countDown - prev + 1;
        }
        return total;
    }

    private static void test(int[] ratings) {
        System.out.printf("Input: %s; Output: %d\n", Arrays.toString(ratings), new Candy().candy(ratings));
    }

    public static void main(String[] args) {
        test(new int[]{ 1, 2, 2 });
        test(new int[]{ 1 });
        test(new int[]{ 1, 2, 3 });
        test(new int[]{ 1, 3, 2 });
        test(new int[]{ 3, 2, 1 });
        test(new int[]{ 1, 1, 1, 5, 4, 2, 1, 3, 10 });
    }

}
