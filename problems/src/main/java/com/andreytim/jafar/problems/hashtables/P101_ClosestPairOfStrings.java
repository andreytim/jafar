package com.andreytim.jafar.problems.hashtables;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Let's be an array of strings.
 * Write a function which finds a closest pair of equal entries.
 * For example, if
 * s = ["All", "work", "and", "no", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "results"],
 * then the second and third occurrences of "no" is the closest pair.
 * EoPI, 12.3
 *
 * Created by shpolsky on 14.11.14.
 */
public class P101_ClosestPairOfStrings {

    public Object[] closestPair(String[] arr) {
        if (arr.length < 2) return null;
        Map<String, Integer> strToLastIdx = new HashMap<>();
        Object[] result = new Object[3];
        int minDistance = Integer.MAX_VALUE;
        int i = 0;
        for (String str : arr) {
            if (strToLastIdx.containsKey(str)) {
                int distance = i - strToLastIdx.get(str);
                if (distance < minDistance) {
                    minDistance = distance;
                    result[0] = strToLastIdx.get(str);
                    result[1] = i;
                    result[2] = str;
                }
            }
            strToLastIdx.put(str, i);
            i++;
        }
        return result;
    }

    public static void test(String[] arr) {
        System.out.printf("Input: %s; Result: %s\n", Arrays.toString(arr),
                Arrays.toString(new P101_ClosestPairOfStrings().closestPair(arr)));
    }

    public static void main(String[] args) {
        test(new String[]{});
        test(new String[]{ "a" });
        test(new String[]{ "a", "a" });
        test(new String[]{ "a", "b" });
        test(new String[]{ "aa", "fds", "aa", "sdgs", "sd", "", "aa", "aa" });
        test(new String[]{ "All", "work", "and", "no", "play", "makes", "for",
                           "no", "work", "no", "fun", "and", "no", "results"});
    }

}
