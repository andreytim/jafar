package com.andreytim.jafar.problems.arrstr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * CtCI, 1.1
 *
 * Created by shpolsky on 18.11.14.
 */
public class P28_StringUniqueChars {

    // Using a set (O(n) time, O(n) space (actually it's O(1) due to finite alphabet))
    public boolean check(String str) {
        if (str == null) return false; // or throw new IllegalArgumentException();
        Set<Character> chars = new HashSet<>();
        for (char ch : str.toCharArray()) {
            if (chars.contains(ch)) return false;
            chars.add(ch);
        }
        return true;
    }

    // Using a bit mask, when string is only lower case english letters (O(n) time, O(1) space)
    public boolean checkBitMask(String str) {
        int bitMask = 0;
        for (char ch : str.toCharArray()) {
            if ((bitMask & (1 << (ch - 'a'))) != 0) return false;
            bitMask |= 1 << (ch - 'a');
        }
        return true;
    }

    // No additional data structure (O(n*log(n)) time, in-place, but with modification of input string)
    public boolean checkNoDS(String str) {
        if (str == null) return false; // or throw new IllegalArgumentException();
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] == arr[i]) return false;
        }
        return true;
    }

    public static void test(String str) {
        System.out.printf("Input: %s; Output: set=%b, bitmask=%b, noDS=%b\n", str,
                new P28_StringUniqueChars().check(str), new P28_StringUniqueChars().checkBitMask(str),
                new P28_StringUniqueChars().checkNoDS(str));
    }

    public static void main(String[] args) {
        test("aaaaa");
        test("a");
        test("asdfga");
        test("asdafg");
        test("asdfg");
    }
}
