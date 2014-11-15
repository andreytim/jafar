package com.andreytim.jafar.problems.arrstr;

import java.util.HashSet;
import java.util.Set;

/**
 * TopCoder:
 * Single Round Match 417 Round 1 - Division I, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=9943
 *
 * Created by shpolsky on 15.11.14.
 */
public class P26_TemplateMatching {

    private int prefixMatch(String str, String prefix) {
        Set<String> prefixParts = new HashSet<>();
        for (int i = 0; i < prefix.length(); i++) {
            prefixParts.add(prefix.substring(i, prefix.length()));
        }
        for (int i = str.length(); i > 0; i--) {
            if (prefixParts.contains(str.substring(0, i))) {
                return i;
            }
        }
        return 0;
    }

    public String bestMatch(String text, String prefix, String suffix) {
        String result = new String(new char[]{text.charAt(text.length()-1)});
        int maxPrm = 0;
        int maxBm = 0;
        for (int i = 0; i < text.length() - maxPrm; i++) {
            for (int j = i+1; j <= text.length(); j++) {
                String currStr = text.substring(i,j);
                int prm = prefixMatch(currStr, prefix);
                int sfm = prefixMatch(suffix, currStr);
                int bm = prm + sfm;
                if (bm > maxBm || (bm == maxBm && prm > maxPrm) ||
                        (bm == maxBm && prm == maxPrm && currStr.compareTo(result) < 0)) {
                    maxPrm = prm;
                    maxBm = bm;
                    result = currStr;
                }
            }
        }
        return result;
    }

    public static void test(String text, String prefix, String suffix) {
        System.out.printf("Input: test=%s, prefix=%s, suffix=%s; Output: %s\n",
                text, prefix, suffix, new P26_TemplateMatching().bestMatch(text, prefix, suffix));
    }

    public static void main(String[] args) {
        test("some", "some", "some");
        test("s", "s", "s");
        test("some", "so", "me");
        test("some", "so", "e");
        test("some", "so", "m");
        test("asdfsomeasdf", "so", "me");
        test("sadfsofsmesadf", "dsfso", "fsofsmes");
        test("havka", "eto", "papstvo");
        test("a a a a a a", "a a", "a");
        test("mississippi", "promise", "piccolo");
    }

}
