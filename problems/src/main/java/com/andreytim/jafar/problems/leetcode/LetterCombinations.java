package com.andreytim.jafar.problems.leetcode;

import java.util.*;

public class LetterCombinations {

    private static final Map<Character, String> mapping = new HashMap<>();
    static {
        mapping.put('2', "abc"); mapping.put('3', "def"); mapping.put('4', "ghi");
        mapping.put('5', "jkl"); mapping.put('6', "mno"); mapping.put('7', "pqrs");
        mapping.put('8', "tuv"); mapping.put('9', "wxyz");
    }

    public interface Solver {
        public List<String> letterCombinations(String digits);
    }

    private static final Solver BACKTRACKING = new Solver() {
        private void helper(String digits, StringBuilder buff, List<String> res) {
            if (buff.length() == digits.length()) res.add(buff.toString());
            else {
                for (char ch : mapping.get(digits.charAt(buff.length())).toCharArray()) {
                    buff.append(ch);
                    helper(digits, buff, res);
                    buff.setLength(buff.length()-1);
                }
            }
        }
        public List<String> letterCombinations(String digits) {
            List<String> res = new LinkedList<>();
            helper(digits, new StringBuilder(), res);
            return res;
        }
    };

    private static final Solver ITERATIVE = new Solver() {
        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) return Arrays.asList("");
            List<String> res = new LinkedList<>();
            char[] buf = new char[digits.length()];
            for (int k = 0; k < buf.length; k++)
                buf[k] = mapping.get(digits.charAt(k)).charAt(0);
            int[] idx = new int[digits.length()];
            int i = 0;
            while (i < digits.length()) {
                res.add(new String(buf));
                i = 0;
                while (i < digits.length()) {
                    idx[i] = (idx[i] + 1) % mapping.get(digits.charAt(i)).length();
                    buf[i] = mapping.get(digits.charAt(i)).charAt(idx[i]);
                    if (idx[i] != 0) break;
                    i++;
                }
            }
            return res;
        }
    };

    private static void test(Solver solver, String name) {
        long ts = System.currentTimeMillis();
        solver.letterCombinations("222222222");
        solver.letterCombinations("23456789");
        solver.letterCombinations("");
        solver.letterCombinations("2");
        solver.letterCombinations("99999999999");
        solver.letterCombinations("5356435634");
        System.out.printf("Solver: %s, time: %d ms\n", name, System.currentTimeMillis() - ts);
    }

    public static void main(String[] args) {
        test(BACKTRACKING, "backtracking");
        test(ITERATIVE, "iterative");
    }

}
