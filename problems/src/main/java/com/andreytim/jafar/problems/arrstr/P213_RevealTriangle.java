package com.andreytim.jafar.problems.arrstr;

import java.util.Arrays;

/**
 * TopCoder:
 * Single Round Match 404 Round 1 - Division I, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=8215
 *
 * Created by shpolsky on 22.11.14.
 */
public class P213_RevealTriangle {

    private String[] revealString(int si, String[] tr) {
        if (si == tr.length-1) return tr;
        tr = revealString(si+1, tr);
        int idx = 0;
        String str = tr[si];
        while (str.charAt(idx) == '?') idx++;
        char[] newStr = new char[str.length()];
        newStr[idx] = str.charAt(idx);
        for (int i = idx; i > 0; i--) {
            int x = tr[si+1].charAt(i-1);
            int y = newStr[i];
            if (x - y >= 0) newStr[i-1] = (char)(x - y + '0');
            else newStr[i-1] = (char)(x + 10 - y + '0');
        }
        for (int i = idx; i < str.length()-1; i++) {
            int x = tr[si+1].charAt(i);
            int y = newStr[i];
            if (x - y >= 0) newStr[i+1] = (char)(x - y + '0');
            else newStr[i+1] = (char)(x + 10 - y + '0');
        }
        tr[si] = new String(newStr);
        return tr;
    }

    public String[] calcTriangle(String[] questionMarkTriangle) {
        return revealString(0, questionMarkTriangle);
    }

    public static void test(String[] arr) {
        System.out.printf("Input: %s; Result: %s\n", Arrays.toString(arr),
                Arrays.toString(new P213_RevealTriangle().calcTriangle(arr)));
    }

    public static void main(String[] args) {
        test(new String[]{"4??", "?2", "1"});
    }

}
