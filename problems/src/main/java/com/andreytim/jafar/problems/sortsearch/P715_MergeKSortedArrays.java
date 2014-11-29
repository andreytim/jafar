package com.andreytim.jafar.problems.sortsearch;

import java.util.*;

/**
 * Merge k sorted arrays. The heap approach.
 * The same approach would be applicable for the merging of lists or external streams.
 * O(k) - memory consumption, O(n*log(k)) complexity.
 *
 * Another approach is to do it recursively pair-wise like in the merge-sort.
 *
 * Created by shpolsky on 10.11.14.
 */
public class P715_MergeKSortedArrays {

    private static class Pair implements Comparable<Pair> {
        private final int idxOfArray;
        private final int value;
        public Pair(int idx, int val) {
            idxOfArray = idx;
            value = val;
        }
        @Override public int compareTo(Pair other) {
            return value - other.value;
        }
    }

    public static int[] mergeKSortedArrays(List<int[]> arrays) {
        Queue<Pair> heap = new PriorityQueue<>(arrays.size());
        int[] idcs = new int[arrays.size()];
        int resultSize = 0;
        for (int i = 0; i < arrays.size(); i++) {
            int[] arr = arrays.get(i);
            if (arr != null && arr.length > 0) {
                resultSize += arr.length;
                heap.offer(new Pair(i, arr[0]));
                idcs[i]++;
            }
        }
        int[] result = new int[resultSize];
        int k = 0;
        while (!heap.isEmpty()) {
            Pair curr = heap.poll();
            result[k++] = curr.value;
            int[] arr = arrays.get(curr.idxOfArray);
            if (idcs[curr.idxOfArray] < arr.length) {
                heap.offer(new Pair(curr.idxOfArray, arr[idcs[curr.idxOfArray]++]));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<int[]> arrays = new ArrayList<>();
        arrays.add(new int[]{ });
        System.out.println(Arrays.toString(mergeKSortedArrays(arrays)));
        arrays = new ArrayList<>();
        arrays.add(null);
        arrays.add(new int[]{ });
        System.out.println(Arrays.toString(mergeKSortedArrays(arrays)));
        arrays.add(new int[]{ 1, 3, 5, 6 });
        arrays.add(new int[]{ 7 });
        System.out.println(Arrays.toString(mergeKSortedArrays(arrays)));
        arrays.add(new int[]{ 4, 8, 10 });
        arrays.add(new int[]{ 2, 9 });
        System.out.println(Arrays.toString(mergeKSortedArrays(arrays)));
        arrays.add(new int[]{ -2341, 42354325 });
        arrays.add(new int[]{ 0, 0, 0, 0 });
        System.out.println(Arrays.toString(mergeKSortedArrays(arrays)));
    }

}
