package com.andreytim.jafar.core.list;

import com.andreytim.jafar.core.list.prim.JList;
import com.andreytim.jafar.core.prim.PrimType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shpolsky on 19.07.14.
 */
public final class Lists {

    // Creation methods.

    public static JList<?> createJJArrayList(PrimType primType) {
        switch (primType) {
            case BYTE: return new JArrayList<Byte>();
            case CHAR: return new JArrayList<Character>();
            case SHORT: return new JArrayList<Short>();
            case INT: return new JArrayList<Integer>();
            case LONG: return new JArrayList<Long>();
            case FLOAT: return new JArrayList<Float>();
            case DOUBLE: return new JArrayList<Double>();
            case BOOLEAN: return new JArrayList<Boolean>();
        }
        return null;
    }

    public static List<?> createJArrayList(PrimType primType) {
        switch (primType) {
            case BYTE: return new JArrayList<Byte>();
            case CHAR: return new JArrayList<Character>();
            case SHORT: return new JArrayList<Short>();
            case INT: return new JArrayList<Integer>();
            case LONG: return new JArrayList<Long>();
            case FLOAT: return new JArrayList<Float>();
            case DOUBLE: return new JArrayList<Double>();
            case BOOLEAN: return new JArrayList<Boolean>();
        }
        return null;
    }

    public static List<?> createArrayList(PrimType primType) {
        switch (primType) {
            case BYTE: return new ArrayList<Byte>();
            case CHAR: return new ArrayList<Character>();
            case SHORT: return new ArrayList<Short>();
            case INT: return new ArrayList<Integer>();
            case LONG: return new ArrayList<Long>();
            case FLOAT: return new ArrayList<Float>();
            case DOUBLE: return new ArrayList<Double>();
            case BOOLEAN: return new ArrayList<Boolean>();
        }
        return null;
    }

    // Filling methods.

    private static final Random RAND = new Random();

    /**
     * This method must be used cautiously, because it's really unsafe
     * and it's assumed that the input list is of corresponding boxed primitive type.
     *
     * @param list
     * @param type
     * @param amount
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> void addRands(List<T> list, PrimType type, long amount) {
        for (int i = 0; i < amount; i++) {
            list.add((T) type.nextRand());
        }
    }

    public static void refill(List<Integer> list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refillArithmeticProgression(List<Integer> list, int size, int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(List<Integer> list, int size, int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

}
