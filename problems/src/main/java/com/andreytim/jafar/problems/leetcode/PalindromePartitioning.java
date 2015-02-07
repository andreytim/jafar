package com.andreytim.jafar.problems.leetcode;

import java.util.*;

public class PalindromePartitioning {

    private final Map<String, List<List<String>>> cache = new HashMap<>();

    private boolean pal(String s) {
        for (int i = 0, j = s.length()-1; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j)) return false;
        return true;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.isEmpty()) return res;
        if (cache.containsKey(s)) return cache.get(s);
        if (pal(s)) res.add(Arrays.asList(s));
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0,i);
            if (pal(left)) {
                List<List<String>> right = partition(s.substring(i));
                for (List<String> r : right) {
                    List<String> p = new ArrayList<>();
                    p.add(left); p.addAll(r);
                    res.add(p);
                }
            }
        }
        cache.put(s, res);
        return res;
    }

    private static void test(String s) {
        System.out.printf("Input: %s; Output: %s\n", s, new PalindromePartitioning().partition(s).toString());
    }

    public static void main(String[] args) {
        test("aaabaa");
    }
}
