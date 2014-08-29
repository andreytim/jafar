package com.andreytim.jafar.algo.sort;

import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class InsertionSwapInPlaceSort extends AbstractSort {
    @Override
    protected <T extends Comparable<T>> void performSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T tmp = list.get(i);
            int j = i;
            while (j > 0 && less(tmp, list.get(j-1))) {
                list.set(j, list.get(j-1));
                j--;
            }
            list.set(j, tmp);
        }
    }
}
