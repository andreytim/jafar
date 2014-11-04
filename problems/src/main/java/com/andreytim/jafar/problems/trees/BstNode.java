package com.andreytim.jafar.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shpolsky on 22.10.14.
 */
public class BstNode {

    public final int value;
    public BstNode left;
    public BstNode right;

    public BstNode(int value) {
        this.value = value;
    }

    public static BstNode valueOf(String stree) {
        String[] nodes = stree.substring(1, stree.length() - 1).split(",");
        Queue<BstNode> queue = new LinkedList<>();
        BstNode root = new BstNode(Integer.parseInt(nodes[0]));
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            BstNode curr = queue.poll();
            curr.left = (i < nodes.length && !nodes[i].equals("#")) ? new BstNode(Integer.parseInt(nodes[i])) : null;
            i++;
            curr.right = (i < nodes.length && !nodes[i].equals("#")) ? new BstNode(Integer.parseInt(nodes[i])) : null;
            i++;
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return root;
    }

    public static void main(String[] args) {
        BstNode.valueOf("{3,9,20,#,#,15,7}");
        BstNode.valueOf("{1,#,2,3}");
    }
}