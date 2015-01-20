package com.andreytim.jafar.problems.leetcode;

/**
 * Created by shpolsky on 20.01.15.
 */
public class ReverseWordsInAString {

    public char[] reverse(char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
        return arr;
    }

    public String reverseWords(String s) {
        char[] str = reverse(s.toCharArray(), 0, s.length()-1);
        int start = 0, end = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ' ') {
                str[end++] = str[i];
            } else if (i > 0 && str[i-1] != ' ') {
                reverse(str, start, end-1);
                str[end++] = ' ';
                start = end;
            }
        }
        reverse(str, start, end-1);
        return new String(str, 0, end);
    }

    private static void test(String str) {
        System.out.printf("Input: \"%s\"; Output: \"%s\"\n", str, new ReverseWordsInAString().reverseWords(str));
    }

    public static void main(String[] args) {
        test("the sky is blue");
        test("");
        test("t");
        test("ta");
        test("   the sky is blue    ");
    }
}
