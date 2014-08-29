package com.andreytim.jafar.algo.sort;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class SortsTest {

    private static final Logger LOG = LoggerFactory.getLogger(SortsTest.class);
    private static final int[] SIZES = new int[]{ 1, 2, 10, 100, 1000 };
    private static final boolean LOG_STATS = true;

    @Test
    public void testSelectionSort() {
        testSortingAlgorithm(new SelectionSort());
    }

    @Test
    public void testInsertionSort() {
        testSortingAlgorithm(new InsertionSort());
    }

    @Test
    public void testInsertionSwapInPlaceSort() {
        testSortingAlgorithm(new InsertionSwapInPlaceSort());
    }

    private void testSortingAlgorithm(AbstractSort sort) {
        if (LOG_STATS) sort.logStats();
        for (int size : SIZES) {
            if (LOG_STATS) LOG.info("TEST: size={}, type=ASC", size);
            List<Integer> list = SortUtils.getAscSortedIntArrayList(size);
            List<Integer> sorted = sort.sorted(list);
            Collections.sort(list);
            Assert.assertEquals(list, sorted);

            if (LOG_STATS) LOG.info("TEST: size={}, type=DESC", size);
            list = SortUtils.getDescSortedIntArrayList(size);
            sorted = sort.sorted(list);
            Collections.sort(list);
            Assert.assertEquals(list, sorted);

            if (LOG_STATS) LOG.info("TEST: size={}, type=RANDOM", size);
            list = SortUtils.getRandomIntArrayList(size);
            sorted = sort.sorted(list);
            Collections.sort(list);
            Assert.assertEquals(list, sorted);
        }
    }

}
