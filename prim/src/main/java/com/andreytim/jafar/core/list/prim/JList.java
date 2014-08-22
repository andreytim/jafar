package com.andreytim.jafar.core.list.prim;

import java.util.List;

/**
 * Created by shpolsky on 19.07.14.
 */
public interface JList<E> extends List<E> {

    boolean contains(byte e);
    boolean contains(char e);
    boolean contains(short e);
    boolean contains(int e);
    boolean contains(long e);
    boolean contains(float e);
    boolean contains(double e);
    boolean contains(boolean e);

    boolean add(byte e);
    boolean add(char e);
    boolean add(short e);
    boolean add(int e);
    boolean add(long e);
    boolean add(float e);
    boolean add(double e);
    boolean add(boolean e);

    int indexOf(byte e);
    int indexOf(char e);
    int indexOf(short e);
    int indexOf(int e);
    int indexOf(long e);
    int indexOf(float e);
    int indexOf(double e);
    int indexOf(boolean e);

    int lastIndexOf(byte e);
    int lastIndexOf(char e);
    int lastIndexOf(short e);
    int lastIndexOf(int e);
    int lastIndexOf(long e);
    int lastIndexOf(float e);
    int lastIndexOf(double e);
    int lastIndexOf(boolean e);

    byte getByte(int idx);
    char getChar(int idx);
    short getShort(int idx);
    int getInt(int idx);
    long getLong(int idx);
    float getFloat(int idx);
    double getDouble(int idx);
    boolean getBoolean(int idx);

    byte[] bytes();
    char[] chars();
    short[] shorts();
    int[] ints();
    long[] longs();
    float[] floats();
    double[] doubles();
    boolean[] booleans();
}
