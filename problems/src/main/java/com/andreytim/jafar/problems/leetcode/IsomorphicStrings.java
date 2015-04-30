package com.andreytim.jafar.problems.leetcode;

/**
 * Created by tim on 29/04/15.
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256], m2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (m1[s.charAt(i)] == 0 && m2[t.charAt(i)] == 0) {
                m1[s.charAt(i)] = t.charAt(i);
                m2[t.charAt(i)] = s.charAt(i);
            }
            if (m1[s.charAt(i)] != t.charAt(i) &&
                    m2[t.charAt(i)] != s.charAt(i)) return false;
        }
        return true;
    }

    private static void test(String s1, String s2) {
        System.out.printf("Input: %s, %s; Output: %b\n", s1, s2, new IsomorphicStrings().isIsomorphic(s1, s2));
    }

    public static void main(String[] args) {
        test("ab", "aa");
        test("", "");
        test("ab", "at");
        test("egg", "add");
        test("foo", "bar");
        test("paper", "title");
    }

}
