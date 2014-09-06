package com.andreytim.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListByte extends JAbstractList<Byte> implements RandomAccess, Serializable {

    private static final long serialVersionUID = 4260667938180140700L;

    private byte[] data = new byte[DEFAULT_LENGTH];

    public JArrayListByte() {}

    public JArrayListByte(int capacity) {
        data = new byte[capacity];
    }

    protected JArrayListByte(byte[] data) {
        this.data = data;
        this.size = data.length;
    }

    @Override
    public Class<?> getPrimType() {
        return byte.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Byte) {
            return contains(((Byte) o).byteValue());
        }
        return false;
    }

    @Override
    public boolean contains(byte e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Byte e) {
        if (e == null) {
            return false;
        }
        return add(e.byteValue());
    }

    @Override
    public boolean add(byte e) {
        data = grow(data, size + 1, size);
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Byte) {
            byte byteVal = ((Byte) o).byteValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == byteVal) {
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
    public boolean addAll(Collection<? extends Byte> c) {
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            int i = size;
            for (byte e : c) {
                data[i++] = e;
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Byte> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (byte e : c) {
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
    public void add(int index, Byte element) {
        rangeCheck(index);
        data = grow(data, size + 1, size);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.byteValue();
    }

    @Override
    public Byte remove(int index) {
        rangeCheck(index);
        byte tmp = data[index];
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return Byte.valueOf(tmp);
    }

    private byte[] grow(byte[] array, int length, int preserve) {
        if (length > array.length) {
            byte[] newArray = new byte[Math.min(array.length << 1, ARRAY_LIST_MAX_SIZE)];
            System.arraycopy(array, 0, newArray, 0, preserve);
            return newArray;
        }
        return array;
    }

    @Override
    public void clear() {
        data = new byte[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Byte get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Byte set(int index, Byte element) {
        rangeCheck(index);
        byte tmp = data[index];
        data[index] = element.byteValue();
        return Byte.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        byte tmp;
        if (o instanceof Byte) {
            tmp = ((Byte) o).byteValue();
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
        byte tmp;
        if (o instanceof Byte) {
            tmp = ((Byte) o).byteValue();
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
    public Iterator<Byte> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Byte> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Byte next() {
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
                JArrayListByte.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public byte[] bytes() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public byte getByte(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Byte.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Byte> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListByte(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}