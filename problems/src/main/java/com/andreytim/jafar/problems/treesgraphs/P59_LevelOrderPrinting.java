package com.andreytim.jafar.problems.treesgraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root node r of a binary tree, print all the keys and levels at r and its descendants.
 * The nodes should be printed in order of their level. You cannot use recursion.
 * You may use a single queue, and constant additional storage.
 *
 * Created by shpolsky on 06.11.14.
 */
public class P59_LevelOrderPrinting {

    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { value = val; }
    }

    public static void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != root) System.out.print(" ");
            System.out.print(curr.value);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        System.out.println();
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
        printLevelOrder(valueOf("{3,9,20,#,#,15,7}"));
        printLevelOrder(null);
        printLevelOrder(valueOf("{1,2,#,3,#,#,#,4,#,#,#,#,#,#,#}"));

    }
}
