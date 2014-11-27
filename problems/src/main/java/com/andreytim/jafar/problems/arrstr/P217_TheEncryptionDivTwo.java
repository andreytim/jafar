package com.andreytim.jafar.problems.arrstr;

/**
 * TopCoder:
 * Single Round Match 445 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=10509
 *
 * Created by shpolsky on 27.11.14.
 */
public class P217_TheEncryptionDivTwo {

    public String encrypt(String message) {
        char[] mapping = new char[26];
        char[] result = new char[message.length()];
        int nextCh = 0;
        for (int i = 0; i < message.length(); i++) {
            int ch = message.charAt(i) - 'a';
            if (mapping[ch] == 0) {
                mapping[ch] = (char)('a' + nextCh++);
            }
            result[i] = mapping[ch];
        }
        return new String(result);
    }

}
