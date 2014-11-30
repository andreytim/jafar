package com.andreytim.jafar.problems.numeric;

/**
 * Write a program to swap odd and even bits in an integer with as few instructions as possible
 * (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and soon).
 * CtCI, 5.6
 *
 * Created by shpolsky on 30.11.14.
 */
public class P111_SwapConsecutive {

    public int swapConsecutiveBook(int n) {
        return (((n & 0xAAAAAAAA) >> 1) | ((n & 0x55555555) << 1));
    }

    public int swapConsecutive(int n) {
        n = ~n;
        for (int shift = 0; shift < 32; shift += 2)
            if (((n >> shift) & 1) == ((n >> (shift+1)) & 1)) n ^= (3 << shift);
        return n;
    }

    private static void test(int n) {
        System.out.printf("Input: %d=%s; Result: mine=%s, book=%s\n", n, Integer.toBinaryString(n),
                Integer.toBinaryString(new P111_SwapConsecutive().swapConsecutive(n)),
                Integer.toBinaryString(new P111_SwapConsecutive().swapConsecutiveBook(n)));
    }

    public static void main(String[] args) {
        test(5);
        test(234);
        test(234213412);
    }
}
