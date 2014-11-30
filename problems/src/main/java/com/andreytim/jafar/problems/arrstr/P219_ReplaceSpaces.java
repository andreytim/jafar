package com.andreytim.jafar.problems.arrstr;

/**
 * Replace all spaces in a string with '%20' in-place.
 * CtCI, 1.4
 *
 * Created by shpolsky on 30.11.14.
 */
public class P219_ReplaceSpaces {

    public String replace(String str, int trueLength) {
        char[] charr = str.toCharArray();
        replace(charr, trueLength);
        return new String(charr);
    }

    // Assumptions: trueLength > 0, space - one character, str.length is exactly of needed size
    public void replace(char[] str, int trueLength) {
        for (int i = trueLength-1, j = str.length-1; i >= 0; i--) { // i = 2, j = 4
            if (str[i] != ' ') {
                str[j--] = str[i]; // "a b b", j = 3
            } else {
                str[j--] = '0'; // i = 1
                str[j--] = '2';
                str[j--] = '%'; // "a%20b", j = 0, i = 1
            }
        }
    }

    private static void test(String str, int trueLength) {
        System.out.printf("Input: str=\"%s\", true_length=%d; Result: \"%s\"\n",
                str, trueLength, new P219_ReplaceSpaces().replace(str, trueLength));
    }

    public static void main(String[] args) {
        test("a b  ", 3);
        test("Mr John Smith    ", 13);
    }

}
