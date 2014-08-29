package com.andreytim.jafar.algo.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public abstract class AbstractSort implements Sort {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSort.class);

    private boolean logStats = false;
    private int comparisons;
    private int swaps;

    @Override
    public final <T extends Comparable<T>> void sort(List<T> list) {
        if (logStats) {
            resetStats();
        }
        performSort(list);
        if (logStats) {
            LOG.info("[SORT-STATS][{}] size={}, comparisons={}, swaps={}", getName(), list.size(), comparisons, swaps);
        }
    }

    protected abstract <T extends Comparable<T>> void performSort(List<T> list);

    @Override
    public final <T extends Comparable<T>> List<T> sortAndReturn(List<T> list) {
        sort(list);
        return list;
    }

    @Override
    public final <T extends Comparable<T>> List<T> sorted(List<T> list) {
        List<T> clone = new ArrayList<>(list);
        sort(clone);
        return clone;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    protected <T extends Comparable<T>> boolean greater(T left, T right) {
        comparisons++;
        return left.compareTo(right) > 0;
    }

    protected <T extends Comparable<T>> boolean less(T left, T right) {
        comparisons++;
        return left.compareTo(right) < 0;
    }

    protected <T> void swap(List<T> list, int i, int j) {
        if (i != j) {
            swaps++;
            T tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    private void resetStats() {
        comparisons = 0;
        swaps = 0;
    }

    public Sort logStats() {
        this.logStats = true;
        return this;
    }
}
