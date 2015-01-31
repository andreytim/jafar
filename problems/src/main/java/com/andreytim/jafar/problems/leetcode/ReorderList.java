package com.andreytim.jafar.problems.leetcode;

/**
 * Created by shpolsky on 30.01.15.
 */
public class ReorderList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public int mid(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return (length+1)/2;
    }

    public ListNode reverse(ListNode head) {
        if (head != null && head.next != null) {
            ListNode prev = head;
            head = head.next;
            prev.next = null;
            while (head != null) {
                ListNode tmp = head.next;
                head.next = prev;
                prev = head;
                head = tmp;
            }
            head = prev;
        }
        return head;
    }

    public ListNode reorderList(ListNode head) {
        if (head != null && head.next != null && head.next.next != null) {
            ListNode curr = head;
            int count = 1, mid = mid(head);
            while (count++ < mid) curr = curr.next;
            ListNode reverse = reverse(curr.next);
            curr.next = null;
            curr = head;
            while (curr != null && reverse != null) {
                ListNode tmp = curr.next;
                curr.next = reverse;
                reverse = reverse.next;
                curr.next.next = tmp;
                curr = tmp;
            }
        }
        return head;
    }

    // ---------
    // utility methods for testing
    // (copy/paste from class to class, because linked list every time is implemented from scratch)
    // ---------

    private static ListNode valueOf(String listDescr) {
        if (listDescr.isEmpty()) return null;
        String[] nodes = listDescr.split(" \\-\\> ");
        ListNode curr = new ListNode(Integer.parseInt(nodes[0]));
        ListNode head = curr;
        for (int i = 1; i < nodes.length; i++) {
            curr.next = new ListNode(Integer.parseInt(nodes[i]));
            curr = curr.next;
        }
        return head;
    }

    private static String toString(ListNode node) {
        if (node == null) return "null";
        return node.val + " -> " + toString(node.next);
    }

    public static void test(ListNode list) {
        System.out.printf("Input: %s; Output: %s\n", toString(list),
                toString(new ReorderList().reorderList(list)));
    }

    public static void main(String[] args) {
        test(valueOf("1 -> 2 -> 3"));
        test(valueOf("1 -> 2 -> 3 -> 4"));
    }

}
