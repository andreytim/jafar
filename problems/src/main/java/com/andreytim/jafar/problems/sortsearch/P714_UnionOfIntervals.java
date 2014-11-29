package com.andreytim.jafar.problems.sortsearch;

/**
 * TopCoder:
 * Single Round Match 277 Round 1 - Division II, Level Three
 * http://community.topcoder.com/stat?c=problem_statement&pm=4823&rd=8074
 *
 * Created by shpolsky on 29.11.14.
 */
public class P714_UnionOfIntervals {

    public int nthElement(int[] lowerBound, int[] upperBound, int n) {
        long lo = Integer.MIN_VALUE, hi = Integer.MAX_VALUE;
        while (lo < hi) {
            long count = 0;
            long v = lo + (hi-lo+1)/2;
            for (int i = 0; i < lowerBound.length; i++) {
                if (v > lowerBound[i]) {
                    long upper = v;
                    if (v > upperBound[i]) upper = upperBound[i] + 1;
                    count += upper - lowerBound[i];
                }
            }
            if (count <= n) lo = v;
            else hi = v-1;
        }
        return (int)lo;
    }

}
