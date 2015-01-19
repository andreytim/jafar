package com.andreytim.jafar.problems.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by shpolsky on 18.01.15.
 */
public class BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public Iterable<TreeNode> topSort(TreeNode root) {
        Deque<TreeNode> result = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.push(curr);
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
        return result;
    }

    public int maxPathSum(TreeNode root) {
        int result = 0;
        Map<TreeNode, Integer> maxRootPath = new HashMap<>();
        maxRootPath.put(null, 0);
        for (TreeNode node : topSort(root)) {
            int left = Math.max(maxRootPath.get(node.left), 0);
            int right = Math.max(maxRootPath.get(node.right), 0);
            maxRootPath.put(node, Math.max(left, right) + node.val);
            result = Math.max(left + right + node.val, result);
        }
        return result;
    }

}
