package com.andreytim.jafar.problems.arrstr;

/**
 * TopCoder:
 * Single Round Match 546 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=12048
 *
 * Created by shpolsky on 22.11.14.
 */
public class P212_ContestWinner {

    private static final int N = 1000000;

    public int getWinner(int[] events) {
        int[] counts = new int[N+1];
        int maxCount = 0;
        int currentLeader = 0;
        for (int i = 0; i < events.length; i++) {
            counts[events[i]]++;
            if (counts[events[i]] > maxCount) {
                currentLeader = events[i];
                maxCount = counts[events[i]];
            }
        }
        return currentLeader;
    }

}
