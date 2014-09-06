package com.andreytim.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Specifically implemented bitwise boolean ArrayList.
 * It needs eight times less memory than any other primitive implementations
 * but has tiny performance overhead for slightly more operations during addition
 *
 * Created by shpolsky on 06.09.14.
 */
public class JArrayListBoolean extends JAbstractList<Boolean> implements RandomAccess, Serializable {

    private static final long serialVersionUID = -8975253306985770066L;

    private byte[] data = new byte[DEFAULT_LENGTH / 8 + 1];

    public JArrayListBoolean() {}

    public JArrayListBoolean(int capacity) {
        data = new byte[capacity / 8 + 1];
    }

    protected JArrayListBoolean(boolean[] data) {
        for (boolean b : data) {
            add(b);
        }
        this.size = data.length;
    }

    private void setBit(boolean bit, int idx) {
        int byteIdx = idx / 8;
        byte bitIdx = (byte) (idx % 8);
        if (bit) {
            data[byteIdx] |= 1 << bitIdx;
        } else {
            data[byteIdx] &= ~(1 << bitIdx);
        }
    }

    private boolean getBit(int idx) {
        int byteIdx = idx / 8;
        byte bitIdx = (byte) (idx % 8);
        return ((data[byteIdx] >> bitIdx) & 1) == 1;
    }

    private boolean checkBit(int idx, boolean val) {
        return getBit(idx) == val;
    }

    @Override
    public Class<?> getPrimType() {
        return boolean.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Boolean) {
            return contains(((Boolean) o).booleanValue());
        }
        return false;
    }

    @Override
    public boolean contains(boolean e) {
        for (int i = 0; i < size; i++) {
            if (checkBit(i, e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Boolean e) {
        if (e == null) {
            return false;
        }
        return add(e.booleanValue());
    }

    @Override
    public boolean add(boolean e) {
        data = grow(data, size + 1, size);
        setBit(e, size++);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Boolean) {
            boolean booleanVal = ((Boolean) o).booleanValue();
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (checkBit(i, booleanVal)) {
                    count++;
                }
            }
            if (count > 0) {
                int newSize = size - count;
                clear();
                for (int i = 0; i < newSize; i++) {
                    add(!booleanVal);
                }
                size = newSize;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Boolean> c) {
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            int i = size;
            for (boolean e : c) {
                setBit(e, i++);
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Boolean> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            size += c.size();
            for (int i = size - 1; i >= index + c.size(); i--) {
                setBit(getBit(i - c.size()), i);
            }
            int i = 0;
            for (boolean e : c) {
                setBit(e, index + i++);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int r = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(getBit(i))) {
                setBit(getBit(i), r++);
            }
        }
        if (size - r > 0) {
            size = r;
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, Boolean element) {
        rangeCheck(index);
        data = grow(data, size + 1, size);
        for (int i = size; i > index; i--) {
            setBit(getBit(i-1), i);
        }
        size++;
        setBit(element.booleanValue(), index);
    }

    @Override
    public Boolean remove(int index) {
        rangeCheck(index);
        boolean tmp = getBit(index);
        for (int i = index; i < size - 1; i++) {
            setBit(getBit(i+1), i);
        }
        size--;
        return Boolean.valueOf(tmp);
    }

    private byte[] grow(byte[] array, int length, int preserve) {
        if (length / 8 + 1 > array.length) {
            byte[] newArray = new byte[Math.min(growSize(array.length), ARRAY_LIST_MAX_SIZE)];
            System.arraycopy(array, 0, newArray, 0, preserve / 8);
            return newArray;
        }
        return array;
    }

    @Override
    public void clear() {
        data = new byte[DEFAULT_LENGTH / 8 + 1];
        size = 0;
    }

    @Override
    public Boolean get(int index) {
        rangeCheck(index);
        return getBit(index);
    }

    @Override
    public Boolean set(int index, Boolean element) {
        rangeCheck(index);
        boolean tmp = getBit(index);
        setBit(element.booleanValue(), index);
        return Boolean.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        boolean tmp;
        if (o instanceof Boolean) {
            tmp = ((Boolean) o).booleanValue();
        } else {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (checkBit(i, tmp)) {
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
        boolean tmp;
        if (o instanceof Boolean) {
            tmp = ((Boolean) o).booleanValue();
        } else {
            return -1;
        }
        for (int i = size - 1; i > -1; i--) {
            if (checkBit(i, tmp)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Boolean> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Boolean next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            if (i >= data.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return getBit(lastRet = i);
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                JArrayListBoolean.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public boolean[] booleans() {
        boolean[] res = new boolean[size];
        for (int i = 0; i < size; i++) {
            res[i] = getBit(i);
        }
        return res;
    }

    @Override
    public boolean getBoolean(int idx) {
        rangeCheck(idx);
        return getBit(idx);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Boolean.valueOf(getBit(i));
        }
        return result;
    }

    @Override
    public List<Boolean> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        List<Boolean> res = new JArrayListBoolean();
        for (int i = fromIndex; i < toIndex; i++) {
            res.add(getBit(i));
        }
        return res;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}