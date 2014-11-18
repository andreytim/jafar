package com.andreytim.jafar.problems.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * How would you solve this problem if a temporary buffer is not allowed?
 * CtCI, 2.1
 *
 * Created by shpolsky on 18.11.14.
 */
public class P32_RemoveDuplicates {

    private static class Node {
        public final int value;
        public Node next;
        public Node(int value) { this(value, null); }
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    // Using additional set, two pointers, iterative, O(n) time, O(n) space
    public Node removeDuplicates(Node head) {
        if (head == null) return head;
        Set<Integer> vals = new HashSet<>();
        vals.add(head.value);
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (vals.contains(curr.value)) {
                prev.next = curr.next;
            } else {
                vals.add(curr.value);
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    // Using additional set, one pointer, iterative, O(n) time, O(n) space
    public Node removeDuplicates2(Node head) {
        if (head == null) return head;
        Set<Integer> vals = new HashSet<>();
        vals.add(head.value);
        Node curr = head;
        while (curr.next != null) {
            if (vals.contains(curr.next.value)) {
                curr.next = curr.next.next;
            } else {
                vals.add(curr.next.value);
                curr = curr.next;
            }
        }
        return head;
    }

    // No additional buffer, one pointer, recursive (could be also iterative),
    // O(n^2) time, O(n) space for recursion
    public Node removeDuplicates3(Node head) {
        if (head == null) return head;
        Node curr = head;
        while (curr != null) {
            curr = removeVal(curr.value, curr.next);
        }
        return head;
    }

    public Node removeVal(int val, Node head) {
        if (head == null) return head;
        else if (head.value == val) {
            return removeVal(val, head.next);
        } else {
            head.next = removeVal(val, head.next);
            return head;
        }
    }

    // ---------
    // utility methods for testing
    // (copy/paste from class to class, because linked list every time is implemented from scratch)
    // ---------

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

    public static void test(String list) {
        Node head = valueOf(list);
        System.out.printf("Input: %s\n", toString(head));
        System.out.printf("Result1: %s\n", toString(new P32_RemoveDuplicates().removeDuplicates(head)));
        System.out.printf("Result2: %s\n", toString(new P32_RemoveDuplicates().removeDuplicates2(head)));
        System.out.printf("Result3: %s\n", toString(new P32_RemoveDuplicates().removeDuplicates3(head)));
    }

    public static void main(String[] args) {
        test("");
        test("3 -> 4 -> 7 -> 10");
        test("3 -> 3 -> 3 -> 10");
        test("3 -> 4 -> 4 -> 10");
        test("3 -> 4 -> 7 -> 10 -> 10");
        test("3 -> 4 -> 7 -> 3 -> 7 -> 10 -> 10");
    }

}
