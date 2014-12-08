package com.andreytim.jafar.problems.topcoder.SRM639Div2;

/**
 * Created by shpolsky on 09.12.14.
 */
public class AliceGameEasy {

    public long findMinimumValue(long x, long y) {
        long i = 0;
        while (i*(i+1)/2 < x+y) { i++; }
        if (i*(i+1)/2 == x+y) {
            int count = 0;
            while (x != 0) {
                while (i > x) i--;
                x -= i--;
                count++;
            }
            return count;
        }
        return -1;
    }

}
