package com.andreytim.jafar.problems.arrstr;

import java.util.*;

/**
 * Find all pairs of integers within an array which sum to a specified value.
 * CtCI, 17.12
 *
 * Created by shpolsky on 22.11.14.
 */
public class P211_AllPairsSumToValue {

    public List<String> allPairsSumToValue(int[] arr, int sum) {
        Arrays.sort(arr);
        int i = 0, j = arr.length-1;
        List<String> result = new ArrayList<>();
        while (i < j) {
            if (arr[i] + arr[j] == sum) {
                result.add(arr[i] + ":" + arr[j]); // just for convenience of output
                while (i < arr.length-1 && arr[i+1] + arr[j] == sum ) i++;
                while (j > 0 && arr[i] + arr[j-1] == sum ) j--;
                i++; j--;
            } else if (arr[i] + arr[j] < sum) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    public List<String> allPairsSumToValueSet(int[] arr, int sum) {
        Set<String> result = new HashSet<>();
        Set<Integer> complements = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (complements.contains(sum - arr[i])) {
                result.add(arr[i] + ":" + (sum - arr[i]));
            }
            complements.add(arr[i]);
        }
        return new ArrayList<>(result);
    }

    public static void test(int[] arr, int sum) {
        System.out.printf("Input: arr=%s, sum=%d; Result: arr=%s, set=%s\n", Arrays.toString(arr), sum,
                new P211_AllPairsSumToValue().allPairsSumToValue(arr, sum).toString(),
                new P211_AllPairsSumToValue().allPairsSumToValueSet(arr, sum).toString());
    }

    public static void main(String[] args) {
        test(new int[]{0}, 0);
        test(new int[]{1, 2}, 3);
        test(new int[]{1, 2}, 2);
        test(new int[]{1, 4, 3, 2}, 5);
        test(new int[]{1, 4, 3, 4, 4, 2, 2, 3, 5, 0}, 5);
        test(new int[]{1, 2, 3, 4}, -1);
        test(new int[]{5, -4, 4, 4, 4, -2, 0, 6, -7, -3, -3}, -6);
    }
}
