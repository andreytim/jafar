package com.andreytim.jafar.problems.linkedlist;

/**
 * Design a stack that supports a max operation,
 * which returns the maximum value stored in the stack,
 * and throws an exception if the stack is empty.
 * Assume elements are comparable.
 * All operations must be 0(1) time.
 * You can use O(n) additional space, beyond what is required for the elements themselves.
 *
 * Created by shpolsky on 06.11.14.
 */
public class P33_StackWithMax {

  public static class StackWithMax {
  
    private class Node {
      private final int value;
      private Node next;
      private int maxBelow;
      public Node(int value) { this.value = value; }
      public Node(int value, Node next) { this.value = value; this.next = next; }
    }

    private Node top;

    public void push(int value) {
      top = new Node(value, top);
      top.maxBelow = Math.max(top.value, top.next == null ? Integer.MIN_VALUE : top.next.maxBelow);
    }

    public int pop() {
      if (isEmpty()) throw new IllegalStateException();
      int val = top.value;
      top = top.next;
      return val;
    }

    public int max() {
      if (isEmpty()) throw new IllegalStateException();
      return top.maxBelow;
    }

    public boolean isEmpty() {
      return top == null;
    }
  }

  // ---------
  // utility methods for testing
  // (copy/paste from class to class, because linked list every time is implemented from scratch)
  // ---------

  private static StackWithMax valueOf(String stackDescr) {
      if (stackDescr.isEmpty()) return null;
      String[] nodes = stackDescr.split(" \\-\\> ");
      StackWithMax stack = new StackWithMax();
      for (int i = 0; i < nodes.length; i++) {
          stack.push(Integer.parseInt(nodes[i]));
      }
      return stack;
  }

  // --------

  public static void main(String[] args) {
      StackWithMax stack = valueOf("6 -> 10 -> 5 -> 4 -> 234 -> 0 -> 423 -> 4 -> 6 -> 2543");
      System.out.println(stack.max());
      stack.pop();
      System.out.println(stack.max());
      System.out.println(valueOf("6 -> 10 -> 5 -> 4").max());
      System.out.println(valueOf("6").max());
      System.out.println(valueOf("1 -> 2 -> 3").max());
  }

}
