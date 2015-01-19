package com.andreytim.jafar.problems.leetcode;

import java.util.*;

/**
 * Created by shpolsky on 07.01.15.
 */
public class StrConcatOfAllWords {

    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<>();
        int size = L[0].length();
        if (L.length == 0 || L[0].isEmpty() || L[0].length() > S.length()) return result;
        Map<String, Integer> hist = new HashMap<>();
        for (String w : L) {
            hist.put(w, !hist.containsKey(w) ? 1 : hist.get(w)+1);
        }
        for (int i = 0; i+size*L.length <= S.length(); i++) {
            if (hist.containsKey(S.substring(i,i+size))) {
                Map<String, Integer> currHist = new HashMap<>();
                for (int j = 0; j < L.length; j++) {
                    String word = S.substring(i + j * size, i + (j + 1) * size);
                    currHist.put(word, !currHist.containsKey(word) ? 1 : currHist.get(word)+1);
                }
                if (currHist.equals(hist)) result.add(i);
            }
        }
        return result;
    }

    private static void test(String s, String[] dict) {
        System.out.printf("Input: s=%s, dict=%s; Result: %s\n", s, Arrays.toString(dict),
                new StrConcatOfAllWords().findSubstring(s, dict));
    }

    public static void main(String[] args) {
        test("a",
                new String[]{ "a","a" });
        test("barfoothefoobarman",
                new String[]{ "foo","bar" });
        test("lingmindraboofooowingdingbarrwingmonkeypoundcake",
                new String[]{ "fooo","barr","wing","ding","wing" });
    }

}
