package com.andreytim.jafar.core.list;

import org.junit.Assert;

import java.util.List;

/**
 * Created by shpolsky on 15.07.14.
 */
public abstract class JAbstractTest {

    protected static final boolean ENABLE_OUTPUT = true;

    protected void checkStructure(int[] expected, List<Integer> actual) {
        Assert.assertEquals(expected.length, actual.size());
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual.get(i).intValue());
        }
    }

    protected void checkStructure(boolean[] expected, List<Boolean> actual) {
        Assert.assertEquals(expected.length, actual.size());
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual.get(i));
        }
    }

    protected void print(String msg) {
        if (ENABLE_OUTPUT) {
            System.out.print(msg);
        }
    }

    protected void ok() {
        if (ENABLE_OUTPUT) {
            System.out.print("OK\n");
        }
    }
}
