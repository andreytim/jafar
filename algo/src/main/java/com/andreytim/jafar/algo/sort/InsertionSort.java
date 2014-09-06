package com.andreytim.jafar.algo.sort;

import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class InsertionSort extends AbstractSort {

    public static enum Mode {
        SWAPS, SHIFTS;
    }

    private final Mode mode;

    public InsertionSort() {
        this(Mode.SHIFTS);
    }

    public InsertionSort(Mode mode) {
        this.mode = mode;
    }

    @Override
    protected <T extends Comparable<T>> void performSort(List<T> list) {
        switch (mode) {
            case SWAPS: sortThroughSwaps(list); break;
            case SHIFTS: sortThroughShifts(list); break;
        }
    }

    /**
     * The most straightforward implementation.
     *
     * @param list
     * @param <T>
     */
    private <T extends Comparable<T>> void sortThroughSwaps(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && less(list.get(j), list.get(j-1))) {
                swap(list, j, j-1);
                j--;
            }
        }
    }

    /**
     * Implementation with tiny optimization - getting rid of redundant swaps
     * through shifting and temporary local variable.
     *
     * @param list
     * @param <T>
     */
    private <T extends Comparable<T>> void sortThroughShifts(List<T> list) {
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
