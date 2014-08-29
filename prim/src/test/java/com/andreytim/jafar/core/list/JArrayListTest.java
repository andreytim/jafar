package com.andreytim.jafar.core.list;

import com.andreytim.jafar.core.list.prim.JList;
import com.andreytim.jafar.core.prim.PrimType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListTest {

    private static final EnumSet<PrimType> typesToCheck = EnumSet.of(
            PrimType.BYTE, PrimType.SHORT, PrimType.INT, PrimType.LONG,
            PrimType.FLOAT, PrimType.DOUBLE, PrimType.CHAR, PrimType.BOOLEAN);
    private static final boolean ENABLE_OUTPUT = true;

    @Test
    public void testCreation() {
        print("Create array lists of these types " + typesToCheck + ": ");
        for (PrimType pt : typesToCheck) {
            Lists.createJArrayList(pt);
            Lists.createJJArrayList(pt);
        }
        ok();
    }

    @Test
    public void testAddAndContains() {
        List<Integer> l1 = new JArrayList<Integer>();
        print("Check if empty JArrayList<Integer> contains 1: ");
        Assert.assertFalse(l1.contains(1));
        ok();
        l1.add(1);
        print("Add 1 and check again: ");
        Assert.assertTrue(l1.contains(1));
        ok();
        JList<Integer> l2 = new JArrayList<Integer>();
        print("Check if empty JJArrayList<Integer> contains 1: ");
        Assert.assertFalse(l2.contains(1));
        ok();
        l2.add(1);
        print("Add 1 and check again: ");
        Assert.assertTrue(l2.contains(1));
        ok();
    }

    @Test
    public void testRemove() {
        List<Integer> l1 = new JArrayList<Integer>();
        print("Add 1,2,3,4,5 to JArrayList<Integer>.\n");
        l1.add(1); l1.add(2); l1.add(3); l1.add(4); l1.add(5);
        print("Check if everything is within it: ");
        Assert.assertTrue(l1.contains(1) || l1.contains(2) || l1.contains(3) || l1.contains(4) || l1.contains(5));
        Assert.assertEquals(l1.size(), 5);
        ok();
        print("Remove 1,2 from the list.\n");
        l1.remove(Integer.valueOf(1));
        l1.remove(Integer.valueOf(2));
        print("Check if removed: ");
        Assert.assertFalse(l1.contains(1));
        Assert.assertFalse(l1.contains(2));
        Assert.assertEquals(l1.size(), 3);
        ok();
    }

    @Test
    public void testSetGetAndIndexOf() {
        List<Integer> list = new JArrayList<Integer>();
        print("Add 1,2,3,4,5 to JArrayList<Integer>.\n");
        list.add(1); list.add(2); list.add(3); list.add(4); list.add(5);
        print("Check if everything is on the right position: ");
        checkStructure(new int[]{1, 2, 3, 4, 5}, list);
        ok();
        print("Set 10 to 2d and 3d positions.\n");
        list.set(2, 10);
        list.set(3, 10);
        print("Check if size didn't change: ");
        Assert.assertEquals(list.size(), 5);
        ok();
        print("Check if indexOf(10) is 2: ");
        Assert.assertEquals(list.indexOf(10), 2);
        ok();
        print("Check if lastIndexOf(10) is 3: ");
        Assert.assertEquals(list.lastIndexOf(10), 3);
        ok();
    }

    @Test
    public void testAddRemoveAndRetainAll() {
        List<Integer> list = new JArrayList<Integer>();

        print("Add 1,2,3,4,5 with addAll to JArrayList<Integer>.\n");
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        print("Check if everything is in the right place: ");
        checkStructure(new int[]{1, 2, 3, 4, 5}, list);
        ok();

        print("Add 10,11 to with addAll 2 position in the list.\n");
        list.addAll(2, Arrays.asList(10, 11));
        print("Check if everything is on the right place: ");
        checkStructure(new int[]{1, 2, 10, 11, 3, 4, 5}, list);
        ok();

        print("Remove 1,2,5 with removeAll from the list.\n");
        list.removeAll(Arrays.asList(1, 2, 5));
        print("Check if everything is on the right place: ");
        checkStructure(new int[]{10, 11, 3, 4}, list);
        ok();

        print("Retain 10,11 with retainAll from the list.\n");
        list.retainAll(Arrays.asList(10, 11));
        print("Check if everything is on the right place: ");
        checkStructure(new int[]{10, 11}, list);
        ok();
    }

    @Test
    public void testClearAndEdgeCases() {
        List<Integer> list = new JArrayList<>();

        print("Add 1,2,3,4,5 with addAll to JArrayList<Integer>.\n");
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        print("Check if everything is in the right place: ");
        checkStructure(new int[]{1, 2, 3, 4, 5}, list);
        ok();
        print("Clear the list.\n");
        list.clear();
        print("Check if the list is empty and size == 0: ");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(0, list.size());
        ok();

        print("Add 1 with addAll to the list.\n");
        list.addAll(Arrays.asList(1));
        print("Check if everything is in the right place: ");
        checkStructure(new int[]{1}, list);
        ok();
        print("Remove 1 from the list.\n");
        list.remove(Integer.valueOf(1));
        print("Check if the list is empty and size == 0: ");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(0, list.size());
        ok();

        print("Add 1 with add to the list.\n");
        list.add(1);
        print("Check if everything is in the right place: ");
        checkStructure(new int[]{1}, list);
        ok();
        print("Remove index 0 from the list.\n");
        list.remove(0);
        print("Check if the list is empty and size == 0: ");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(0, list.size());
        ok();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBounds1() {
        List<Integer> list = new JArrayList<Integer>();
        list.set(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBounds2() {
        List<Integer> list = new JArrayList<Integer>();
        list.addAll(Arrays.asList(1, 2, 3));
        list.set(4, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds1() {
        List<Integer> list = new JArrayList<Integer>();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds2() {
        List<Integer> list = new JArrayList<Integer>();
        list.addAll(Arrays.asList(1, 2, 3));
        list.get(4);
    }

    private void checkStructure(int[] expected, List<Integer> actual) {
        Assert.assertEquals(expected.length, actual.size());
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual.get(i).intValue());
        }
    }

    private void print(String msg) {
        if (ENABLE_OUTPUT) {
            System.out.print(msg);
        }
    }

    private void ok() {
        if (ENABLE_OUTPUT) {
            System.out.print("OK\n");
        }
    }
}
