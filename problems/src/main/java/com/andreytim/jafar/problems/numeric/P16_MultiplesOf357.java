package com.andreytim.jafar.problems.numeric;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the kth number such that its' only prime factors are 3, 5, and 7.
 * CtCI, 7.7
 *
 * Created by shpolsky on 17.11.14.
 */
public class P16_MultiplesOf357 {

    // O(k) time, O(k) memory
    public int kth357(int k) {
        if (k <= 0) return 0;
        int[] arr = new int[k+1];
        arr[0] = 1;
        int p3 = 0, p5 = 0, p7 = 0;
        for (int i = 1; i <= k; i++) {
            int next = Math.min(arr[p3]*3, Math.min(arr[p5]*5, arr[p7]*7));
            if (next == arr[p3]*3) p3++;
            if (next == arr[p5]*5) p5++;
            if (next == arr[p7]*7) p7++;
            arr[i] = next;
        }
        return arr[k];
    }

    // O(k) time, O(k) memory, but seems like worse in both aspects than the approach above
    // due to pointers and boxed primitives
    public int kth357Queues(int k) {
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queue5 = new LinkedList<>();
        Queue<Integer> queue7 = new LinkedList<>();
        queue3.add(3); queue5.add(5); queue7.add(7);
        int next = 0;
        for (int i = 0; i < k; i++) {
            next = Math.min(queue3.peek(), Math.min(queue5.peek(), queue7.peek()));
            if (next == queue3.peek()) {
                queue3.poll();
                queue3.offer(next * 3);
                queue5.offer(next*5);
            } else if (next == queue5.peek()) {
                queue5.poll();
                queue5.offer(next*5);
            } else {
                queue7.poll();
            }
            queue7.offer(next*7);
        }
        return next;
    }

    public static void test(int k) {
        System.out.printf("Input: %d; Result: array=%d, queues=%d\n", k,
                new P16_MultiplesOf357().kth357(k), new P16_MultiplesOf357().kth357Queues(k));
    }

    public static void main(String[] args) {
        test(0);
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
        test(6);
        test(7);
        test(67);
        test(100);
        test(150);
    }

}
