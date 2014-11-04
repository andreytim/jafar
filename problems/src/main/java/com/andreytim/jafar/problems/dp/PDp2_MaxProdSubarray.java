package com.andreytim.jafar.problems.dp;

import java.util.Arrays;

/**
 * Maximum product sub-array of an integer array.
 * Upgrade follow-up of {@link PDP1_MaxSumSubarray}.
 *
 * Created by shpolsky on 20.10.14.
 */
public class PDP2_MaxProdSubarray {

    /**
     * It doesn't keep track of the positions, just calculates the needed product.
     * Main idea is to use the same Dynamic Programming approach as in PArr1, but
     * considering the fact, that the previous minimum product, if it's negative,
     * could become maximum after multiplication to the current element.
     *
     * @param arr
     * @return
     */
    public static int maxProduct(int[] arr) {
        if (arr.length == 0) return Integer.MIN_VALUE;
        int maxProd = arr[0], currMaxProd = arr[0], currMinProd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int newMaxProd = Math.max(Math.max(currMaxProd * arr[i], arr[i]), currMinProd * arr[i]);
            currMinProd = Math.min(Math.min(currMinProd * arr[i], arr[i]), currMaxProd * arr[i]);
            currMaxProd = newMaxProd;
            maxProd = Math.max(maxProd, currMaxProd);
        }
        return maxProd;
    }

    public static void main(String[] args) {
        test(new int[]{-4, -3, -2});
        test(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        test(new int[]{});
        test(new int[]{-2, -3, -5, -1});
        test(new int[]{-2, -3, -5, -1, 5});
    }

    private static void test(int[] arr) {
        System.out.printf("%s: %d\n", Arrays.toString(arr), maxProduct(arr));
    }
}
