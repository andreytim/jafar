package com.andreytim.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListLong extends JAbstractList<Long> implements RandomAccess, Serializable {

    private static final long serialVersionUID = 4362138619824771592L;

    private long[] data = new long[DEFAULT_LENGTH];

    public JArrayListLong() {}

    protected JArrayListLong(long[] data) {
        this.data = data;
        this.size = data.length;
    }


    @Override
    public Class<?> getPrimType() {
        return long.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Long) {
            return contains(((Long) o).longValue());
        } else if (o instanceof Integer) {
            return contains(((Integer) o).longValue());
        } else if (o instanceof Short) {
            return contains(((Short) o).longValue());
        } else if (o instanceof Byte) {
            return contains(((Byte) o).longValue());
        }
        return false;
    }

    @Override
    public boolean contains(long e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Long e) {
        if (e == null) {
            return false;
        }
        return add(e.longValue());
    }

    @Override
    public boolean add(long e) {
        ensureSize(size + 1);
        modCount++;
        data[size++] = e;
        return true;
    }

    @Override
    public boolean add(short e) {
        return add((long)e);
    }

    @Override
    public boolean add(byte e) {
        return add((long)e);
    }

    @Override
    public boolean add(int e) {
        return add((long)e);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Long) {
            long longVal = ((Long) o).longValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == longVal) {
                    if (size > i-1) {
                        System.arraycopy(data, i + 1, data, i, size - i - 1);
                    }
                    ensureSize(size-1);
                    modCount++;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    private void ensureSize(int newSize) {
        if (newSize == data.length) {
            long[] newArray = new long[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        } else if (newSize < data.length >> 2) {
            long[] newArray = new long[data.length >> 1];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Long> c) {
        if (c.size() > 0) {
            ensureSize(size + c.size());
            int i = size;
            for (long e : c) {
                data[i++] = e;
            }
            size += c.size();
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Long> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            ensureSize(size + c.size());
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (long e : c) {
                data[index + i++] = e;
            }
            size += c.size();
            modCount++;
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
            ensureSize(r);
            size = r;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        modCount++;
        data = new long[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Long get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Long set(int index, Long element) {
        rangeCheck(index);
        long tmp = data[index];
        data[index] = element.longValue();
        modCount++;
        return Long.valueOf(tmp);
    }

    @Override
    public void add(int index, Long element) {
        rangeCheck(index);
        ensureSize(size + 1);
        modCount++;
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.longValue();
    }

    @Override
    public Long remove(int index) {
        rangeCheck(index);
        long tmp = data[index];
        ensureSize(size - 1);
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        modCount++;
        size--;
        return Long.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        long tmp;
        if (o instanceof Long) {
            tmp = ((Long) o).longValue();
        } else if (o instanceof Integer) {
            tmp = ((Integer) o).longValue();
        } else if (o instanceof Short) {
            tmp = ((Short) o).longValue();
        } else if (o instanceof Byte) {
            tmp = ((Byte) o).longValue();
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
        long tmp;
        if (o instanceof Long) {
            tmp = ((Long) o).longValue();
        } else if (o instanceof Integer) {
            tmp = ((Integer) o).longValue();
        } else if (o instanceof Short) {
            tmp = ((Short) o).longValue();
        } else if (o instanceof Byte) {
            tmp = ((Byte) o).longValue();
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
    public Iterator<Long> iterator() {
        return new Itr();
    }

    // quite resembling the one from ArrayList
    private class Itr implements Iterator<Long> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        public Long next() {
            checkForComodification();
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
            checkForComodification();
            try {
                JArrayListLong.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public long[] longs() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public long getLong(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Long.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Long> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListLong(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}