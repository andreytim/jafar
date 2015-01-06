package com.andreytim.jafar.problems.hashtables;

import java.util.*;

/**
 * Created by shpolsky on 06.01.15.
 */
public class P97_WordBreakLeetcode {

    private static final Map<String, Boolean> cache = new HashMap<>();

    public boolean wordBreak(String s, Set<String> dict) {
        if (dict.contains(s)) return true;
        if (cache.containsKey(s)) return cache.get(s);
        for (int i = 1; i < s.length(); i++) {
            if (dict.contains(s.substring(0,i)) && wordBreak(s.substring(i), dict)) {
                cache.put(s, true);
                return true;
            }
        }
        cache.put(s, false);
        return false;
    }

    private static void test(String word, String[] dict) {
        System.out.printf("Input: word=%s, dict=%s; Result: %b", word, Arrays.toString(dict),
                new P97_WordBreakLeetcode().wordBreak(word, new HashSet<>(Arrays.asList(dict))));
    }

    public static void main(String[] args) {
        test("aaaaaaa", new String[]{ "aaaa","aaa" });
    }

}
