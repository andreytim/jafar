package com.andreytim.jafar.problems.leetcode;

import java.util.*;

/**
 * https://oj.leetcode.com/problems/maximum-gap/
 *
 * Created by shpolsky on 05.01.15.
 */
public class MaximumGap {

    private List<Integer> radixSort(int[] arr) {
        List<List<Integer>> radix = new ArrayList<>();
        for (int i = 0; i < 10; i++) radix.add(new LinkedList<Integer>());
        for (int n : arr) radix.get(n % 10).add(n);
        for (int i = 1; i < 10; i++) {
            List<List<Integer>> newRadix = new ArrayList<>();
            for (int j = 0; j < 10; j++) newRadix.add(new LinkedList<Integer>());
            for (List<Integer> qr : radix) {
                for (int n : qr) {
                    int tmp = n;
                    for (int k = i; k > 0; k--) n /= 10;
                    newRadix.get(n % 10).add(tmp);
                }
            }
            radix = newRadix;
        }
        return radix.get(0);
    }

    public int maximumGap(int[] num) {
        if (num.length < 2) return 0;
        int prev = Integer.MIN_VALUE;
        int maxGap = Integer.MIN_VALUE;
        for (int n : radixSort(num)) {
            maxGap = Math.max(n - prev, maxGap);
            prev = n;
        }
        return maxGap;
    }

    public static void test(int[] arr) {
        System.out.printf("Input: %s; Result: %d;\n",
                Arrays.toString(arr), new MaximumGap().maximumGap(arr));
    }

    public static void main(String[] arr) {
        test(new int[]{1,10_000_000});
        test(new int[]{1,1});
        test(new int[]{1});
        test(new int[]{10, 4, 8, 0, 1});
    }
}
