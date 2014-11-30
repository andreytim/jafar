package com.andreytim.jafar.problems.linkedlist;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Implement towers of hanoi.
 * CtCI, 3.4
 *
 * Created by shpolsky on 30.11.14.
 */
public class P37_TowersOfHanoi {

    public void recursiveHelper(LinkedList[] towers, int from, int to, int using, int size) {
        if (size > 0) {
            recursiveHelper(towers, from, using, to, size-1);
            towers[to].push(towers[from].pop()); // unchecked warning, but we know that everything is of Integer
            recursiveHelper(towers, using, to, from, size-1);
        }
    }

    public LinkedList[] solveTowers(int size, boolean debug) {
        LinkedList<Integer> tower = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            tower.push(i+1);
        }
        LinkedList[] towers = new LinkedList[]{
                tower, new LinkedList<Integer>(), new LinkedList<Integer>()
        };
        if (debug) System.out.printf("Input debug: %s\n", Arrays.asList(towers));
        recursiveHelper(towers, 0, 1, 2, size);
        return towers;
    }

    private static void test(int size) {
        System.out.printf("Input: %d; Result: %s\n", size,
                Arrays.toString(new P37_TowersOfHanoi().solveTowers(size, false)));
    }

    public static void main(String[] args) {
        test(0);
        test(1);
        test(2);
        test(5);
        test(10);
    }

}
