package com.andreytim.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListInt extends JAbstractList<Integer> implements RandomAccess, Serializable {

    private static final long serialVersionUID = 7429845362216861622L;

    private int[] data = new int[DEFAULT_LENGTH];

    public JArrayListInt() {}

    public JArrayListInt(int capacity) {
        data = new int[capacity];
    }

    protected JArrayListInt(int[] data) {
        this.data = data;
        this.size = data.length;
    }

    @Override
    public Class<?> getPrimType() {
        return int.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Integer) {
            return contains(((Integer) o).intValue());
        } else if (o instanceof Short) {
            return contains(((Short) o).intValue());
        } else if (o instanceof Byte) {
            return contains(((Byte) o).intValue());
        }
        return false;
    }

    @Override
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(int e) {
        data = grow(data, size + 1, size);
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Integer) {
            int intVal = ((Integer) o).intValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == intVal) {
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
    public boolean addAll(Collection<? extends Integer> c) {
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            int i = size;
            for (int e : c) {
                data[i++] = e;
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (int e : c) {
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
    public void add(int index, Integer element) {
        rangeCheck(index);
        data = grow(data, size + 1, size);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.intValue();
    }

    @Override
    public Integer remove(int index) {
        rangeCheck(index);
        int tmp = data[index];
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return Integer.valueOf(tmp);
    }

    private int[] grow(int[] array, int length, int preserve) {
        if (length > array.length) {
            int[] newArray = new int[Math.min(growSize(array.length), ARRAY_LIST_MAX_SIZE)];
            System.arraycopy(array, 0, newArray, 0, preserve);
            return newArray;
        }
        return array;
    }

    @Override
    public boolean add(Integer e) {
        if (e == null) {
            return false;
        }
        return add(e.intValue());
    }

    @Override
    public boolean add(short e) {
        return add((int)e);
    }

    @Override
    public boolean add(byte e) {
        return add((int)e);
    }

    @Override
    public void clear() {
        data = new int[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Integer get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Integer set(int index, Integer element) {
        rangeCheck(index);
        int tmp = data[index];
        data[index] = element.intValue();
        return Integer.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        int tmp;
        if (o instanceof Integer) {
            tmp = ((Integer) o).intValue();
        } else if (o instanceof Short) {
            tmp = ((Short) o).intValue();
        } else if (o instanceof Byte) {
            tmp = ((Byte) o).intValue();
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
        int tmp;
        if (o instanceof Integer) {
            tmp = ((Integer) o).intValue();
        } else if (o instanceof Short) {
            tmp = ((Short) o).intValue();
        } else if (o instanceof Byte) {
            tmp = ((Byte) o).intValue();
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
    public Iterator<Integer> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Integer> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Integer next() {
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
                JArrayListInt.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public int[] ints() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public int getInt(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Integer.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListInt(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}