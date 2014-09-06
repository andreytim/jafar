package com.andreytim.jafar.core.list;

import com.andreytim.jafar.core.list.prim.JAbstractList;
import com.andreytim.jafar.core.prim.PrimTypeUtils;
import com.andreytim.jafar.core.list.prim.JList;

import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayList<E> extends AbstractList<E>
        implements JList<E>, RandomAccess, java.io.Serializable {

    private static final long serialVersionUID = -7777976475771634860L;

    /** Initial capacity for lazy initialization of internal primitive list. */
    private final int capacity;

    private boolean notInited = true;
    private boolean inited = false;

    private JList<E> primitiveList;

    public JArrayList() {
        this(JAbstractList.DEFAULT_LENGTH);
    }

    public JArrayList(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return notInited ? 0 : primitiveList.size();
    }

    @Override
    public boolean isEmpty() {
        return notInited || primitiveList.isEmpty();
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
        return notInited ? super.listIterator() : primitiveList.listIterator();
    }

    @Override
    public Iterator<E> iterator() {
        return notInited ? super.iterator() : primitiveList.iterator();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        return primitiveList.subList(fromIndex, toIndex);
    }

    @Override
    public int indexOf(Object e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int lastIndexOf(Object e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public Object[] toArray() {
        return notInited ? new Object[]{} : primitiveList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        return notInited ? (T[]) new Object[]{} : primitiveList.toArray(arr);
    }

    @Override
    public String toString() {
        return notInited ? "[]" : primitiveList.toString();
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
        if (notInited) init(byte.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(char e) {
        if (notInited) init(char.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(short e) {
        if (notInited) init(short.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(int e) {
        if (notInited) init(int.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(long e) {
        if (notInited) init(long.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(float e) {
        if (notInited) init(float.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(double e) {
        if (notInited) init(double.class);
        return primitiveList.add(e);
    }

    @Override
    public boolean add(boolean e) {
        if (notInited) init(boolean.class);
        return primitiveList.add(e);
    }

    @Override
    public int indexOf(byte e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(char e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(short e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(int e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(long e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(float e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(double e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int indexOf(boolean e) {
        return notInited ? -1 : primitiveList.indexOf(e);
    }

    @Override
    public int lastIndexOf(byte e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(char e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(short e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(int e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(long e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(float e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(double e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
    }

    @Override
    public int lastIndexOf(boolean e) {
        return notInited ? -1 : primitiveList.lastIndexOf(e);
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
        return notInited ? new byte[]{} : primitiveList.bytes();
    }

    @Override
    public char[] chars() {
        return notInited ? new char[]{} : primitiveList.chars();
    }

    @Override
    public short[] shorts() {
        return notInited ? new short[]{} : primitiveList.shorts();
    }

    @Override
    public int[] ints() {
        return notInited ? new int[]{} : primitiveList.ints();
    }

    @Override
    public long[] longs() {
        return notInited ? new long[]{} : primitiveList.longs();
    }

    @Override
    public float[] floats() {
        return notInited ? new float[]{} : primitiveList.floats();
    }

    @Override
    public double[] doubles() {
        return notInited ? new double[]{} : primitiveList.doubles();
    }

    @Override
    public boolean[] booleans() {
        return notInited ? new boolean[]{} : primitiveList.booleans();
    }

    // private util methods

    private void initCheck(Class<?> clazz) {
        if (notInited) {
            init(clazz);
        }
    }

    @SuppressWarnings("unchecked")
    private void init(Class<?> clazz) {
        // unchecked -  we suppose that we always find the proper instance of primitive JList
        primitiveList = PrimTypeUtils.getTypedPrimInstance(clazz, JArrayList.class, capacity);
        if (primitiveList == null) {
            throw new IllegalArgumentException("Unsupported type for primitive collection! Type: " + clazz);
        }
        notInited = false;
        inited = true;
    }

    private void rangeCheck(int index) {
        if (notInited) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: 0";
    }
}
