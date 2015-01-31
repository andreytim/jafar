package com.andreytim.jafar.problems.careercup;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by shpolsky on 30.01.15.
 */
public class MaxQazGoogle {

    public int maxQaz(int[] arr) {
        int max = 0;
        TreeSet<Integer> heap = new TreeSet<>();
        for (int i = arr.length-1; i >= 0; i--) {
            heap.add(arr[i]);
            max = Math.max(max, heap.tailSet(arr[i], false).size());
        }
        return max;
    }

    private static void test(int[] arr) {
        System.out.printf("Input: %s; Output: %d\n",
                Arrays.toString(arr), new MaxQazGoogle().maxQaz(arr));
    }

    public static void main(String[] args) {
        test(new int[]{1, 33 , 25 , 26 , 58 , 41 , 59});
        test(new int[]{33 , 25 , 26 , 58 , 41 , 59});
        test(new int[]{1});
        test(new int[]{});
        test(new int[]{1,2,3,4,5,6,7,8,9});
        test(new int[]{9,8,7,6,5,4,3,2,1});
    }

}
