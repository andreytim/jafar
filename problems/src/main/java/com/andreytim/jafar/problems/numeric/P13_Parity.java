package com.andreytim.jafar.problems.numeric;

/**
 * Compute the parity of a very large number of nonnegative 64-bit integers. (Aziz, P5.1)
 * Caching approach when we precompute the parity for all 16-bit integers.
 *
 * What if negative numbers are allowed?
 * 1. We need to shift number by (Short.MAX_VALUE + 1) before put it into the cache or check.
 *
 * Created by shpolsky on 04.11.14.
 */
public class P13_Parity {

  private static final boolean[] PARITY_CACHE = new boolean[1024*64];
  static {
    for (int i = 0; i < 1024*64; i++) {
      PARITY_CACHE[i] = straightParity(i);
    }
  } 

  private static boolean straightParity(int n) {
    boolean parity = false;
    while (n != 0) {
      if ((n & 1) == 1) parity = !parity;
      n >>>= 1;
    }
    return parity;
  }

  private static boolean cachedParity(long n) {
    boolean parity = false;
    // System.out.printf("n=%d, nbits=%s, ", n, Long.toBinaryString(n));
    while (n != 0) {
      parity ^= PARITY_CACHE[(int) n & 0xFFFF];
      n >>>= 16; // unsigned right shift is crucial here
    }
    // System.out.printf("parity=%b\n", parity);
    return parity;
  }

  public static boolean parity(long[] arr) {
    boolean parity = false;
    for (long n : arr) {
      parity = cachedParity(n);
    }
    return parity;
  }

  public static void main(String[] args) {
    System.out.println(parity(new long[]{ 0, 0, 0, 0, 0, 0 }));
    System.out.println(parity(new long[]{ 1, 1, 1, 1, 1 }));
    System.out.println(parity(new long[]{ 2, 2, 2, 2 }));
    System.out.println(parity(new long[]{ 2, 2, 2, 2, 2 }));
    System.out.println(parity(new long[]{ Long.MAX_VALUE }));
    System.out.println(parity(new long[]{ Long.MIN_VALUE }));
    System.out.println(parity(new long[]{ 42135 }));
    System.out.println(parity(new long[]{ Long.MAX_VALUE, 42135 }));
  }
}
