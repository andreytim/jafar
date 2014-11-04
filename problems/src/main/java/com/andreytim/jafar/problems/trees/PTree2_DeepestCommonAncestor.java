package com.andreytim.jafar.problems.trees;

/**
 * Created by shpolsky on 28.10.14.
 */
public class PTree2_DeepestCommonAncestor {

    public static BstNode deepestCommonAncestorBST(BstNode root, int v1, int v2) {
        if (root == null || (root.value == v1 || root.value == v2)) return root;
        if (v1 < root.value && v2 > root.value) return root;
        else if (v1 < root.value) return deepestCommonAncestorBST(root.left, v1, v2);
        else return deepestCommonAncestorBST(root.right, v1, v2);
    }

    public static int deepestCommonAncestor(BstNode root, int v1, int v2) {
        if (root.value == v1 || root.value == v2) return root.value;
        if (root.left != null && find(root.left, v1) && find(root.left, v2)) {
            return deepestCommonAncestor(root.left, v1, v2);
        } else if (root.right != null && find(root.right, v1) && find(root.right, v2)) {
            return deepestCommonAncestor(root.right, v1, v2);
        } else {
            return root.value;
        }
    }

    private static boolean find(BstNode root, int value) {
        if (root == null) return false;
        if (root.value == value) return true;
        return find(root.left, value) || find(root.right, value);
    }

}
