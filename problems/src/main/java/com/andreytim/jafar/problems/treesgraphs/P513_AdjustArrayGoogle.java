package com.andreytim.jafar.problems.treesgraphs;

import java.util.*;

/**
 * Created by shpolsky on 11.12.14.
 */
public class P513_AdjustArrayGoogle {

    public int adjust(int[] arr, int target) {
        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(arr));
        Map<String, int[]> parents = new HashMap<>();
        parents.put(Arrays.toString(arr), null);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(arr);
        int cost = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            boolean adjusted = false;
            for (int i = 0; i < curr.length-1; i++) {
                if (Math.abs(curr[i] - curr[i+1]) > target) {
                    int[] A = Arrays.copyOf(curr, curr.length);
                    A[i] += (curr[i] - curr[i+1] > 0) ? -1 : 1;
                    if (visited.add(Arrays.toString(A))) {
                        parents.put(Arrays.toString(A), curr);
                        queue.offer(A);
                    }
                    A = Arrays.copyOf(curr, curr.length);
                    A[i+1] += (curr[i] - curr[i+1] > 0) ? 1 : -1;
                    if (visited.add(Arrays.toString(A))) {
                        parents.put(Arrays.toString(A), curr);
                        queue.offer(A);
                    }
                    adjusted = true;
                }
            }
            if (!adjusted) {
                System.out.println(Arrays.toString(curr));
                while (parents.get(Arrays.toString(curr)) != null) {
                    cost++;
                    curr = parents.get(Arrays.toString(curr));
                }
                break;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        System.out.println(new P513_AdjustArrayGoogle().adjust(new int[]{1,4,2,3}, 1));
        System.out.println(new P513_AdjustArrayGoogle().adjust(new int[]{1,4,2,3}, 2));
        System.out.println(new P513_AdjustArrayGoogle().adjust(new int[]{1,2,3,4}, 1));
    }
}
