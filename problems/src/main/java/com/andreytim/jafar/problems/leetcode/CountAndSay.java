package com.andreytim.jafar.problems.leetcode;

/**
 * Created by shpolsky on 20.01.15.
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder newRes = new StringBuilder();
            int count = 0;
            char prev = '#';
            for (char ch : res.toCharArray()) {
                if (prev != ch && count > 0) {
                    newRes.append(String.valueOf(count));
                    newRes.append(prev);
                    count = 0;
                }
                count++;
                prev = ch;
            }
            newRes.append(String.valueOf(count));
            newRes.append(prev);
            res = newRes.toString();
        }
        return res;
    }

    private static void test(int n) {
        System.out.printf("Input: %d; Result: %s\n", n, new CountAndSay().countAndSay(n));
    }

    public static void main(String[] args) {
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
        test(6);
        test(7);
    }

}
