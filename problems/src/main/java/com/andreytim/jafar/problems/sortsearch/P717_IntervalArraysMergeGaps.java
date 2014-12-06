package com.andreytim.jafar.problems.sortsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of busy time intervals of two people as in a calendar,
 * find the free time intervals of both the people so as to arrange a new meeting.
 *
 * Created by shpolsky on 06.12.14.
 */
public class P717_IntervalArraysMergeGaps {

    public static class Interval {
        public final int a, b;
        public Interval(int a, int b) { this.a = a; this.b = b; }
        @Override public String toString() {
            return String.format("(%d,%d)", a, b);
        }
    }

    public List<Interval> freeTimes(Interval[] per1, Interval[] per2) {
        if (per1 == null || per2 == null || per1.length == 0 || per2.length == 0) {
            return Collections.emptyList();
        }
        List<Interval> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < per1.length && j < per2.length) {
            if (per1[i].a < per2[j].b) merged.add(per1[i++]);
            else merged.add(per2[j++]);
        }
        while (i < per1.length) merged.add(per1[i++]);
        while (j < per2.length) merged.add(per2[j++]);
        List<Interval> result = new ArrayList<>();
        for (int k = 0; k < merged.size()-1; k++) {
            Interval curr = merged.get(k);
            while (k < merged.size()-1 && merged.get(k).b < curr.b) k++;
            if (curr.b < merged.get(k+1).a-1)
                result.add(new Interval(curr.b+1, merged.get(k+1).a-1));
        }
        return result;
    }

    public static void test(Interval[] per1, Interval[] per2) {
        System.out.printf("Input: per1=%s, per2=%s; Output: %s\n", Arrays.toString(per1),
                Arrays.toString(per2), new P717_IntervalArraysMergeGaps().freeTimes(per1, per2));
    }

    public static void main(String[] args) {
        test(new Interval[]{}, new Interval[]{});
        test(new Interval[]{new Interval(1,2)}, new Interval[]{});
        test(new Interval[]{new Interval(1,2)}, new Interval[]{new Interval(2,3)});
        test(new Interval[]{new Interval(1,2)}, new Interval[]{new Interval(3,4)});
        test(new Interval[]{new Interval(1,2)}, new Interval[]{new Interval(4,5)});
        test(
                new Interval[]{new Interval(1,5), new Interval(10,14), new Interval(19,20), new Interval(27,30)},
                new Interval[]{new Interval(3,5), new Interval(12,15), new Interval(18,21), new Interval(23,24)});
    }

}
