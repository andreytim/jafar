package com.andreytim.jafar.problems.linkedlist;

/**
 * Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater than or equal to x.
 * CtCI, 2.4
 *
 * Created by shpolsky on 30.11.14.
 */
public class P36_PartitionLl {

    public static class Node {
        public final int value;
        public Node next;
        public Node(int value) { this.value = value; }
    }

    public Node partition(Node head, int x) {
        if (head == null) return null;
        Node lessHead = null, lessTail = null, greaterHead = null;
        while (head != null) {
            Node tmp = head.next;
            if (head.value < x) {
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                } else {
                    lessTail.next = head;
                    lessTail = lessTail.next;
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = head;
                    head.next = null;
                } else {
                    head.next = greaterHead;
                    greaterHead = head;
                }
            }
            head = tmp;
        }
        if (lessTail == null) lessHead = greaterHead;
        else lessTail.next = greaterHead;
        return lessHead;
    }

    // utils

    private static Node valueOf(String listDescr) {
        if (listDescr.isEmpty()) return null;
        String[] nodes = listDescr.split(" \\-\\> ");
        Node curr = new Node(Integer.parseInt(nodes[0]));
        Node head = curr;
        for (int i = 1; i < nodes.length; i++) {
            curr.next = new Node(Integer.parseInt(nodes[i]));
            curr = curr.next;
        }
        return head;
    }

    private static String toString(Node node) {
        if (node == null) return "null";
        return node.value + " -> " + toString(node.next);
    }

    public static void test(String listDescr, int x) {
        System.out.printf("Input: list=%s, x=%d; Result: %s\n", listDescr, x,
                toString(new P36_PartitionLl().partition(valueOf(listDescr), x)));
    }

    public static void main(String[] args) {
        test("5", 3);
        test("5", 6);
        test("5", 5);
        test("5 -> 4", 6);
        test("5 -> 4", 5);
        test("5 -> 4", 4);
        test("5 -> 4", 3);
        test("5 -> 4 -> 10 -> 1 -> 5 -> 8 -> 2 -> 0", 5);
        test("5 -> 4 -> 10 -> 1 -> 5 -> 8 -> 2 -> 0", 3);
        test("5 -> 4 -> 10 -> 1 -> 5 -> 8 -> 2 -> 0", 10);
    }

}
