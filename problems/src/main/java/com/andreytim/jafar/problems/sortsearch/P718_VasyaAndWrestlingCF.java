package com.andreytim.jafar.problems.sortsearch;

import java.io.*;
import java.util.*;

/**
 * CodeForces:
 * Round 281 - Div II, B
 * http://codeforces.com/contest/493/problem/B
 *
 * Created by shpolsky on 06.12.14.
 */
public class P718_VasyaAndWrestlingCF {

    public static String winner(int[] points) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        long firstSum = 0, secondSum = 0;
        String lastOne = "first";
        for (int i = 0; i < points.length; i++) {
            if (points[i] > 0) {
                first.add(points[i]); firstSum += points[i]; lastOne = "first";
            } else {
                second.add(-points[i]); secondSum -= points[i]; lastOne = "second";
            }
        }
        if (firstSum != secondSum)
           return (firstSum > secondSum) ? "first" : "second";
        else {
            int i = 0;
            while (i < first.size() && i < second.size() && first.get(i).equals(second.get(i))) i++;
            if (i < first.size() && i < second.size())
                return (first.get(i) > second.get(i)) ? "first" : "second";
            else if (first.size() != second.size())
                return (first.size() > second.size()) ? "first" : "second";
            else return lastOne;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(reader.readLine());
//        int[] points = new int[N];
//        for (int i = 0; i < N; i++) {
//            points[i] = Integer.parseInt(reader.readLine());
//        }
//        System.out.println(winner(points));
        System.out.println(winner(new int[]{1, 2, -3, -4, 3}));
        System.out.println(winner(new int[]{-1, -2, 3}));
        System.out.println(winner(new int[]{4, -4}));
    }

}
