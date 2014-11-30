package com.andreytim.jafar.problems.treesgraphs;

/**
 * You have two very large binary trees: T1, with millions of nodes, and T2, with
 * hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.
 * CtCI, 4.8
 *
 * Created by shpolsky on 30.11.14.
 */
public class P510_CheckIfSubtree {

    public static class TreeNode {
        public final int val;
        public TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
    }

    private boolean compare(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return root1.val == root2.val &&
                compare(root1.left, root2.left) && compare(root1.right, root2.right);
    }

    public boolean check(TreeNode bigTree, TreeNode subtree) {
        if (bigTree == null) return false;
        if (subtree == null) return true;
        return compare(bigTree, subtree) ||
                check(bigTree.left, subtree) || check(bigTree.right, subtree);
    }

}
