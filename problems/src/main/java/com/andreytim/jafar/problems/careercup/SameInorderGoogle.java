package com.andreytim.jafar.problems.careercup;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tim on 29/04/15.
 */
public class SameInorderGoogle {

    private static class TreeNode {
        public final int val;
        public TreeNode left, right;
        public TreeNode(int val) { this(val, null, null); }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        };
    }

    private static class InOrderIterator implements Iterator<TreeNode> {
        private TreeNode curr;
        private Deque<TreeNode> stack = new LinkedList<>();
        public InOrderIterator(TreeNode root) { this.curr = root; }

        @Override public TreeNode next() {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode ret = stack.pop();
            curr = ret.right;
            return ret;
        }

        @Override public boolean hasNext() {
            return curr != null || !stack.isEmpty();
        }
    }

    public boolean compareInOrder(TreeNode r1, TreeNode r2) {
        InOrderIterator iterator1 = new InOrderIterator(r1);
        InOrderIterator iterator2 = new InOrderIterator(r2);
        while (iterator1.hasNext() && iterator2.hasNext())
            if (iterator1.next().val != iterator2.next().val) return false;
        return true;
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

    public static void test(String t1, String t2) {
        System.out.printf("Input: t1=\"%s\", t2=\"%s\"; Output: %b\n", t1, t2,
                new SameInorderGoogle().compareInOrder(valueOf(t1), valueOf(t2)));
    }

    public static void main(String[] args) {
        test("{2,1,3}", "{1,#,2,#,#,#,3}");
        test("{2,1,3}", "{1,#,2,#,#,3,#}");
    }

}
