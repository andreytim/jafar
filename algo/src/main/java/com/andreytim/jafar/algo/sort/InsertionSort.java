package com.andreytim.jafar.algo.sort;

import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class InsertionSort extends AbstractSort {
    @Override
    protected <T extends Comparable<T>> void performSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && less(list.get(j), list.get(j-1))) {
                swap(list, j, j-1);
                j--;
            }
        }
    }
}
