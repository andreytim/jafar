package com.andreytim.jafar.problems.sortsearch;

import java.util.Arrays;

/**
 * TopCoder Single Round Match 588 Round 1 - Division II, Level Two
 * http://community.topcoder.com/stat?c=problem_statement&pm=12707
 *
 * Created by shpolsky on 13.11.14.
 */
public class P72_GUMIAndSongsDiv2 {

    public class Song implements Comparable<Song> {
        private int duration;
        private int tone;
        public Song(int duration, int tone) {
            this.duration = duration;
            this.tone = tone;
        }
        @Override public int compareTo(Song other) {
            return this.tone - other.tone;
        }
    }

    public int maxSongs(int[] duration, int[] tone, int T) {
        Song[] songs = new Song[tone.length];
        for (int i = 0; i < tone.length; i++) {
            songs[i] = new Song(duration[i], tone[i]);
        }
        Arrays.sort(songs);
        int result = 0;
        int bitMask = 0;
        while (bitMask < (1 << songs.length)) {
            int currSum = 0, currCount = 0;
            int minTone = Integer.MAX_VALUE/2, maxTone = Integer.MIN_VALUE/2;
            for (int i = 0; i < songs.length; i++) {
                if ((bitMask & (1 << i)) != 0) {
                    currCount++;
                    currSum += songs[i].duration;
                    minTone = Math.min(minTone, songs[i].tone);
                    maxTone = Math.max(maxTone, songs[i].tone);
                }
            }
            if (currSum + maxTone - minTone <= T) {
                result = Math.max(result, currCount);
            }
            bitMask++;
        }
        return result;
    }

    public void test(int[] duration, int[] tone, int T) {
        System.out.printf("Input: duration=%s, tone=%s, T=%d;\nResult: %d\n-----\n",
                Arrays.toString(duration), Arrays.toString(tone), T, maxSongs(duration, tone, T));
    }

    public static void main(String[] args) {
        P72_GUMIAndSongsDiv2 gumi = new P72_GUMIAndSongsDiv2();
        gumi.test(new int[]{100, 200, 300}, new int[]{1, 2, 3}, 0);
        gumi.test(new int[]{1, 2, 3, 4}, new int[]{1, 1, 1, 1}, 100);
        gumi.test(new int[]{10, 10, 10}, new int[]{58, 58, 58}, 30);
        gumi.test(new int[]{8, 11, 7, 15, 9, 16, 7, 9}, new int[]{3, 8, 5, 4, 2, 7, 4, 1}, 14);
        gumi.test(
                new int[]{5611,39996,20200,56574,81643,90131,33486,99568,48112,97168,5600,49145,73590,3979,94614},
                new int[]{2916,53353,64924,86481,44803,61254,99393,5993,40781,2174,67458,74263,69710,40044,80853},
                302606);
    }
}
