package com.andreytim.jafar.core.list.prim;

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
        data = grow(data, size + 1, size);
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
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Double> c) {
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            int i = size;
            for (double e : c) {
                data[i++] = e;
            }
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Double> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            data = grow(data, size + c.size(), size);
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (double e : c) {
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
    public void add(int index, Double element) {
        rangeCheck(index);
        data = grow(data, size + 1, size);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.doubleValue();
    }

    @Override
    public Double remove(int index) {
        rangeCheck(index);
        double tmp = data[index];
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return Double.valueOf(tmp);
    }

    private double[] grow(double[] array, int length, int preserve) {
        if (length > array.length) {
            double[] newArray = new double[Math.min(growSize(array.length), ARRAY_LIST_MAX_SIZE)];
            System.arraycopy(array, 0, newArray, 0, preserve);
            return newArray;
        }
        return array;
    }

    @Override
    public void clear() {
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

    private class Itr implements Iterator<Double> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public Double next() {
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
                JArrayListDouble.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
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