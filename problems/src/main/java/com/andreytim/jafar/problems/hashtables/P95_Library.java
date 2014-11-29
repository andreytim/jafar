package com.andreytim.jafar.problems.hashtables;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TopCoder:
 * Single Round Match 384 Round 1 - Division I, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=7659
 *
 * Created by shpolsky on 29.11.14.
 */
public class P95_Library {

    public int documentAccess(String[] records, String[] userGroups, String[] roomRights) {
        Set<String> groups = new HashSet<>(Arrays.asList(userGroups));
        Set<String> rights = new HashSet<>(Arrays.asList(roomRights));
        Set<String> access = new HashSet<>();
        for (String record : records) {
            String[] rec = record.split("\\s");
            if (rights.contains(rec[1]) && groups.contains(rec[2])) {
                access.add(rec[0]);
            }
        }
        return access.size();
    }

}
