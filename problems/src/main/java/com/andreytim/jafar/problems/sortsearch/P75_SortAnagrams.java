package com.andreytim.jafar.problems.sortsearch;

import java.util.*;

/**
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 * CtCI, 11.2
 *
 * Created by shpolsky on 14.11.14.
 */
public class P75_SortAnagrams {

    public void sortAnagrams(String[] arr) {
        if (arr.length < 3) return;
        Map<String, List<String>> buckets = new HashMap<>();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!buckets.containsKey(key)) {
                buckets.put(key, new ArrayList<String>());
            }
            buckets.get(key).add(s);
        }
        int i = 0;
        for (List<String> anagrams : buckets.values()) {
            for (String s : anagrams) {
                arr[i++] = s;
            }
        }
    }

    public static void test(String[] arr) {
        System.out.println("Unsorted: " + Arrays.toString(arr));
        new P75_SortAnagrams().sortAnagrams(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
        System.out.println();
    }

    public static void main(String[] args) {
        test(new String[]{ "aaa", "bbb", "aaa", "ccc", "bbb" });
        test(new String[]{ "abc", "bbb", "cba", "ccc", "bac" });
        test(new String[]{ "abcasdga", "bbbsadgas" });
    }
}
