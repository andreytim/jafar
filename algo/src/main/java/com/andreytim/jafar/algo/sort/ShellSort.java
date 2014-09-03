package com.andreytim.jafar.algo.sort;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class ShellSort extends AbstractSort {

    private Deque<Integer> gaps = new LinkedList<>();

    @Override
    protected <T extends Comparable<T>> void performSort(List<T> list) {
        initGaps(list.size());
        for (int gap : gaps) {
            for (int i = gap; i < list.size(); i++) {
                T tmp = list.get(i);
                // todo
            }
        }
    }

    private void initGaps(int n) {
        if (!gaps.isEmpty()) {
            gaps.clear();
        }
        int h = 1;
        while (h < n / 2.25D) {
            gaps.push(h);
            h *= 2.25D;
        }
    }
}
