package com.andreytim.jafar.problems.treesgraphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TopCoder:
 * Single Round Match 218 Round 1 - Division II, Level Three
 * http://community.topcoder.com/stat?c=problem_statement&pm=3093&rd=5864
 *
 * Created by shpolsky on 18.11.14.
 */
public class P52_PermissionTree {

    private static class Folder {
        public final int idx;
        public final int parent;
        public final Set<String> users;
        private Folder(int idx, int parent, Set<String> users) {
            this.idx = idx; this.parent = parent; this.users = users;
        }
        public static Folder valueOf(int idx, String str) {
            String[] arr = str.split("\\s");
            return new Folder(idx, Integer.parseInt(arr[0]),
                    new HashSet<String>(Arrays.asList(arr[1].split(","))));
        }
    }

    private int findHome(String[] folders, String user) {
        int result = -1;
        for (int i = 0; i < folders.length; i++) {
            Folder folder = Folder.valueOf(i, folders[i]);
            if (folder.users.contains(user)) {
                if (result == -1) {
                    result = folder.idx;
                } else {
                    Set<Integer> pathToRoot = new HashSet<>();
                    pathToRoot.add(folder.idx);
                    while (folder.parent != folder.idx) {
                        folder = Folder.valueOf(folder.parent, folders[folder.parent]);
                        pathToRoot.add(folder.idx);
                    }
                    Folder resultFolder = Folder.valueOf(result, folders[result]);
                    while (!pathToRoot.contains(resultFolder.idx)) {
                        resultFolder = Folder.valueOf(resultFolder.parent, folders[resultFolder.parent]);
                    }
                    result = resultFolder.idx;
                }
            }
        }
        return result;
    }

    public int[] findHome(String[] folders, String[] users) {
        if (folders == null || users == null || users.length < 1 || folders.length < 1)
            return new int[]{};
        int[] result = new int[users.length];
        for (int i = 0; i < users.length; i++) {
            result[i] = findHome(folders, users[i]);
        }
        return result;
    }

    public static void test(String[] folders, String[] users) {
        System.out.printf("Input: folders=%s, users=%s\n", Arrays.toString(folders), Arrays.toString(users));
        System.out.printf("Result: homes=%s\n", Arrays.toString(new P52_PermissionTree().findHome(folders, users)));
    }

    public static void main(String[] args) {
        test(new String[]{"0 Admin", "0 Joe,Phil", "0 Joe"}, new String[]{"Admin", "Joe", "Phil"});
        test(new String[]{}, new String[]{});
        test(null, null);
        test(new String[]{"0 Admin"}, new String[]{"Peter", "Paul", "Mary"});
        test(new String[]{"0 Admin", "2 John", "0 Peter,John", "0 Tim", "1 John"},
                new String[]{"John"});
        test(new String[] {"0 Admin", "0 Jeff", "1 Mark,Tim", "1 Tim,Jeff", "0 Dan", "4 Ed", "4 Tom", "4 Kyle,Ed",
                           "0 Ben", "8 Rich", "8 Sam", "8 Tim"},
                new String[]{"Jeff", "Ed", "Tim", "Steve"});
        test(new String[]{"0 Admin", "0 Bob,Joe,Bob", "0 Joe"}, new String[]{"Joe", "Bob"});
    }

}
