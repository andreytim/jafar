package com.andreytim.jafar.problems.dp;

import java.util.Arrays;
import java.util.Collections;

/**
 * A circus is designing a tower routine consisting of people standing atop one another's shoulders.
 * For practical and aesthetic reasons, each person must be both shorter and lighter than the person
 * below him or her. Given the heights and weights of each person in the circus,
 * write a method to compute the largest possible number of people in such a tower.
 * CtCI, 11.7
 *
 * Created by shpolsky on 02.12.14.
 */
public class P813_CircusTower {

    public static class Person implements Comparable<Person> {
        public final int h, w;
        public Person(int h, int w) { this.h = h; this.w = w; }
        @Override public int compareTo(Person other) {
            if (h < other.h && w < other.w) return -1;
            else if (h > other.h && w > other.w) return 1;
            else return 0;
        }
        @Override public String toString() {
            return String.format("[%d,%d]", h, w);
        }
    }

    public int maxTower(Person[] circus) {
        if (circus == null || circus.length == 0) return 0;
        Arrays.sort(circus, Collections.reverseOrder());
        int[] tHeights = new int[circus.length];
        int res = 1;
        for (int i = 0; i < circus.length; i++) {
            tHeights[i] = 1;
            for (int j = 0; j < i && circus[j].compareTo(circus[i]) > 0; j++) {
                tHeights[i] = Math.max(tHeights[j]+1, tHeights[i]);
            }
            res = Math.max(res, tHeights[i]);
        }
        return res;
    }

    public static void test(Person[] circus) {
        System.out.printf("Input: %s; Result: %d\n", Arrays.toString(circus), new P813_CircusTower().maxTower(circus));
    }

    public static void main(String[] args) {
        test(null);
        test(new Person[]{});
        test(new Person[]{ new Person(1,1) });
        test(new Person[]{ new Person(1,1), new Person(1,1) });
        test(new Person[]{ new Person(1,1), new Person(2,3), new Person(1,1) });
        test(new Person[]{ new Person(1,1), new Person(2,3), new Person(4,4) });
    }

}
