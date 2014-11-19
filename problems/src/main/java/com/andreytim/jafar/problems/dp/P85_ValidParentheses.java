package com.andreytim.jafar.problems.dp;

/**
 * Print all valid (i.e., properly opened and closed) combinations of n-pairs of parentheses.
 * CtCI, 9.6
 *
 * Created by shpolsky on 20.11.14.
 */
public class P85_ValidParentheses {

    private void printRecursion(int co, int cc, StringBuilder result) {
        if (co < 0 || cc < 0 || co > cc) return;
        if (cc == 0) {
            System.out.print(result.toString() + "; ");
        } else {
            result.append('(');
            printRecursion(co-1, cc, result);
            result.setLength(result.length()-1);
            result.append(')');
            printRecursion(co, cc-1, result);
            result.setLength(result.length()-1);
        }
    }

    public void printCombs(int n) {
        if (n < 1) return;
        printRecursion(n, n, new StringBuilder());
        System.out.println();
    }

    public static void main(String[] args) {
        new P85_ValidParentheses().printCombs(3);
        new P85_ValidParentheses().printCombs(5);
        new P85_ValidParentheses().printCombs(8);
        new P85_ValidParentheses().printCombs(10);
    }

}
