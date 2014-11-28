package com.andreytim.jafar.problems.treesgraphs;

import java.util.*;

/**
 * TopCoder:
 * Single Round Match 570 Round 1 - Division II, Level Three
 * http://community.topcoder.com/stat?c=problem_statement&pm=12426
 * Basically - calc number of all subtrees of a given tree.
 *
 * Created by shpolsky on 19.11.14.
 */
public class P54_CentaurCompanyDiv2 {

    private Map<Integer, Set<Integer>> initGraph(int[] a, int[] b) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!graph.containsKey(a[i])) {
                graph.put(a[i], new HashSet<Integer>());
            }
            if (!graph.containsKey(b[i])) {
                graph.put(b[i], new HashSet<Integer>());
            }
            graph.get(a[i]).add(b[i]);
            graph.get(b[i]).add(a[i]);
        }
        return graph;
    }

    private Iterable<Integer> getBacktrackDfsOrder(Map<Integer, Set<Integer>> graph) {
        Deque<Integer> order = new LinkedList<>();
        Deque<Integer> dfs = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        dfs.push(1); visited.add(1);
        while (!dfs.isEmpty()) {
            Integer curr = dfs.pop();
            order.push(curr);
            for (Integer next : graph.get(curr)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    dfs.push(next);
                }
            }
        }
        return order;
    }

    public long count(int[] a, int[] b) {
        Map<Integer, Set<Integer>> graph = initGraph(a, b);
        long[] numST = new long[graph.size()+1];
        long[] numRootedST = new long[graph.size()+1];
        for (Integer v : getBacktrackDfsOrder(graph)) {
            long st = 1, rst = 1;
            for (Integer n : graph.get(v)) {
                st += numST[n] + rst*numRootedST[n];
                rst += rst*numRootedST[n];
            }
            numST[v] = st;
            numRootedST[v] = rst;
        }
        return numST[1] + 1; // adding one as empty tree is also considered as a sub-tree in this case
    }

    public static void test(int[] a, int[] b) {
        System.out.printf("Input:\na=%s\nb=%s\n", Arrays.toString(a), Arrays.toString(b));
        System.out.printf("Output: %d\n", new P54_CentaurCompanyDiv2().count(a, b));
    }

    public static void main(String[] args) {
        test(new int[]{1}, new int[]{2});
        test(new int[]{2, 2}, new int[]{1, 3});
        test(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{2,3,4,5,6,7,8,9,10});
        test(
                new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,
                        28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51}
        );
        test(new int[]{10,7,2,5,6,2,4,9,7}, new int[]{8,10,10,4,1,6,2,2,3});
    }

}
