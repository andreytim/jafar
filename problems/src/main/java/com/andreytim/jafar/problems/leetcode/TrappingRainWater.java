package com.andreytim.jafar.problems.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by tim on 27/04/15.
 */
public class TrappingRainWater {

    public int trap(int[] h) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < h.length; i++) {
            if (i > 0 && h[i] > h[i-1] && !stack.isEmpty()) {
                int prev = stack.pop();
                while (!stack.isEmpty() && h[stack.peek()] < h[i]) {
                    res += (h[stack.peek()] - h[prev]) * (i-stack.peek()-1);
                    prev = stack.pop();
                }
                if (!stack.isEmpty()) res += (h[i] - h[prev]) * (i-stack.peek()-1);
            }
            stack.push(i);
        }
        return res;
    }

    private static void test(int[] h) {
        System.out.printf("Input: %s; Output: %d\n", Arrays.toString(h), new TrappingRainWater().trap(h));
    }

    public static void main(String[] args) {
        test(new int[]{});
        test(new int[]{1});
        test(new int[]{1,1});
        test(new int[]{1,2});
        test(new int[]{2,1});
        test(new int[]{2,1,2});
        test(new int[]{3,1,3});
        test(new int[]{3,1,2});
        test(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }

}
