package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * TopCoder:
 * Single Round Match 571 Round 1 - Division II, Level Two
 * http://community.topcoder.com/stat?c=problem_statement&pm=12437
 *
 * Created by shpolsky on 13.11.14.
 */
public class P73_FoxAndMp3Easy {

    private static final int TOP_SIZE = 50;
    private static final String SONG_SUFFIX = ".mp3";

    public String[] playList(int n)
    {
        Queue<String> top = new PriorityQueue<>(1, Collections.reverseOrder());
        for (int i = 1; i <= n; i++) {
            top.offer(String.valueOf(i));
            if (top.size() > TOP_SIZE) {
                top.poll();
            }
        }
        String[] result = new String[Math.min(TOP_SIZE, n)];
        for (int i = Math.min(TOP_SIZE, n) - 1; i >= 0; i--) {
            result[i] = top.poll() + SONG_SUFFIX;
        }
        return result;
    }

    public void test(int n) {
        System.out.printf("Songs for n=%d: %s\n", n, Arrays.toString(playList(n)));
    }

    public static void main(String[] args) {
        P73_FoxAndMp3Easy instance = new P73_FoxAndMp3Easy();
        instance.test(1);
        instance.test(3);
        instance.test(10);
        instance.test(18);
        instance.test(32);
        instance.test(109);
    }

}
