package com.andreytim.jafar.algo.sort;

import java.util.List;

/**
 * Main interface for all sorting algorithms.
 * The main method for implementing in subclasses is {@link #sort(java.util.List)}.
 * The others are rather a syntactic sugar for specific cases and several utilities.
 *
 * Created by shpolsky on 28.08.14.
 */
public interface Sort {

    /**
     * Sorts the list according to the ascending order provided by comparable type parameter
     * and does it in-place changing the list's internal state.
     *
     * @param list
     * @param <T>
     */
    public <T extends Comparable<T>> void sort(List<T> list);

    /**
     * Sorts the list according to the ascending order provided by comparable type parameter.
     * Does it in-place changing the list's internal state and returns it to the caller.
     *
     * @param list
     * @param <T>
     * @return the same list, but sorted
     */
    public <T extends Comparable<T>> List<T> sortAndReturn(List<T> list);

    /**
     * Makes a copy of the input list and sorts it according to the ascending order
     * provided by comparable type parameter. Then returns that copy to the caller.
     *
     * @param list
     * @param <T>
     * @return a new sorted list consisting of the elements from the input list
     */
    public <T extends Comparable<T>> List<T> sorted(List<T> list);

    /**
     * Name of the sorting algorithm for debugging and logging purposes.
     *
     * @return sort's name
     */
    public String getName();
}
