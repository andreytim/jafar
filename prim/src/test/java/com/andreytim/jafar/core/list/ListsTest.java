package com.andreytim.jafar.core.list;

import com.andreytim.jafar.core.prim.PrimType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by shpolsky on 28.08.14.
 */
public class ListsTest extends JAbstractTest {

    protected static final EnumSet<PrimType> typesToCheck = EnumSet.of(
            PrimType.BYTE, PrimType.SHORT, PrimType.INT, PrimType.LONG,
            PrimType.FLOAT, PrimType.DOUBLE, PrimType.CHAR, PrimType.BOOLEAN);

    @Test
    public void testCreation() {
        print("Create array lists of these types " + typesToCheck + ": ");
        for (PrimType pt : typesToCheck) {
            Lists.createJArrayList(pt);
            Lists.createJJArrayList(pt);
        }
        ok();
    }

    private static final int SIZE = 1_0;

    @Test
    public void testFilling() {
        List<Integer> intALSorted = new ArrayList<>();
        List<Integer> intALSortedReversed = new ArrayList<>();
        Lists.refillArithmeticProgression(intALSorted, SIZE, 1, 1);
        Lists.refillArithmeticProgression(intALSortedReversed, SIZE, SIZE, -1);
        Assert.assertTrue(Arrays.equals(intALSorted.toArray(), new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        Assert.assertTrue(Arrays.equals(intALSortedReversed.toArray(), new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

}
