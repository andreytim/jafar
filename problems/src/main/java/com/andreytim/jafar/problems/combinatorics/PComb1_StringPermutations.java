package com.andreytim.jafar.problems.combinatorics;

/**
 * Created by shpolsky on 28.10.14.
 */
public class PComb1_StringPermutations {

    public static void permute(String str) {
        permuteRecursion(str.toCharArray(), new boolean[str.length()], new StringBuilder());
    }

    private static void permuteRecursion(char[] chars, boolean[] bits, StringBuilder currString) {
        if (currString.length() == chars.length) {
            System.out.println(currString.toString());
        } else {
            for (int i = 0; i < chars.length; i++) {
                if (!bits[i]) {
                    bits[i] = true;
                    currString.append(chars[i]);
                    permuteRecursion(chars, bits, currString);
                    currString.setLength(currString.length() - 1);
                    bits[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Permutations for \"perm\":");
        permute("perm");
    }

}
