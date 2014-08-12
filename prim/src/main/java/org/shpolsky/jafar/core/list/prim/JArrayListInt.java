package org.shpolsky.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListInt extends JAbstractList<Integer> implements RandomAccess, Serializable {

    private static final long serialVersionUID = 7429845362216861622L;

    private int[] data = new int[DEFAULT_LENGTH];

    public JArrayListInt() {}

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
    public boolean add(Integer e) {
        if (e == null) {
            return false;
        }
        return add(e.intValue());
    }

    @Override
    public boolean add(int e) {
        ensureSize(size + 1);
        modCount++;
        data[size++] = e;
        return true;
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
            int[] newArray = new int[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        } else if (newSize < data.length >> 2) {
            int[] newArray = new int[data.length >> 1];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        if (c.size() > 0) {
            ensureSize(size + c.size());
            int i = size;
            for (int e : c) {
                data[i++] = e;
            }
            size += c.size();
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            ensureSize(size + c.size());
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (int e : c) {
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
        modCount++;
        return Integer.valueOf(tmp);
    }

    @Override
    public void add(int index, Integer element) {
        rangeCheck(index);
        ensureSize(size + 1);
        modCount++;
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.intValue();
    }

    @Override
    public Integer remove(int index) {
        rangeCheck(index);
        int tmp = data[index];
        ensureSize(size - 1);
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        modCount++;
        size--;
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

    // quite resembling the one from ArrayList
    private class Itr implements Iterator<Integer> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        public Integer next() {
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
                JArrayListInt.this.remove(lastRet);
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

    private int lastModCount;
    private int[] lastCopy;

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