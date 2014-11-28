package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * TopCoder:
 * Single Round Match 366 Round 1 - Division II, Level One
 * http://community.topcoder.com/stat?c=problem_statement&pm=8171
 *
 * Created by shpolsky on 27.11.14.
 */
public class P713_SerialNumbers {

    public String[] sortSerials(String[] serialNumbers) {
        Arrays.sort(serialNumbers, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() != s2.length()) return s1.length() - s2.length();
                else {
                    int sum1 = 0, sum2 = 0;
                    for (int i = 0; i < s1.length(); i++) {
                        if (Character.isDigit(s1.charAt(i))) sum1 += (s1.charAt(i) - '0');
                        if (Character.isDigit(s2.charAt(i))) sum2 += (s2.charAt(i) - '0');
                    }
                    if (sum1 != sum2) {
                        return sum1 - sum2;
                    } else {
                        return s1.compareTo(s2);
                    }
                }
            }
        });
        return serialNumbers;
    }

    public static void test(String ... serialNumbers) {
        System.out.printf("Input: %s;\nResult: %s\n", Arrays.toString(serialNumbers),
                Arrays.toString(new P713_SerialNumbers().sortSerials(serialNumbers)));
    }

    public static void main(String[] args) {
        test("3UH6TEJOAILR1KQNEJ2E7L2BVC", "PI3GISQAJODABD4NIC9NPZ5YBX");
    }

}
