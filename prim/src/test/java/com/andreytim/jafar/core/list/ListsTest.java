package com.andreytim.jafar.core.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class ListsTest {

    private static final int SIZE = 1_0;

    private List<Integer> INT_AL_SORTED = new ArrayList<>();
    private List<Integer> INT_AL_SORTED_REVERSED = new ArrayList<>();

    @Test
    public void testFilling() {
        Lists.refillArithmeticProgression(INT_AL_SORTED, SIZE, 1, 1);
        Lists.refillArithmeticProgression(INT_AL_SORTED_REVERSED, SIZE, SIZE, -1);
        Assert.assertTrue(
                Arrays.equals(INT_AL_SORTED.toArray(), new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        Assert.assertTrue(
                Arrays.equals(INT_AL_SORTED_REVERSED.toArray(), new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

}
