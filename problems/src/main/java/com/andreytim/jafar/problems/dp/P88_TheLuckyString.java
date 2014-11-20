package com.andreytim.jafar.problems.dp;

/**
 * Created by shpolsky on 21.11.14.
 */
public class P88_TheLuckyString {

    private int generate(char ch, int[] counts, int l) {
        if (l == 0) return 1;
        int res = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0 && ch != (char)('a'+i)) {
                counts[i]--;
                res += generate((char)('a'+i), counts, l-1);
                counts[i]++;
            }
        }
        return res;
    }

    public int count(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i)-'a']++;
        }
        return generate('0', counts, s.length());
    }

    private static void test(String s) {
        System.out.printf("Input: %s; Result: %d\n", s, new P88_TheLuckyString().count(s));
    }

    public static void main(String[] args) {
        test("ab");
        test("aaab");
        test("aabbbaa");
        test("abcdefghij");
        test("a");
    }

}
