package com.andreytim.jafar.problems.hashtables;

import java.util.*;

/**
 * You have a large text file containing words.
 * Given any two words, find the shortest distance (in terms of number of words)
 * between them in the file. If the operation will be repeated many times
 * for the same file (but different pairs of words),
 * can you optimize your solution?
 * CtCI, 18.5
 *
 * Created by shpolsky on 29.11.14.
 */
public class P94_WordsDistance {

    public static final Map<String, List<Integer>> positions = new HashMap<>();

    public static void init(String text) {
        String[] words = text.split("\\s");
        for (int i = 0; i < words.length; i++) {
            if (!positions.containsKey(words[i])) {
                positions.put(words[i], new ArrayList<Integer>());
            }
            positions.get(words[i]).add(i);
        }
    }

    public static int distance(String w1, String w2) {
        if (!positions.containsKey(w1) || !positions.containsKey(w2)) return -1;
        if (w1.equals(w2)) return 0;
        Integer[] p1l = positions.get(w1).toArray(new Integer[1]);
        Integer[] p2l = positions.get(w2).toArray(new Integer[1]);
        int i = 0, j = 0;
        int min = Math.abs(p1l[i] - p2l[j]);
        while (i < p1l.length-1 && j < p2l.length-1) {
            if (Math.abs(p1l[i+1] - p2l[j]) < Math.abs(p1l[i] - p2l[j+1])) i++;
            else j++;
            min = Math.min(min, Math.abs(p1l[i] - p2l[j]));
        }
        while (i < p1l.length-1) min = Math.min(min, Math.abs(p1l[++i] - p2l[j]));
        while (j < p2l.length-1) min = Math.min(min, Math.abs(p1l[i] - p2l[++j]));
        return min-1;
    }

    public static void test(String w1, String w2) {
        System.out.printf("Distance between \"%s\" and \"%s\" is %d.\n", w1, w2, distance(w1, w2));
    }

    public static void main(String[] args) {
        init("aa bb cc dd ee ff kk dd ee cc dd bb fff dds gf ddd ssa d d d gggd dfd d");
        test("aa", "bb");
        test("dd", "kk");
        test("bb", "ddd");
        test("d", "gf");
    }
}
