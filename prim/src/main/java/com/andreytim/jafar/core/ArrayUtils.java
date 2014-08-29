package com.andreytim.jafar.core;

import java.util.Random;

/**
 * Created by shpolsky on 28.08.14.
 */
public class ArrayUtils {

    private static final Random RAND = new Random();

    public static void fill(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RAND.nextInt();
        }
    }

    public static void fillArithmeticProgression(int[] arr, int first, int step) {
        for (int i = first; i < arr.length; i++) {
            arr[i] = i + step;
        }
    }

}
