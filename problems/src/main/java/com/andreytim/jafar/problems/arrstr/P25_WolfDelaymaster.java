package com.andreytim.jafar.problems.arrstr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TopCoder:
 * Single Round Match 593 Round 1 - Division II, Level Two
 * http://community.topcoder.com/stat?c=problem_statement&pm=12778
 *
 * Created by shpolsky on 14.11.14.
 */
public class P25_WolfDelaymaster {

    private static final String VALID = "VALID";
    private static final String INVALID = "INVALID";
    private static final Set<Integer> HASHES = new HashSet<>(
            Arrays.asList((int) 'w', 'w' + 'o' * 4, 'w' + 'o' * 4 + 'l' * 16, 'w' + 'o' * 4 + 'l' * 16 + 'f' * 64));

    public String check(String str) {
        if (str.length() < 4) return INVALID;
        char[] arr = str.toCharArray();
        int currCount = 0, currHash = 0, currLength = 0, count = 0;
        char prev = 'f';
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != prev) {
                if (prev == 'w') currCount = count;
                else if (currCount != count) return INVALID;
                count = 1;
                if (prev == 'f') {
                    currLength = 0;
                    currHash = 0;
                }
                currHash += arr[i]*((int)Math.pow(4,currLength++));
                if (!HASHES.contains(currHash)) return INVALID;
            } else {
                count++;
            }
            prev = arr[i];
        }
        if (currCount != count) return INVALID;
        if (currHash != 'w' + 'o' * 4 + 'l' * 16 + 'f' * 64) return INVALID;
        return VALID;
    }

    public static void test(String str) {
        System.out.printf("Input string: %s; Result: %s\n", str, new P25_WolfDelaymaster().check(str));
    }

    public static void main(String[] args) {
        test("wolf");
        test("wwwooolllfff");
        test("wwolf");
        test("wwolfolf");
        test("wolfwwoollff");
        test("wolfwol");
    }

}
