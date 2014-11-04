package com.andreytim.jafar.problems.arrstr;

import java.util.regex.Pattern;

/**
 * Check if the string is a valid number.
 * Clarifying questions:
 * - could the number be negative? (it could)
 * - is the number of base-10 or it's binary or hexademical? (decimal)
 * - is it the floating point number? (yes, it could be)
 * - should we consider the number range or size? (no, any proper number is sufficient)
 * - could it be in exponential notation? (yes)
 * - what should we do with the spaces before and after the number? (they are allowable and shouldn't be considered)
 * - can we use a regular expression? (let's try both variants)
 * - can the leading zero for floating points number be ommited? (yes)
 * - could it be leading zeros which shouldn't be considered? (yes)
 * - can the number be like "543."? (yes)
 * - can the plus sign be putted at the beginning? after the exponent? (yes)
 * - can the string be null or empty? (yes)
 *
 * Created by shpolsky on 21.10.14.
 */
public class P24_ValidNumber {

    private static final Pattern NUM_FORMAT =
            Pattern.compile("\\s*[-+]?(\\d+\\.?|\\d*\\.\\d+)(e[-+]?\\d+)?\\s*");

    public static boolean isNumberRegex(String s) {
        if (s == null || s.isEmpty()) return false;
        return NUM_FORMAT.matcher(s).matches(); // s.matches(regex); is also possible
    }

    public static boolean isNumberIterative(String s) {
        if (s == null || s.isEmpty()) return false;
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length && Character.isWhitespace(chars[i])) i++; // skip all front spaces
        if (i < chars.length && (chars[i] == '+' || chars[i] == '-')) i++; // skip +/- if it's there
        boolean dotFound = false;
        boolean expFound = false;
        boolean digitFound = false;
        while (i < chars.length && !Character.isWhitespace(chars[i])) {
            char ch = chars[i++];
            if (ch == '.') {
                if (dotFound || expFound) return false; // if we've already seen dot or exponent, that's wrong
                dotFound = true;
            } else if (ch - '0' >= 0 && ch - '0' <= 9) {
                digitFound = true;
            } else if (ch == 'e' || ch == 'E') { // we found exponent
                if (i == chars.length || !digitFound) return false; // check if it's not the last symbol and there were digits before
                if (chars[i] == '+' || chars[i] == '-') i++; // skip +/- if it's going after the exponent
                if (i == chars.length) return false; // check if +/- is not the last symbol
                expFound = true;
            } else { // if the symbol is not 0-9, exponent or dot, then the string is invalid
                return false;
            }
        }
        if (!digitFound) return false; // if there were no digits, that's wrong
        while (i < chars.length && Character.isWhitespace(chars[i])) i++; // skip all back spaces
        return i == chars.length;
    }

    public static void main(String[] args) {
        test("0");
        test("");
        test(null);
        test(" 0.1 ");
        test("abc");
        test("1 a");
        test("2e10");
        test(".1231");
        test("423.1231");
        test("000412.1231");
        test("000412..1231");
        test("-.1231");
        test("-321");
        test("-asf.1231");
        test("e9");
        test("3.");
        test("+.8");
    }

    private static void test(String numberStr) {
        System.out.printf("%s: regex=%b, iter=%b\n",
                numberStr, isNumberRegex(numberStr), isNumberIterative(numberStr));
    }

}
