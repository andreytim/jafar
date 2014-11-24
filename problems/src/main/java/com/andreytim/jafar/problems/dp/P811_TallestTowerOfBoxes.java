package com.andreytim.jafar.problems.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * You have a stack of n boxes, with widths w, heights l, and depths d.
 * The boxes cannot be rotated and can only be stacked on top of one another
 * if each box in the stack is strictly larger than the box above it
 * in width, height, and depth.
 * Implement a method to build the tallest stack possible,
 * where the height of a stack is the sum of the heights of each box.
 * CtCI, 9.10
 *
 * Created by shpolsky on 24.11.14.
 */
public class P811_TallestTowerOfBoxes {

    private static class Box implements Comparable<Box> {
        public final int w, h, d;
        public Box(int w, int h, int d) { this.w = w; this.h = h; this.d = d; }
        @Override public int compareTo(Box other) {
            if (w > other.w && h > other.h && d > other.d) return 1;
            else if (w < other.w && h < other.h && d < other.d) return -1;
            return 0;
        }
        @Override public String toString() {
            return String.format("{w:%d,h:%d,d:%d}", w, h, d);
        }
    }

    public List<Box> tallestStack(Box[] boxes) {
        Arrays.sort(boxes, Collections.reverseOrder());
        int[] dph = new int[boxes.length];
        int[] prev = new int[boxes.length];
        int maxHeight = Integer.MIN_VALUE, idx = -1;
        for (int i = 0; i < boxes.length; i++) {
            int currMax = boxes[i].h, currPrev = i;
            for (int j = 0; j < i && boxes[j].compareTo(boxes[i]) > 0; j++) {
                if (dph[j] + boxes[i].h > currMax) {
                    currMax = dph[j] + boxes[i].h;
                    currPrev = j;
                }
            }
            dph[i] = currMax;
            prev[i] = currPrev;
            if (currMax > maxHeight) {
                maxHeight = currMax;
                idx = i;
            }
        }
        LinkedList<Box> res = new LinkedList<>();
        while (prev[idx] != idx) {
            res.push(boxes[idx]);
            idx = prev[idx];
        }
        res.push(boxes[idx]);
        return res;
    }

    private static void test(Box[] boxes) {
        System.out.printf("Input: %s\n", Arrays.toString(boxes));
        System.out.printf("Result: %s\n", new P811_TallestTowerOfBoxes().tallestStack(boxes).toString());
    }

    public static void main(String[] args) {
        test(new Box[]{ new Box(1, 1, 1) });
        test(new Box[]{ new Box(1, 1, 1), new Box(1, 1, 1) });
        test(new Box[]{ new Box(5, 5, 5), new Box(1, 1, 1), new Box(3, 2, 3), new Box(9, 1, 1), new Box(4, 3, 5) });
        test(new Box[]{ new Box(1, 1, 1), new Box(2, 2, 2), new Box(3, 1, 2) });
    }

}
