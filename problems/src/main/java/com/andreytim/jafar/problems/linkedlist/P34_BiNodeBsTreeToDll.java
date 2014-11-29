package com.andreytim.jafar.problems.linkedlist;

/**
 * Convert BS-Tree on BiNodes to Doubly-Linked list on BiNodes.
 * CtCI, 17.13
 *
 * Created by shpolsky on 29.11.14.
 */
public class P34_BiNodeBsTreeToDll {

    public static class BiNode {
        public BiNode node1, node2;
        public int value;
    }

    public BiNode toDoublyLinkedList(BiNode root) {
        if (root == null) return null;
        BiNode leftHead = toDoublyLinkedList(root.node1);
        if (leftHead == null) {
            leftHead = root;
            root.node1 = null;
        } else {
            BiNode curr = leftHead;
            while (curr.node2 != null) curr = curr.node2;
            curr.node2 = root;
            root.node1 = curr;
        }
        BiNode rightHead = toDoublyLinkedList(root.node2);
        if (rightHead != null) {
            root.node2 = rightHead;
            rightHead.node1 = root;
        }
        return leftHead;
    }

}
