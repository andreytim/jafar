package com.andreytim.jafar.problems.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by shpolsky on 11.01.15.
 */
public class LargestRectangleInHist {

    public int largestRectangleArea(int[] height) {
        int[] h = new int[height.length+1];
        System.arraycopy(height, 0, h, 0, height.length);
        h[h.length-1] = 0;
        int maxArea = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < h.length; i++) {
            while (!stack.isEmpty() && h[stack.peek()] > h[i]) {
                int idx = stack.pop();
                maxArea = Math.max(maxArea, h[idx]*(stack.isEmpty() ? i : i-stack.peek()-1));
            }
            stack.push(i);
        }
        return maxArea;
    }

    private static void test(int[] arr) {
        System.out.printf("Input: %s; Output: %d\n", Arrays.toString(arr),
                new LargestRectangleInHist().largestRectangleArea(arr));
    }

    public static void main(String[] args) {
        test(new int[]{1});
        test(new int[]{2,1,5,6,2,3});
    }
}
