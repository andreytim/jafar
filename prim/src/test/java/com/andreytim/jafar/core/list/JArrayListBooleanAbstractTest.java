package com.andreytim.jafar.core.list;

import com.andreytim.jafar.core.list.prim.JList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListBooleanAbstractTest extends JAbstractTest {

    @Test
    public void testAddAndContains() {
        List<Boolean> l1 = new JArrayList<>();
        print("Check if empty JArrayList<Boolean> contains 'true': ");
        Assert.assertFalse(l1.contains(true));
        ok();
        l1.add(true);
        print("Add 'true' and check again: ");
        Assert.assertTrue(l1.contains(true));
        ok();
        JList<Boolean> l2 = new JArrayList<>();
        print("Check if empty JJArrayList<Boolean> contains 'true': ");
        Assert.assertFalse(l2.contains(true));
        ok();
        l2.add(true);
        print("Add 'true' and check again: ");
        Assert.assertTrue(l2.contains(true));
        ok();
    }

    @Test
    public void testRemove() {
        List<Boolean> l1 = new JArrayList<>();
        print("Add true,false,false,true,true to JArrayList<Boolean>.\n");
        l1.add(true); l1.add(false); l1.add(false); l1.add(true); l1.add(true);
        print("Check if everything is within it: ");
        Assert.assertTrue(l1.contains(true) && l1.contains(false));
        Assert.assertEquals(l1.size(), 5);
        ok();
        print("Remove true from the list.\n");
        l1.remove(true);
        print("Check if removed: ");
        Assert.assertFalse(l1.contains(true));
        Assert.assertEquals(l1.size(), 2);
        ok();
    }

    @Test
    public void testSetGetAndIndexOf() {
        List<Boolean> list = new JArrayList<>();
        print("Add true,false,false,true,true to JArrayList<Boolean>.\n");
        list.add(true); list.add(false); list.add(false); list.add(true); list.add(true);
        print("Check if everything is on the right position: ");
        checkStructure(new boolean[]{true, false, false, true, true}, list);
        ok();
        print("Set false to 3d position.\n");
        list.set(3, false);
        print("Check if size didn't change: ");
        Assert.assertEquals(list.size(), 5);
        ok();
        print("Check if indexOf(false) is 1: ");
        Assert.assertEquals(list.indexOf(false), 1);
        ok();
        print("Check if lastIndexOf(false) is 3: ");
        Assert.assertEquals(list.lastIndexOf(false), 3);
        ok();
    }

    @Test
    public void testAddRemoveAndRetainAll() {
        List<Boolean> list = new JArrayList<>();

        print("Add true,false,false,true,true with addAll to JArrayList<Boolean>.\n");
        list.addAll(Arrays.asList(true, false, false, true, true));
        print("Check if everything is in the right place: ");
        checkStructure(new boolean[]{true, false, false, true, true}, list);
        ok();

        print("Add false,true with addAll to 2 position in the list.\n");
        list.addAll(2, Arrays.asList(false, true));
        print("Check if everything is on the right place: ");
        checkStructure(new boolean[]{true, false, false, true, false, true, true}, list);
        ok();

        print("Remove false with removeAll from the list.\n");
        list.removeAll(Arrays.asList(false));
        print("Check if everything is on the right place: ");
        checkStructure(new boolean[]{true, true, true, true}, list);
        ok();

        print("Retain true with retainAll from the list.\n");
        list.retainAll(Arrays.asList(true));
        print("Check if everything is on the right place: ");
        checkStructure(new boolean[]{true, true, true, true}, list);
        ok();
    }

    @Test
    public void testClearAndEdgeCases() {
        List<Boolean> list = new JArrayList<>();

        print("Add true,false,false,true,true with addAll to JArrayList<Boolean>.\n");
        list.addAll(Arrays.asList(true, false, false, true, true));
        print("Check if everything is in the right place: ");
        checkStructure(new boolean[]{true, false, false, true, true}, list);
        ok();
        print("Clear the list.\n");
        list.clear();
        print("Check if the list is empty and size == 0: ");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(0, list.size());
        ok();

        print("Add true with addAll to the list.\n");
        list.addAll(Arrays.asList(true));
        print("Check if everything is in the right place: ");
        checkStructure(new boolean[]{true}, list);
        ok();
        print("Remove 1 from the list.\n");
        list.remove(true);
        print("Check if the list is empty and size == 0: ");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(0, list.size());
        ok();

        print("Add false with add to the list.\n");
        list.add(false);
        print("Check if everything is in the right place: ");
        checkStructure(new boolean[]{false}, list);
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
        List<Boolean> list = new JArrayList<>();
        list.set(0, true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBounds2() {
        List<Boolean> list = new JArrayList<>();
        list.addAll(Arrays.asList(true, false, true));
        list.set(4, false);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds1() {
        List<Boolean> list = new JArrayList<>();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds2() {
        List<Boolean> list = new JArrayList<>();
        list.addAll(Arrays.asList(true, true, true));
        list.get(4);
    }
}
