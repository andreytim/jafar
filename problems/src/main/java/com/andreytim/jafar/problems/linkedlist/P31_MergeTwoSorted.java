package com.andreytim.jafar.problems.linkedlist;

/**
 * Merge two sorted singly linked lists.
 * Your code should use 0(1) additional storage - it should reuse the nodes
 * from the lists provided as input.
 * The only field you can change in a node is next
 *
 * Created by shpolsky on 05.11.14.
 */
public class P31_MergeTwoSorted {

  private static class ListNode {
    public final int value;
    public ListNode next;
    public ListNode(int value) { this(value, null); }
    public ListNode(int value, ListNode next) { 
      this.value = value; 
      this.next = next;
    }
  }

  /**
   * Simple recursive approach. Great for an interview, in my opinion.
   *
   * @param head1
   * @param head2
   * @return
   */
  public static ListNode merge(ListNode head1, ListNode head2) {
    if (head1 == null && head2 == null) return null;
    if (head1 == null) return head2;
    if (head2 == null) return head1;
    if (head1.value < head2.value) {
      head1.next = merge(head1.next, head2);
      return head1;
    } else {
      head2.next = merge(head2.next, head1);
      return head2;
    }
  }

  /**
   * Iterative approach. Harder to come up with the error-free version under pressure,
   * but just in case of being asked.
   *
   * @param head1
   * @param head2
   * @return
   */
  public static ListNode mergeIterative(ListNode head1, ListNode head2) {
    ListNode dumbHead = new ListNode(0, null);
    ListNode curr = dumbHead;
    while (true) {
      if (head1 == null && head2 == null) break;
      if (head1 == null || (head2 != null && head2.value < head1.value)) {
        curr.next = head2;
        head2 = head2.next;
      } else {
        curr.next = head1;
        head1 = head1.next;
      }
      curr = curr.next;
    }
    return dumbHead.next;
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
      return node.value + " -> " + toString(node.next);
  }

  // --------

  public static void main(String[] args) {
      System.out.println(toString(merge(valueOf("4 -> 5 -> 7"), valueOf("2 -> 3 -> 6"))));
      System.out.println(toString(merge(valueOf("7"), valueOf("2 -> 3 -> 8"))));
      System.out.println(toString(merge(valueOf(""), valueOf(""))));
      System.out.println(toString(merge(valueOf(""), valueOf("2 -> 3 -> 8"))));
      System.out.println(toString(mergeIterative(valueOf("4 -> 5 -> 7"), valueOf("2 -> 3 -> 6"))));
      System.out.println(toString(mergeIterative(valueOf("7"), valueOf("2 -> 3 -> 8"))));
      System.out.println(toString(mergeIterative(valueOf(""), valueOf(""))));
      System.out.println(toString(mergeIterative(valueOf(""), valueOf("2 -> 3 -> 8"))));
  }

}
