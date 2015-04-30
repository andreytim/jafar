package com.andreytim.jafar.problems.leetcode;

import java.util.*;

/**
 * Created by tim on 28/04/15.
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < num.length; i++) {
            Set<String> cache = new HashSet<>();
            while (res.peekFirst().size() == i) {
                List<Integer> l = res.removeFirst();
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>();
                    newL.addAll(l.subList(0,j));
                    newL.add(num[i]);
                    newL.addAll(l.subList(j,l.size()));
                    if (cache.add(newL.toString())) res.add(newL);
                }
            }
        }
        return res;
    }

    public static void test(int[] arr) {
        System.out.printf("Input: %s; Output: %s\n", Arrays.toString(arr), new PermutationsII().permuteUnique(arr));
    }

    public static void main(String[] args) {
        test(new int[]{});
        test(new int[]{1,2});
        test(new int[]{1,1});
        test(new int[]{1,2,3});
        test(new int[]{1,1,3});
        test(new int[]{3,1,3});
    }
}
