package com.andreytim.jafar.problems.leetcode;

import java.util.*;

/**
 * Created by shpolsky on 31.01.15.
 */
public class WordBreakII {

    private static final Map<String, List<String>> cache = new HashMap<>();

    private boolean containsSuffix(Set<String> dict, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (dict.contains(str.substring(i))) return true;
        }
        return false;
    }

    public List<String> wordBreak(String s, Set<String> dict) {
        if (cache.containsKey(s)) return cache.get(s);
        List<String> result = new LinkedList<>();
        if (dict.contains(s)) result.add(s);
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0,i), right = s.substring(i);
            if (dict.contains(left) && containsSuffix(dict, right)) {
                for (String ss : wordBreak(right, dict)) {
                    result.add(left + " " + ss);
                }
            }
        }
        cache.put(s, result);
        return result;
    }

    private static void test(String s, String[] dict) {
        System.out.printf("Input: s=%s; dict=%s\n", s, Arrays.toString(dict));
        Set<String> ds = new HashSet<>();
        for (String str : dict) ds.add(str);
        List<String> res = new WordBreakII().wordBreak(s, ds);
        System.out.println("Output:");
        for (String r : res) {
            System.out.println(r);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test("a", new String[]{"a"});
    }

}
