package com.andreytim.jafar.problems.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a boolean expression consisting of the symbols 0, 1, &, /, and ^,
 * and a desired boolean result value implement a function to count
 * the number of ways of parenthesizing the expression such that it evaluates to result.
 * CtCI, 9.11
 *
 * Created by shpolsky on 30.11.14.
 */
public class P812_BooleanParenthesising {

    public static final Map<String, Integer> cache = new HashMap<>();

    public int ways(String expr, boolean result) {
        if (expr.length() == 0) return 0;
        if (expr.length() == 1) {
            if ((expr.equals("0") && !result) ||
                    (expr.equals("1") && result)) return 1;
            else return 0;
        } else {
            if (cache.containsKey(expr+result)) return cache.get(expr+result);
            int res = 0;
            for (int i = 1; i < expr.length(); i += 2) {
                char op = expr.charAt(i);
                String left = expr.substring(0,i);
                String right = expr.substring(i+1);
                if (op == '^') {
                    if (result) {
                        res += ways(left, false) * ways(right, true);
                        res += ways(left, true) * ways(right, false);
                    } else {
                        res += ways(left, false) * ways(right, false);
                        res += ways(left, true) * ways(right, true);
                    }
                } else if (op == '|') {
                    if (result) {
                        res += ways(left, false) * ways(right, true);
                        res += ways(left, true) * ways(right, false);
                        res += ways(left, true) * ways(right, true);
                    } else {
                        res += ways(left, false) * ways(right, false);
                    }
                } else {
                    if (result) {
                        res += ways(left, true) * ways(right, true);
                    } else {
                        res += ways(left, false) * ways(right, false);
                        res += ways(left, false) * ways(right, true);
                        res += ways(left, true) * ways(right, false);
                    }
                }
            }
            cache.put(expr+result, res);
            return res;
        }
    }

    public static void test(String expr, boolean result) {
        System.out.printf("Input: expr=%s, res=%b; Result: %d\n", expr, result,
                new P812_BooleanParenthesising().ways(expr, result));
    }

    public static void main(String[] args) {
        test("1^0|0|1", false);
    }

}
