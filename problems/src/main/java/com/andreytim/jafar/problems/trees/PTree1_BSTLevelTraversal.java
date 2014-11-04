package com.andreytim.jafar.problems.trees;

import java.util.*;

/**
 * Print BST in level by level order beginning from the root.
 * Clarifications:
 * - do we need just to print or to return the right order? (both)
 * - do we need just the order of elements or with splitting on levels? (both)
 * - complexity? (O(n))
 *
 * Three types of by-level printing are illustrated below:
 * - direct,
 * - reverse-by-level (bottom-up),
 * - zigzag-direct.
 *
 * Created by shpolsky on 22.10.14.
 */
public class PTree1_BSTLevelTraversal {

    public static void printLevelTraversalOneLine(BstNode root) {
        if (root != null) {
            Queue<BstNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                BstNode curr = queue.poll();
                System.out.print(curr.value + " ");
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            System.out.println();
        }
    }

    public static void printReverseLevelTraversalOneLine(BstNode root) {
        if (root != null) {
            Queue<BstNode> queue = new LinkedList<>();
            queue.offer(root);
            Deque<Integer> stack = new LinkedList<>();
            while (!queue.isEmpty()) {
                BstNode curr = queue.poll();
                stack.push(curr.value);
                if (curr.right != null) queue.offer(curr.right);
                if (curr.left != null) queue.offer(curr.left);
            }
            System.out.println(stack);
        }
    }

    /**
     * The approach with additional memorizing of the level for the child nodes.
     *
     * There's also a two-stack approach which is less error-prone during the interview in my mind.
     * See here: http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
     *
     * @param root
     */
    public static void printZigZagLevelTraversalOneLine(BstNode root) {
        if (root != null) {
            Map<BstNode, Integer> nodeToLevel = new HashMap<>();
            nodeToLevel.put(root, 0);
            Queue<BstNode> queue = new LinkedList<>();
            queue.offer(root);
            int currLevel = -1;
            Deque<Integer> levelToPrint = new LinkedList<>();
            while (!queue.isEmpty()) {
                BstNode curr = queue.poll();
                if (currLevel != nodeToLevel.get(curr)) {
                    currLevel++;
                    for (Integer val : levelToPrint) {
                        System.out.print(val + " ");
                    }
                    System.out.println();
                    levelToPrint = new LinkedList<>();
                }
                if ((currLevel & 1) == 1) {
                    levelToPrint.addLast(curr.value);
                } else {
                    levelToPrint.addFirst(curr.value);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                    nodeToLevel.put(curr.right, currLevel + 1);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                    nodeToLevel.put(curr.left, currLevel + 1);
                }
            }
            if (!levelToPrint.isEmpty()) {
                for (Integer val : levelToPrint) {
                    System.out.print(val + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Method returning the order splitted by levels in the form of a list.
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderTraversal(BstNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<BstNode, Integer> levels = new HashMap<>();
        Queue<BstNode> queue = new LinkedList<>();
        queue.offer(root);
        levels.put(root, 0);
        int currLevel = -1;
        while (!queue.isEmpty()) {
            BstNode curr = queue.poll();
            if (currLevel != levels.get(curr)) {
                result.add(new ArrayList<Integer>());
                currLevel++;
            }
            result.get(currLevel).add(curr.value);
            if (curr.left != null) {
                queue.offer(curr.left);
                levels.put(curr.left, currLevel + 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                levels.put(curr.right, currLevel + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        test("{1,2}");
        test("{3,9,20,#,#,15,7}");
    }

    private static void test(String stree) {
        System.out.printf("Input: %s\nOutput_List: %s\n", stree, levelOrderTraversal(BstNode.valueOf(stree)));
        System.out.print("Output_Print: ");
        printLevelTraversalOneLine(BstNode.valueOf(stree));
        System.out.print("Output_Print_Reverse: ");
        printReverseLevelTraversalOneLine(BstNode.valueOf(stree));
        System.out.print("Output_Print_ZigZag: ");
        printZigZagLevelTraversalOneLine(BstNode.valueOf(stree));
    }

}
