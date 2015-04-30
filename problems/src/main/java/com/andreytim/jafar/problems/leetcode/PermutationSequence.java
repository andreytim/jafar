package com.andreytim.jafar.problems.leetcode;

/**
 * Created by tim on 29/04/15.
 */
public class PermutationSequence {

    private void swap(int[] a, int i, int j) {
        if (a[i] != a[j]) {
            a[i] ^= a[j];
            a[j] ^= a[i];
            a[i] ^= a[j];
        }
    }

    private void reverse(int[] a, int s, int e) {
        for (int i = s, j = e; i < j; i++, j--) swap(a, i, j);
    }

    private void nextPerm(int[] p) {
        int i = p.length-1;
        while (i > 0 && p[i-1] >= p[i]) i--;
        if (i > 0) {
            int j = i, min = j;
            while (++j < p.length) if (p[j] < p[min] && p[j] > p[i-1]) min = j;
            swap(p, i-1, min);
        }
        reverse(p, i, p.length-1);
    }

    private int fact(int n) {
        return (n < 2) ? 1 : n*fact(n-1);
    }

    public String getPermutation(int n, int k) {
        int[] p = new int[n];
        k = (k-1) % fact(n) + 1;
        for (int i = 0; i < n; i++) p[i] = i+1;
        while (--k > 0) nextPerm(p);
        StringBuilder res = new StringBuilder();
        for (int a : p) res.append(a);
        return res.toString();
    }

    private static void test(int n, int k) {
        System.out.printf("Input: %d, %d; Output: \"%s\"\n", n, k, new PermutationSequence().getPermutation(n,k));
    }

    public static void main(String[] args) {
        test(1,1);
        test(1,10);
        test(2,1);
        test(2,2);
        test(2,3);
        test(3,1);
        test(3,2);
        test(3,3);
        test(3,4);
        test(3,5);
        test(3,6);
        test(3,7);
        test(4,2);
        test(4,10);
    }
}
