package com.andreytim.jafar.problems.topcoder.SRM639Div2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shpolsky on 08.12.14.
 */
public class ElectronicPetEasy {

    public String isDifficult(int st1, int p1, int t1, int st2, int p2, int t2) {
        Set<Integer> times = new HashSet<>();
        for (int i = 0, s = st1; i < t1; i++, s += p1) {
            times.add(s);
        }
        for (int i = 0, s = st2; i < t2; i++, s += p2) {
            if (times.contains(s)) return "Difficult";
        }
        return "Easy";
    }

}
