package com.andreytim.jafar.bm.list;

import cern.colt.list.IntArrayList;
import gnu.trove.list.array.TIntArrayList;

import java.util.List;
import java.util.Random;

/**
 * Created by shpolsky on 27.08.14.
 */
public class BmListUtils {

    private static final Random RAND = new Random();

    public static void fill(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void fill(IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void fill(TIntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
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

    public static void fillArithmeticProgression(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
    }

    public static void fillArithmeticProgression(IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
    }

    public static void fillArithmeticProgression(TIntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
    }

    public static void fillArithmeticProgression(it.unimi.dsi.fastutil.ints.IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
    }

    public static void fillArithmeticProgression(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }

}
