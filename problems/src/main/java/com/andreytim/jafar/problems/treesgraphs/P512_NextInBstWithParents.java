package com.andreytim.jafar.problems.treesgraphs;

/**
 * Write an algorithm to find the 'next' node (i.e., in-order successor) of a given node in
 * a binary search tree. You may assume that each node has a link to its parent.
 * CtCI, 4.6
 *
 * Created by shpolsky on 02.12.14.
 */
public class P512_NextInBstWithParents {

    public static class TreeNode {
        public final int value;
        public TreeNode left, right, parent;
        public TreeNode(int value) { this.value = value; }
    }

    public TreeNode next(TreeNode node) {
        if (node == null) return null;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) node = node.left;
            return node;
        } else {
            while (node.parent != null && node.parent.right == node) {
                node = node.parent;
            }
            return node.parent;
        }
    }

}
