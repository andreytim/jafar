package org.shpolsky.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListDouble extends JAbstractList<Double> implements RandomAccess, Serializable {

    private static final long serialVersionUID = 4022061856586753208L;

    private double[] data = new double[DEFAULT_LENGTH];

    public JArrayListDouble() {}

    protected JArrayListDouble(double[] data) {
        this.data = data;
        this.size = data.length;
    }


    @Override
    public Class<?> getPrimType() {
        return double.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Double) {
            return contains(((Double) o).doubleValue());
        } else if (o instanceof Float) {
            return contains(((Float) o).doubleValue());
        }
        return false;
    }

    @Override
    public boolean contains(double e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Double e) {
        if (e == null) {
            return false;
        }
        return add(e.doubleValue());
    }

    @Override
    public boolean add(double e) {
        ensureSize(size + 1);
        modCount++;
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Double) {
            double doubleVal = ((Double) o).doubleValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == doubleVal) {
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
            double[] newArray = new double[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        } else if (newSize < data.length >> 2) {
            double[] newArray = new double[data.length >> 1];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Double> c) {
        if (c.size() > 0) {
            ensureSize(size + c.size());
            int i = size;
            for (double e : c) {
                data[i++] = e;
            }
            size += c.size();
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Double> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            ensureSize(size + c.size());
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (double e : c) {
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
        data = new double[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Double get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Double set(int index, Double element) {
        rangeCheck(index);
        double tmp = data[index];
        data[index] = element.doubleValue();
        modCount++;
        return Double.valueOf(tmp);
    }

    @Override
    public void add(int index, Double element) {
        rangeCheck(index);
        ensureSize(size + 1);
        modCount++;
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.doubleValue();
    }

    @Override
    public Double remove(int index) {
        rangeCheck(index);
        double tmp = data[index];
        ensureSize(size - 1);
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        modCount++;
        size--;
        return Double.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        double tmp;
        if (o instanceof Double) {
            tmp = ((Double) o).doubleValue();
        } else if (o instanceof Float) {
            tmp = ((Float) o).doubleValue();
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
        double tmp;
        if (o instanceof Double) {
            tmp = ((Double) o).doubleValue();
        } else if (o instanceof Float) {
            tmp = ((Float) o).doubleValue();
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
    public Iterator<Double> iterator() {
        return new Itr();
    }

    // quite resembling the one from ArrayList
    private class Itr implements Iterator<Double> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        public Double next() {
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
                JArrayListDouble.this.remove(lastRet);
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
    public double[] doubles() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public double getDouble(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Double.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Double> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListDouble(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}