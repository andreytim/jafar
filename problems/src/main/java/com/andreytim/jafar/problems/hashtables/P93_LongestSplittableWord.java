package com.andreytim.jafar.problems.hashtables;

import java.util.*;

/**
 * Given a list of words, write a program to find the longest word made of other words in the list.
 * CtCI, 18.7
 *
 * Created by shpolsky on 22.11.14.
 */
public class P93_LongestSplittableWord {

    private static final Map<String, Boolean> CACHE = new HashMap<>();

    private boolean isSplittable(String word, Set<String> dict) {
        if (CACHE.containsKey(word)) {
            return CACHE.get(word);
        }
        for (int i = 1; i < word.length(); i++) {
            String leftSubstr = word.substring(0,i);
            String rightSubstr = word.substring(i);
            if (dict.contains(leftSubstr) &&
                    (dict.contains(rightSubstr) || isSplittable(rightSubstr, dict))) {
                CACHE.put(word, true);
                return true;
            }
        }
        CACHE.put(word, false);
        return false;
    }

    public String findLongestSplittable(String[] words) {
        Arrays.sort(words, Collections.reverseOrder());
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (isSplittable(word, dict)) {
                return word;
            }
        }
        return "";
    }

    public static void test(String[] words) {
        System.out.printf("Input: %s; Result: %s\n", Arrays.toString(words),
                new P93_LongestSplittableWord().findLongestSplittable(words));
    }

    public static void main(String[] args) {
        test(new String[]{ "cat", "dog", "catdog", "catodog" });
        test(new String[]{ "cat", "banana", "dog", "nana", "walker", "walkerbananado", "dogwalkernana" });
    }

}
