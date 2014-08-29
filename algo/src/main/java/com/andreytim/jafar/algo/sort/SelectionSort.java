package com.andreytim.jafar.algo.sort;

import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class SelectionSort extends AbstractSort {
    @Override
    protected <T extends Comparable<T>> void performSort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (less(list.get(j), list.get(minIdx))) {
                    minIdx = j;
                }
            }
            swap(list, i, minIdx);
        }
    }
}
