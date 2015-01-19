package com.andreytim.jafar.problems.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shpolsky on 17.01.15.
 */
public class GenPermutationsIterative {

    public List<List<Integer>> permute(int[] num) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        ans.offer(new LinkedList<Integer>());
        for (int n = 0; n < num.length; n++) {
            while (n == ans.peek().size()) {
                List<Integer> l = ans.poll();
                for (int i = 0; i <= l.size(); i++) {
                    LinkedList<Integer> newL = new LinkedList<>(l.subList(0,i));
                    newL.add(num[n]);
                    newL.addAll(l.subList(i,l.size()));
                    ans.offer(newL);
                }
            }
        }
        return ans;
    }

    public static void test(int[] num) {
        System.out.printf("Input: %s; Output:\n %s\n", Arrays.toString(num),
                new GenPermutationsIterative().permute(num).toString());
    }

    public static void main(String[] args) {
        test(new int[]{1});
        test(new int[]{1, 2});
        test(new int[]{1, 2, 3});
    }
}
