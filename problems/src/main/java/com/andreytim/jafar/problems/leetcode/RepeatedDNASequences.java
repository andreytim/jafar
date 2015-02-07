package com.andreytim.jafar.problems.leetcode;

import java.util.*;

public class RepeatedDNASequences {

    private static final Map<Character, Integer> alph = new HashMap<>();
    static {
        alph.put('A',0); alph.put('C',1); alph.put('G',2); alph.put('T',3);
    }
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) return Collections.emptyList();
        Set<String> res = new HashSet<>();
        Set<Integer> hashes = new HashSet<>();
        for (int i = 0, rollingHash = 0; i < s.length(); i++) {
            if (i < 10) rollingHash = alph.size()*rollingHash + alph.get(s.charAt(i));
            else {
                rollingHash -= ((int)Math.pow(alph.size(), 9)) * alph.get(s.charAt(i-10));
                rollingHash = alph.size() * rollingHash + alph.get(s.charAt(i));
            }
            if (i > 8 && !hashes.add(rollingHash)) res.add(s.substring(i-9,i+1));
        }
        return new ArrayList<>(res);
    }

    private static void test(String s) {
        System.out.printf("Input: %s; Output: %s\n", s, new RepeatedDNASequences().findRepeatedDnaSequences(s));
    }

    public static void main(String[] args) {
        test("AAAAAAAAAA");
        test("AAAAAAAAAAA");
        test("GAGAGAGAGAGA");
    }

}
