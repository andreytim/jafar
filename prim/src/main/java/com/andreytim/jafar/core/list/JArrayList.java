package com.andreytim.jafar.core.list;

import com.andreytim.jafar.core.prim.PrimTypeUtils;
import com.andreytim.jafar.core.list.prim.JList;

import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayList<E> extends AbstractList<E>
        implements JList<E>, RandomAccess, java.io.Serializable {

    private static final long serialVersionUID = -7777976475771634860L;

    private boolean inited = false;
    private JList<E> primitiveList;

    public JArrayList() {}

    @Override
    public int size() {
        return inited ? primitiveList.size() : 0;
    }

    @Override
    public boolean isEmpty() {
        return inited && primitiveList.isEmpty();
    }

    @Override
    public boolean contains(Object e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return primitiveList.get(index);
    }

    @Override
    public E set(int index, E e) {
        initCheck(e.getClass());
        return primitiveList.set(index, e);
    }

    @Override
    public boolean add(E e) {
        initCheck(e.getClass());
        return primitiveList.add(e);
    }

    @Override
    public void add(int index, E e) {
        initCheck(e.getClass());
        primitiveList.add(index, e);
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return primitiveList.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return inited && primitiveList.remove(o);
    }

    @Override
    public void clear() {
        if (inited) {
            primitiveList.clear();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (!c.isEmpty()) {
            // relying here on the fact that boxed primitive types are final and can't be inherited
            initCheck(c.iterator().next().getClass());
            return primitiveList.addAll(c);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (!c.isEmpty()) {
            // relying here on the fact that boxed primitive types are final and can't be inherited
            initCheck(c.iterator().next().getClass());
            return primitiveList.addAll(index, c);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (!c.isEmpty()) {
            // relying here on the fact that boxed primitive types are final and can't be inherited
            initCheck(c.iterator().next().getClass());
            return primitiveList.removeAll(c);
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (!c.isEmpty()) {
            // relying here on the fact that boxed primitive types are final and can't be inherited
            initCheck(c.iterator().next().getClass());
            return primitiveList.retainAll(c);
        }
        return false;
    }

    @Override
    public ListIterator<E> listIterator() {
        return inited ? primitiveList.listIterator() : super.listIterator();
    }

    @Override
    public Iterator<E> iterator() {
        return inited ? primitiveList.iterator() : super.iterator();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        return primitiveList.subList(fromIndex, toIndex);
    }

    @Override
    public int indexOf(Object e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(Object e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public Object[] toArray() {
        return inited ? primitiveList.toArray() : new Object[]{};
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        return inited ? primitiveList.toArray(arr) : (T[]) new Object[]{};
    }

    @Override
    public String toString() {
        return inited ? primitiveList.toString() : "[]";
    }

    // primitive methods implementations

    @Override
    public boolean contains(byte e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(short e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(int e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(long e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(float e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(double e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(char e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean contains(boolean e) {
        return inited && primitiveList.contains(e);
    }

    @Override
    public boolean add(byte e) {
        initCheck(byte.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(char e) {
        initCheck(char.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(short e) {
        initCheck(short.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(int e) {
        initCheck(int.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(long e) {
        initCheck(long.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(float e) {
        initCheck(float.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(double e) {
        initCheck(double.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(boolean e) {
        initCheck(boolean.class);
        return primitiveList.add(e);
    }

    @Override
    public int indexOf(byte e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(char e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(short e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(int e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(long e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(float e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(double e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int indexOf(boolean e) {
        return inited ? primitiveList.indexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(byte e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(char e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(short e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(int e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(long e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(float e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(double e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public int lastIndexOf(boolean e) {
        return inited ? primitiveList.lastIndexOf(e) : -1;
    }

    @Override
    public byte getByte(int idx) {
        rangeCheck(idx);
        return primitiveList.getByte(idx);
    }

    @Override
    public char getChar(int idx) {
        rangeCheck(idx);
        return primitiveList.getChar(idx);
    }

    @Override
    public short getShort(int idx) {
        rangeCheck(idx);
        return primitiveList.getShort(idx);
    }

    @Override
    public int getInt(int idx) {
        rangeCheck(idx);
        return primitiveList.getInt(idx);
    }

    @Override
    public long getLong(int idx) {
        rangeCheck(idx);
        return primitiveList.getLong(idx);
    }

    @Override
    public float getFloat(int idx) {
        rangeCheck(idx);
        return primitiveList.getFloat(idx);
    }

    @Override
    public double getDouble(int idx) {
        rangeCheck(idx);
        return primitiveList.getDouble(idx);
    }

    @Override
    public boolean getBoolean(int idx) {
        rangeCheck(idx);
        return primitiveList.getBoolean(idx);
    }

    @Override
    public byte[] bytes() {
        return inited ? primitiveList.bytes() : new byte[]{};
    }

    @Override
    public char[] chars() {
        return inited ? primitiveList.chars() : new char[]{};
    }

    @Override
    public short[] shorts() {
        return inited ? primitiveList.shorts() : new short[]{};
    }

    @Override
    public int[] ints() {
        return inited ? primitiveList.ints() : new int[]{};
    }

    @Override
    public long[] longs() {
        return inited ? primitiveList.longs() : new long[]{};
    }

    @Override
    public float[] floats() {
        return inited ? primitiveList.floats() : new float[]{};
    }

    @Override
    public double[] doubles() {
        return inited ? primitiveList.doubles() : new double[]{};
    }

    @Override
    public boolean[] booleans() {
        return inited ? primitiveList.booleans() : new boolean[]{};
    }

    // private util methods

    @SuppressWarnings("unchecked")
    private void initCheck(Class<?> clazz) {
        if (!inited) {
            // unchecked -  we suppose that we always find the proper instance of primitive JList
            primitiveList = PrimTypeUtils.getTypedPrimInstance(clazz, JArrayList.class);
            if (primitiveList == null) {
                throw new IllegalArgumentException("Unsupported type for primitive collection! Type: " + clazz);
            }
            inited = true;
        }
    }

    private void rangeCheck(int index) {
        if (!inited) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: 0";
    }
}
