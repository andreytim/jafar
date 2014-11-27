package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;

/**
 * TopCoder:
 * Single Round Match 531 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=11278
 *
 * Created by shpolsky on 27.11.14.
 */
public class P712_UnsortedSequence {

    public int[] getUnsorted(int[] s) {
        Arrays.sort(s);
        int i = s.length-2;
        while (i >= 0 && s[i] == s[s.length-1]) { i--; }
        if (i >= 0) {
            s[i] ^= s[i+1];
            s[i+1] ^= s[i];
            s[i] ^= s[i+1];
        } else return new int[]{};
        return s;
    }

}
