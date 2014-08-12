package org.shpolsky.jafar.core.list.prim;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shpolsky on 15.07.14.
 */
public class JArrayListChar extends JAbstractList<Character> implements RandomAccess, Serializable {

    private static final long serialVersionUID = -5323977774217524858L;

    private char[] data = new char[DEFAULT_LENGTH];

    public JArrayListChar() {}

    protected JArrayListChar(char[] data) {
        this.data = data;
        this.size = data.length;
    }


    @Override
    public Class<?> getPrimType() {
        return char.class;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Character) {
            return contains(((Character) o).charValue());
        }
        return false;
    }

    @Override
    public boolean contains(char e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Character e) {
        if (e == null) {
            return false;
        }
        return add(e.charValue());
    }

    @Override
    public boolean add(char e) {
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
        if (o instanceof Character) {
            char charVal = ((Character) o).charValue();
            for (int i = 0; i < size; i++) {
                if (data[i] == charVal) {
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
            char[] newArray = new char[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        } else if (newSize < data.length >> 2) {
            char[] newArray = new char[data.length >> 1];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Character> c) {
        if (c.size() > 0) {
            ensureSize(size + c.size());
            int i = size;
            for (char e : c) {
                data[i++] = e;
            }
            size += c.size();
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Character> c) {
        rangeCheck(index);
        if (c.size() > 0) {
            ensureSize(size + c.size());
            System.arraycopy(data, index, data, index + c.size(), size - index);
            int i = 0;
            for (char e : c) {
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
        data = new char[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public Character get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public Character set(int index, Character element) {
        rangeCheck(index);
        char tmp = data[index];
        data[index] = element.charValue();
        modCount++;
        return Character.valueOf(tmp);
    }

    @Override
    public void add(int index, Character element) {
        rangeCheck(index);
        ensureSize(size + 1);
        modCount++;
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element.charValue();
    }

    @Override
    public Character remove(int index) {
        rangeCheck(index);
        char tmp = data[index];
        ensureSize(size - 1);
        if (size > index + 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        modCount++;
        size--;
        return Character.valueOf(tmp);
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        char tmp;
        if (o instanceof Character) {
            tmp = ((Character) o).charValue();
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
        char tmp;
        if (o instanceof Character) {
            tmp = ((Character) o).charValue();
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
    public Iterator<Character> iterator() {
        return new Itr();
    }

    // quite resembling the one from ArrayList
    private class Itr implements Iterator<Character> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        public Character next() {
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
                JArrayListChar.this.remove(lastRet);
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
    public char[] chars() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public char getChar(int idx) {
        rangeCheck(idx);
        return data[idx];
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = Character.valueOf(data[i]);
        }
        return result;
    }

    @Override
    public List<Character> subList(int fromIndex, int toIndex) {
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        return new JArrayListChar(Arrays.copyOfRange(data, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}