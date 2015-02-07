package com.andreytim.jafar.problems.leetcode;

public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\."), v2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < v1.length && j < v2.length &&
                Integer.parseInt(v1[i]) == Integer.parseInt(v2[j])) {
            i++; j++;
        }
        if (i == v1.length && j == v2.length) return 0;
        if (i == v1.length || j == v2.length) {
            while (i < v1.length && Integer.parseInt(v1[i]) == 0) i++;
            while (j < v2.length && Integer.parseInt(v2[j]) == 0) j++;
            if (i == v1.length && j == v2.length) return 0;
            return (v1.length > v2.length) ? 1 : -1;
        }
        return Integer.parseInt(v1[i]) > Integer.parseInt(v2[j]) ? 1 : -1;
    }

    private static void test(String v1, String v2) {
        System.out.printf("Input: v1=\"%s\", v2=\"%s\"; Output: %d\n", v1, v2,
                new CompareVersionNumbers().compareVersion(v1, v2));
    }

    public static void main(String[] args) {
        test("1", "0");
        test("1.0", "1");
        test("0.1", "1.1");
        test("1.1", "1.2");
        test("1.2", "13.37");
    }
}
