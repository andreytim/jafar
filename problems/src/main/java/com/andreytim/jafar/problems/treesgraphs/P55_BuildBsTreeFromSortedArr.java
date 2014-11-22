package com.andreytim.jafar.problems.treesgraphs;

import java.util.*;

/**
 * Given a sorted (increasing order) array with unique integer elements,
 * write an algorithm to create a binary search tree with minimal height.
 * CtCI, 4.3
 *
 * Created by shpolsky on 22.11.14.
 */
public class P55_BuildBsTreeFromSortedArr {

    public static class BstNode {
        public final int value;
        public BstNode left, right;
        public BstNode(int value) { this(value, null, null); }
        public BstNode(int value, BstNode left, BstNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private BstNode generateBsTree(int[] arr, int lo, int hi) {
        if (hi < lo) return null;
        int mid = lo + (hi-lo)/2;
        BstNode root = new BstNode(arr[mid]);
        root.left = generateBsTree(arr, lo, mid-1);
        root.right = generateBsTree(arr, mid+1, hi);
        return root;
    }

    public BstNode generateBsTree(int[] arr) {
        if (arr == null && arr.length < 1) {
            throw new IllegalArgumentException();
        }
        return generateBsTree(arr, 0, arr.length-1);
    }

    // utils

    public static void printTree(BstNode root) {
        Map<BstNode, Integer> level = new HashMap<>();
        level.put(root, 0);
        Queue<BstNode> queue = new LinkedList<>();
        queue.offer(root);
        int prevLevel = 0;
        while (!queue.isEmpty()) {
            BstNode curr = queue.poll();
            if (level.get(curr) != prevLevel) {
                System.out.println();
            }
            System.out.print(curr.value + " ");
            prevLevel = level.get(curr);
            if (curr.left != null) {
                level.put(curr.left, level.get(curr) + 1);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                level.put(curr.right, level.get(curr) + 1);
                queue.offer(curr.right);
            }
        }
        System.out.println();
    }

    public static void test(int[] arr) {
        System.out.printf("Input: %s; Result: \n", Arrays.toString(arr));
        printTree(new P55_BuildBsTreeFromSortedArr().generateBsTree(arr));
    }

    public static void main(String[] args) {
        test(new int[]{ 1 });
        test(new int[]{ 1, 2 });
        test(new int[]{ 1, 2, 3 });
        test(new int[]{ 1, 2, 3, 5, 7, 9, 10, 234, 543, 1000, 23432 });
    }

}
