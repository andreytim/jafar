package com.andreytim.jafar.problems.treesgraphs;

/**
 * Find the first common ancestor of two nodes in a binary tree.
 * CtCI, 4.7
 *
 * Created by shpolsky on 23.11.14.
 */
public class P56_FirstCommonAncestor {

    public static class TreeNode {
        public final int value;
        public TreeNode left, right;
        public TreeNode(int val) { this.value = val; }
    }

    private boolean find(TreeNode root, int v) {
        if (root == null) return false;
        if (root.value == v) return true;
        return find(root.left, v) || find(root.right, v);
    }

    public TreeNode getFca(TreeNode root, int v1, int v2) {
        if (root == null) return null;
        boolean l_v1 = find(root.left, v1);
        boolean l_v2 = find(root.left, v2);
        boolean r_v1 = find(root.right, v1);
        boolean r_v2 = find(root.right, v2);
        if (l_v1 && l_v2 && !r_v1 && !r_v2) return getFca(root.left, v1, v2);
        else if (!l_v1 && !l_v2 && r_v1 && r_v2) return getFca(root.right, v1, v2);
        else if ((l_v1 && r_v2) || (r_v1 && l_v2)) return root;
        return null;
    }

}
