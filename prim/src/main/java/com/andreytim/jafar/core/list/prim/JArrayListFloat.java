package com.andreytim.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListFloat extends JAbstractList<Float> implements RandomAccess, Serializable {

    private static final long serialVersionUID = -5902022829146007341L;

    private float[] data = new float[DEFAULT_LENGTH];

    public JArrayListFloat() {}

    public JArrayListFloat(int capacity) {
        data = new float[capacity];
    }

    protected JArrayListFloat(float[] data) {
        this.data = data;
        this.size = data.length;
    }

    @Override
    public Class<?> getPrimType() {
        return float.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Float) {
            return contains(((Float) o).floatValue());
        }
        return false;
    }

    @Override
    public boolean contains(float e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Float e) {
        if (e == null) {
            return false;
        }
        return add(e.floatValue());
    }

    @Override
    public boolean add(float e) {
        data = grow(data, size + 1, size);
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Float) {
            float floatVal = ((Float) o).floatValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == floatVal) {
                    if (size > i-1) {
                        System.arraycopy(data, i + 1, data, i, size - i - 1);
                    }
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Float> c) {
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            int i = size;
            for (float e : c) {
                data[i++] = e;
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Float> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (float e : c) {
                data[index + i++] = e;
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int r = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(data[i])) {
                data[r++] = data[i];
            }
        }
        if (size - r > 0) {
            size = r;
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, Float element) {
        rangeCheck(index);
        data = grow(data, size + 1, size);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.floatValue();
    }

    @Override
    public Float remove(int index) {
        rangeCheck(index);
        float tmp = data[index];
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return Float.valueOf(tmp);
    }

    private float[] grow(float[] array, int length, int preserve) {
        if (length > array.length) {
            float[] newArray = new float[Math.min(growSize(array.length), ARRAY_LIST_MAX_SIZE)];
            System.arraycopy(array, 0, newArray, 0, preserve);
            return newArray;
        }
        return array;
    }

    @Override
    public void clear() {
        data = new float[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Float get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Float set(int index, Float element) {
        rangeCheck(index);
        float tmp = data[index];
        data[index] = element.floatValue();
        return Float.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        float tmp;
        if (o instanceof Float) {
            tmp = ((Float) o).floatValue();
        } else {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (data[i] == tmp) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }
        float tmp;
        if (o instanceof Float) {
            tmp = ((Float) o).floatValue();
        } else {
            return -1;
        }
        for (int i = size - 1; i > -1; i--) {
            if (data[i] == tmp) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<Float> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Float> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Float next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            if (i >= data.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return data[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                JArrayListFloat.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public float[] floats() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public float getFloat(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Float.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Float> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListFloat(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}