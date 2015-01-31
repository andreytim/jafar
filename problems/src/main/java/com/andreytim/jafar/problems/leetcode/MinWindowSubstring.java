package com.andreytim.jafar.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shpolsky on 21.01.15.
 */
public class MinWindowSubstring {

    public String minWindow(String S, String T) {
        Map<Character, Integer> sHist = new HashMap<>();
        for (char ch : T.toCharArray()) {
            if (!sHist.containsKey(ch)) sHist.put(ch, 0);
            sHist.put(ch, sHist.get(ch) + 1);
        }
        Map<Character, Integer> cHist = new HashMap<>();
        int start = -1, minStart = 0, minEnd = Integer.MAX_VALUE-1;
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (sHist.containsKey(ch)) {
                if (count < sHist.size()) {
                    if (!cHist.containsKey(ch)) cHist.put(ch, 0);
                    cHist.put(ch, cHist.get(ch)+1);
                    if (cHist.get(ch).equals(sHist.get(ch))) count++;
                }
                if (count == sHist.size()) {
                    if (start == -1 || S.charAt(start) == ch) {
                        while (++start < i) {
                            char tmp = S.charAt(start);
                            if (cHist.containsKey(tmp)) {
                                if (cHist.get(tmp).equals(sHist.get(tmp))) break;
                                else cHist.put(tmp, cHist.get(tmp) - 1);
                            }
                        }
                        if (i - start + 1 < minEnd - minStart + 1) {
                            minStart = start;
                            minEnd = i;
                        }
                    } else cHist.put(ch, cHist.get(ch)+1);
                }
            }
        }
        return minEnd == Integer.MAX_VALUE-1 ? "" : S.substring(minStart, minEnd+1);
    }

    private static void test(String S, String T) {
        System.out.printf("Input: S=%s, T=%s; Output: %s\n", S, T,
                new MinWindowSubstring().minWindow(S, T));
    }

    public static void main(String[] args) {
        test("ADOBECODEBANC", "ABC");
    }

}
