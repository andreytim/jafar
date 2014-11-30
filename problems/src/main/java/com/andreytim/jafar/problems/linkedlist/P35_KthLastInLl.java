package com.andreytim.jafar.problems.linkedlist;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * CtCI, 2.2
 *
 * Created by shpolsky on 30.11.14.
 */
public class P35_KthLastInLl {

    public static class Node {
        public final int value;
        public Node next;
        public Node(int value) { this.value = value; }
        @Override public String toString() {
            return String.valueOf(value);
        }
    }

    public Node findKthLast(Node head, int k) {
        int count = 0;
        Node res = head;
        Node curr = head;
        while (curr != null) {
            count++;
            if (count > k) res = res.next;
            curr = curr.next;
        }
        return count >= k ? res : null;
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

    public static void test(String listDescr, int k) {
        System.out.printf("Input: list=%s, k=%d; Result: %s\n", listDescr, k,
                new P35_KthLastInLl().findKthLast(valueOf(listDescr), k));
    }

    public static void main(String[] args) {
        test("5", 0);
        test("5", 1);
        test("5", 2);
        test("5 -> 2", 0);
        test("5 -> 2", 1);
        test("5 -> 2", 2);
        test("5 -> 2", 3);
        test("5 -> 4 -> 3 -> 2 -> 1", 0);
        test("5 -> 4 -> 3 -> 2 -> 1", 1);
        test("5 -> 4 -> 3 -> 2 -> 1", 2);
        test("5 -> 4 -> 3 -> 2 -> 1", 3);
        test("5 -> 4 -> 3 -> 2 -> 1", 4);
        test("5 -> 4 -> 3 -> 2 -> 1", 5);
        test("5 -> 4 -> 3 -> 2 -> 1", 6);
    }
}
