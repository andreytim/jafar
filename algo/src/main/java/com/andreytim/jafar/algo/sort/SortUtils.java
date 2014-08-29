package com.andreytim.jafar.algo.sort;

import com.andreytim.jafar.core.list.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class SortUtils {

    public static <T> void swap(List<T> list, int i, int j) {
        if (i != j) {
            T tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    public static List<Integer> getAscSortedIntArrayList(int size) {
        List<Integer> res = new ArrayList<>();
        Lists.fillArithmeticProgression(res, size, 1, 1);
        return res;
    }

    public static List<Integer> getDescSortedIntArrayList(int size) {
        List<Integer> res = new ArrayList<>();
        Lists.fillArithmeticProgression(res, size, size, -1);
        return res;
    }

    public static List<Integer> getRandomIntArrayList(int size) {
        List<Integer> res = new ArrayList<>();
        Lists.fill(res, size);
        return res;
    }

}
