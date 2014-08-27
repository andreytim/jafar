package com.andreytim.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListShort extends JAbstractList<Short> implements RandomAccess, Serializable {

    private static final long serialVersionUID = -8939977387420365224L;

    private short[] data = new short[DEFAULT_LENGTH];

    public JArrayListShort() {}

    protected JArrayListShort(short[] data) {
        this.data = data;
        this.size = data.length;
    }


    @Override
    public Class<?> getPrimType() {
        return short.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Short) {
            return contains(((Short) o).shortValue());
        } else if (o instanceof Byte) {
            return contains(((Byte) o).shortValue());
        }
        return false;
    }

    @Override
    public boolean contains(short e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(short e) {
        data = grow(data, size + 1, size);
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Short) {
            short shortVal = ((Short) o).shortValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == shortVal) {
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
    public boolean addAll(Collection<? extends Short> c) {
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            int i = size;
            for (short e : c) {
                data[i++] = e;
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Short> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (short e : c) {
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
    public void add(int index, Short element) {
        rangeCheck(index);
        data = grow(data, size + 1, size);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.shortValue();
    }

    @Override
    public Short remove(int index) {
        rangeCheck(index);
        short tmp = data[index];
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return Short.valueOf(tmp);
    }

    private short[] grow(short[] array, int length, int preserve) {
        if (length > array.length) {
            short[] newArray = new short[Math.min(growSize(array.length), ARRAY_LIST_MAX_SIZE)];
            System.arraycopy(array, 0, newArray, 0, preserve);
            return newArray;
        }
        return array;
    }

    @Override
    public boolean add(Short e) {
        if (e == null) {
            return false;
        }
        return add(e.shortValue());
    }

    @Override
    public boolean add(byte e) {
        return add((int)e);
    }

    @Override
    public void clear() {
        data = new short[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Short get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Short set(int index, Short element) {
        rangeCheck(index);
        short tmp = data[index];
        data[index] = element.shortValue();
        return Short.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        short tmp;
        if (o instanceof Short) {
            tmp = ((Short) o).shortValue();
        } else if (o instanceof Byte) {
            tmp = ((Byte) o).shortValue();
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
        short tmp;
        if (o instanceof Short) {
            tmp = ((Short) o).shortValue();
        } else if (o instanceof Byte) {
            tmp = ((Byte) o).shortValue();
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
    public Iterator<Short> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Short> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Short next() {
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
                JArrayListShort.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public short[] shorts() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public short getShort(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Short.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Short> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListShort(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}