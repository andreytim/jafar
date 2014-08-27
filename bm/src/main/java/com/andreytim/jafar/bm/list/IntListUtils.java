package com.andreytim.jafar.bm.list;

import cern.colt.list.IntArrayList;
import gnu.trove.list.array.TIntArrayList;

import java.util.List;
import java.util.Random;

/**
 * Created by shpolsky on 27.08.14.
 */
public class IntListUtils {

    private static final Random RAND = new Random();

    public static void refill(List<Integer> list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refill(IntArrayList list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refill(TIntArrayList list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(TIntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refill(it.unimi.dsi.fastutil.ints.IntArrayList list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(it.unimi.dsi.fastutil.ints.IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void fill(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RAND.nextInt();
        }
    }

    public static void refillArithmeticProgression(List<Integer> list, int size, int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(List<Integer> list, int size, int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

    public static void refillArithmeticProgression(IntArrayList list, int size, int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(IntArrayList list, int size, int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

    public static void refillArithmeticProgression(TIntArrayList list, int size, int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(TIntArrayList list, int size, int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

    public static void refillArithmeticProgression(it.unimi.dsi.fastutil.ints.IntArrayList list, int size,
                                                   int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(it.unimi.dsi.fastutil.ints.IntArrayList list, int size,
                                                 int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

    public static void fillArithmeticProgression(int[] arr, int first, int step) {
        for (int i = first; i < arr.length; i++) {
            arr[i] = i + step;
        }
    }

}
