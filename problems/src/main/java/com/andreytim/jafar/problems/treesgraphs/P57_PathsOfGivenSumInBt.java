package com.andreytim.jafar.problems.treesgraphs;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a binary tree in which each node contains a value.
 * Design an algorithm to print all paths which sum to a given value.
 * The path does not need to start or end at the root or a leaf.
 * CtCI, 4.9
 *
 * Note: the solution in the book is wrong for such problem statement,
 * because it doesn't take into account not vertical paths.
 *
 * Created by shpolsky on 25.11.14.
 */
public class P57_PathsOfGivenSumInBt {

    private static class TreeNode {
        public final int value;
        public TreeNode left, right;
        public TreeNode(int value) { this.value = value; }
    }

    private static class Path {
        public final int sum;
        public final List<TreeNode> nodes;
        public Path(int sum, List<TreeNode> nodes) {
            this.sum = sum;
            this.nodes = nodes;
        }
        @Override public String toString() {
            if (nodes.isEmpty()) return "[]";
            StringBuilder builder = new StringBuilder("[ ");
            for (int i = 0; i < nodes.size()-1; i++) {
                builder.append(nodes.get(i).value);
                builder.append("->");
            }
            builder.append(nodes.get(nodes.size()-1));
            builder.append(" ]");
            return builder.toString();
        }
    }

    private List<Path> generateAllSubpaths(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Path> result = new LinkedList<>();
        result.add(new Path(root.value, Arrays.asList(root)));
        List<Path> leftPaths = generateAllSubpaths(root.left);
        List<Path> rightPaths = generateAllSubpaths(root.right);
        for (Path pl : leftPaths) {
            result.add(pl);
            if (pl.nodes.get(0) == root.left) {
                LinkedList<TreeNode> newNodes = new LinkedList<>(pl.nodes);
                newNodes.addFirst(root);
                result.add(new Path(pl.sum + root.value, newNodes));
                for (Path pr : rightPaths) {
                    if (pr.nodes.get(0) == root.right) {
                        LinkedList<TreeNode> newJoinNodes = new LinkedList<>(pl.nodes);
                        newJoinNodes.addFirst(root);
                        for (TreeNode node : pr.nodes) {
                            newJoinNodes.addFirst(node);
                        }
                        result.add(new Path(pl.sum + root.value + pr.sum, newJoinNodes));
                    }
                }
            }
        }
        for (Path pr : rightPaths) {
            result.add(pr);
            if (pr.nodes.get(0) == root.right) {
                LinkedList<TreeNode> newNodes = new LinkedList<>(pr.nodes);
                newNodes.addFirst(root);
                result.add(new Path(pr.sum + root.value, newNodes));
            }
        }
        return result;
    }

    public void printPathsOfGivenSum(TreeNode root, int S) {
        List<Path> paths = generateAllSubpaths(root);
        for (Path p : paths) {
            if (p.sum == S) {
                System.out.println(p);
            }
        }
    }

}
