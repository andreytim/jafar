package com.andreytim.jafar.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Write a function that takes as input the root of a binary tree and
 * returns true or false depending on whether the tree is balanced.
 * Use O(h) additional storage, where h is the height of the tree.
 *
 * Created by shpolsky on 10.11.14.
 */
public class P51_CheckIfBtIsBalanced {

    public static class TreeNode {
        private int value;
        private TreeNode left, right;
        public TreeNode(int val) { value = val; }
    }

    public static boolean checkIfBalanced(TreeNode root) {
        if (root == null) return false;
        return Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    /**
     * We could check balance here too and eliminate redundant computation early.
     *
     * @param root
     * @return
     */
    public static int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // ---------
    // utility methods for testing
    // (copy/paste from class to class, because DS every time is implemented from scratch)
    // ---------

    public static TreeNode valueOf(String stree) {
        String[] nodes = stree.substring(1, stree.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                curr.left = (i < nodes.length && !nodes[i].equals("#")) ? new TreeNode(Integer.parseInt(nodes[i])) : null;
                i++;
                curr.right = (i < nodes.length && !nodes[i].equals("#")) ? new TreeNode(Integer.parseInt(nodes[i])) : null;
                i++;
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                i += 2;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(checkIfBalanced(valueOf("{3,9,20,#,#,15,7}")));
        System.out.println(checkIfBalanced(null));
        System.out.println(checkIfBalanced(valueOf("{1,2,5,3,6,#,#,4,#,#,#,#,#,#,#}")));
        System.out.println(checkIfBalanced(valueOf("{1,1,1}")));
        System.out.println(checkIfBalanced(valueOf("{1,1,1,1}")));
    }
}
