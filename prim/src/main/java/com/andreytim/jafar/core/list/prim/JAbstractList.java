package com.andreytim.jafar.core.list.prim;

import com.andreytim.jafar.core.prim.PrimTyped;

import java.util.*;

/**
 * Created by shpolsky on 19.07.14.
 */
public abstract class JAbstractList<E> extends AbstractList<E> implements JList<E>, PrimTyped {

    public static final int ARRAY_LIST_MAX_SIZE = Integer.MAX_VALUE >> 1;
    public static final int DEFAULT_LENGTH = 10;

    private static final String WRONG_TYPE_MSG =
            "Maybe you worked with elements of another type before? Current is \"%s\".";

    private String wrongTypeMessage(Class<?> callType) {
        String msg = String.format(WRONG_TYPE_MSG, callType.getSimpleName());
        if (this instanceof PrimTyped) {
            Class<?> clazz = ((PrimTyped) this).getPrimType();
            msg += " Original type was \"" + clazz.getSimpleName() + "\".";
        }
        return msg;
    }

    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified = (remove(o) || modified);
        }
        return modified;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("JArrayLists don't support listIterators so far! :(");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("JArrayLists don't support listIterators so far! :(");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("JArrayLists don't support a generic toArray() method! " +
                "Use " + getPrimType().getSimpleName() + "s() instead to obtain a safe copy of the internal array.");
    }

    @Override
    public boolean contains(byte e) {
        throw new UnsupportedOperationException(wrongTypeMessage(byte.class));
    }

    @Override
    public boolean contains(char e) {
        throw new UnsupportedOperationException(wrongTypeMessage(char.class));
    }

    @Override
    public boolean contains(short e) {
        throw new UnsupportedOperationException(wrongTypeMessage(short.class));
    }

    @Override
    public boolean contains(int e) {
        throw new UnsupportedOperationException(wrongTypeMessage(int.class));
    }

    @Override
    public boolean contains(long e) {
        throw new UnsupportedOperationException(wrongTypeMessage(long.class));
    }

    @Override
    public boolean contains(float e) {
        throw new UnsupportedOperationException(wrongTypeMessage(float.class));
    }

    @Override
    public boolean contains(double e) {
        throw new UnsupportedOperationException(wrongTypeMessage(double.class));
    }

    @Override
    public boolean contains(boolean e) {
        throw new UnsupportedOperationException(wrongTypeMessage(boolean.class));
    }

    @Override
    public boolean add(byte e) {
        throw new UnsupportedOperationException(wrongTypeMessage(byte.class));
    }

    @Override
    public boolean add(char e) {
        throw new UnsupportedOperationException(wrongTypeMessage(char.class));
    }

    @Override
    public boolean add(short e) {
        throw new UnsupportedOperationException(wrongTypeMessage(short.class));
    }

    @Override
    public boolean add(int e) {
        throw new UnsupportedOperationException(wrongTypeMessage(int.class));
    }

    @Override
    public boolean add(long e) {
        throw new UnsupportedOperationException(wrongTypeMessage(long.class));
    }

    @Override
    public boolean add(float e) {
        throw new UnsupportedOperationException(wrongTypeMessage(float.class));
    }

    @Override
    public boolean add(double e) {
        throw new UnsupportedOperationException(wrongTypeMessage(double.class));
    }

    @Override
    public boolean add(boolean e) {
        throw new UnsupportedOperationException(wrongTypeMessage(boolean.class));
    }

    @Override
    public int indexOf(byte e) {
        throw new UnsupportedOperationException(wrongTypeMessage(byte.class));
    }

    @Override
    public int indexOf(char e) {
        throw new UnsupportedOperationException(wrongTypeMessage(char.class));
    }

    @Override
    public int indexOf(short e) {
        throw new UnsupportedOperationException(wrongTypeMessage(short.class));
    }

    @Override
    public int indexOf(int e) {
        throw new UnsupportedOperationException(wrongTypeMessage(int.class));
    }

    @Override
    public int indexOf(long e) {
        throw new UnsupportedOperationException(wrongTypeMessage(long.class));
    }

    @Override
    public int indexOf(float e) {
        throw new UnsupportedOperationException(wrongTypeMessage(float.class));
    }

    @Override
    public int indexOf(double e) {
        throw new UnsupportedOperationException(wrongTypeMessage(double.class));
    }

    @Override
    public int indexOf(boolean e) {
        throw new UnsupportedOperationException(wrongTypeMessage(boolean.class));
    }

    @Override
    public int lastIndexOf(byte e) {
        throw new UnsupportedOperationException(wrongTypeMessage(byte.class));
    }

    @Override
    public int lastIndexOf(char e) {
        throw new UnsupportedOperationException(wrongTypeMessage(char.class));
    }

    @Override
    public int lastIndexOf(short e) {
        throw new UnsupportedOperationException(wrongTypeMessage(short.class));
    }

    @Override
    public int lastIndexOf(int e) {
        throw new UnsupportedOperationException(wrongTypeMessage(int.class));
    }

    @Override
    public int lastIndexOf(long e) {
        throw new UnsupportedOperationException(wrongTypeMessage(long.class));
    }

    @Override
    public int lastIndexOf(float e) {
        throw new UnsupportedOperationException(wrongTypeMessage(float.class));
    }

    @Override
    public int lastIndexOf(double e) {
        throw new UnsupportedOperationException(wrongTypeMessage(double.class));
    }

    @Override
    public int lastIndexOf(boolean e) {
        throw new UnsupportedOperationException(wrongTypeMessage(boolean.class));
    }

    @Override
    public char getChar(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(char.class));
    }

    @Override
    public short getShort(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(short.class));
    }

    @Override
    public int getInt(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(int.class));
    }

    @Override
    public float getFloat(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(float.class));
    }

    @Override
    public long getLong(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(long.class));
    }

    @Override
    public double getDouble(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(double.class));
    }

    @Override
    public boolean getBoolean(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(boolean.class));
    }

    @Override
    public byte getByte(int idx) {
        throw new UnsupportedOperationException(wrongTypeMessage(byte.class));
    }

    @Override
    public byte[] bytes() {
        throw new UnsupportedOperationException(wrongTypeMessage(byte.class));
    }

    @Override
    public char[] chars() {
        throw new UnsupportedOperationException(wrongTypeMessage(char.class));
    }

    @Override
    public short[] shorts() {
        throw new UnsupportedOperationException(wrongTypeMessage(short.class));
    }

    @Override
    public int[] ints() {
        throw new UnsupportedOperationException(wrongTypeMessage(int.class));
    }

    @Override
    public long[] longs() {
        throw new UnsupportedOperationException(wrongTypeMessage(long.class));
    }

    @Override
    public float[] floats() {
        throw new UnsupportedOperationException(wrongTypeMessage(float.class));
    }

    @Override
    public double[] doubles() {
        throw new UnsupportedOperationException(wrongTypeMessage(double.class));
    }

    @Override
    public boolean[] booleans() {
        throw new UnsupportedOperationException(wrongTypeMessage(boolean.class));
    }

    // protected util methods

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    protected String outOfBoundsMsg(int index) {
        return String.format("Index: %d, Size: %d", index, size);
    }

    protected int growSize(int size) {
        return size + (size >> 1) + 1;
    }
}
