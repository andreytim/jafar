package com.andreytim.jafar.core.list;

import cern.colt.list.*;
import com.andreytim.jafar.core.prim.PrimType;
import gnu.trove.list.array.*;

import java.util.List;
import java.util.Random;

/**
 * Created by shpolsky on 27.08.14.
 */
public class ExtLists {

    private static final Random RAND = new Random();

    public static AbstractList createColtArrayList(PrimType primType) {
        switch (primType) {
            case BYTE: return new ByteArrayList();
            case CHAR: return new CharArrayList();
            case SHORT: return new ShortArrayList();
            case INT: return new IntArrayList();
            case LONG: return new LongArrayList();
            case FLOAT: return new FloatArrayList();
            case DOUBLE: return new DoubleArrayList();
            case BOOLEAN: return new BooleanArrayList();
        }
        return null;
    }

    public static List<?> createFastutilArrayList(PrimType primType) {
        switch (primType) {
            case BYTE: return new it.unimi.dsi.fastutil.bytes.ByteArrayList();
            case CHAR: return new it.unimi.dsi.fastutil.chars.CharArrayList();
            case SHORT: return new it.unimi.dsi.fastutil.shorts.ShortArrayList();
            case INT: return new it.unimi.dsi.fastutil.ints.IntArrayList();
            case LONG: return new it.unimi.dsi.fastutil.longs.LongArrayList();
            case FLOAT: return new it.unimi.dsi.fastutil.floats.FloatArrayList();
            case DOUBLE: return new it.unimi.dsi.fastutil.doubles.DoubleArrayList();
            case BOOLEAN: return new it.unimi.dsi.fastutil.booleans.BooleanArrayList();
        }
        return null;
    }

    public static Object createTroveArrayList(PrimType primType) {
        switch (primType) {
            case BYTE: return new TByteArrayList();
            case CHAR: return new TCharArrayList();
            case SHORT: return new TShortArrayList();
            case INT: return new TIntArrayList();
            case LONG: return new TLongArrayList();
            case FLOAT: return new TFloatArrayList();
            case DOUBLE: return new TDoubleArrayList();
            case BOOLEAN: return null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void addColtRands(AbstractList list, PrimType type, long amount) {
        if (list != null) {
            for (int i = 0; i < amount; i++) {
                switch (type) {
                    case BYTE: ((ByteArrayList) list).add((byte) type.nextRand()); break;
                    case CHAR: ((CharArrayList) list).add((char) type.nextRand()); break;
                    case SHORT: ((ShortArrayList) list).add((short) type.nextRand()); break;
                    case INT: ((IntArrayList) list).add((int) type.nextRand()); break;
                    case LONG: ((LongArrayList) list).add((long) type.nextRand()); break;
                    case FLOAT: ((FloatArrayList) list).add((float) type.nextRand()); break;
                    case DOUBLE: ((DoubleArrayList) list).add((double) type.nextRand()); break;
                    case BOOLEAN: ((BooleanArrayList) list).add((boolean) type.nextRand()); break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void addTroveRands(Object list, PrimType type, long amount) {
        if (list != null && !type.equals(type.BOOLEAN)) {
            for (int i = 0; i < amount; i++) {
                switch (type) {
                    case BYTE: ((TByteArrayList) list).add((byte) type.nextRand()); break;
                    case CHAR: ((TCharArrayList) list).add((char) type.nextRand()); break;
                    case SHORT: ((TShortArrayList) list).add((short) type.nextRand()); break;
                    case INT: ((TIntArrayList) list).add((int) type.nextRand()); break;
                    case LONG: ((TLongArrayList) list).add((long) type.nextRand()); break;
                    case FLOAT: ((TFloatArrayList) list).add((float) type.nextRand()); break;
                    case DOUBLE: ((TDoubleArrayList) list).add((double) type.nextRand()); break;
                }
            }
        }
    }

    public static void refill(IntArrayList list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refill(TIntArrayList list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(TIntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refill(it.unimi.dsi.fastutil.ints.IntArrayList list, int size) {
        if (list.size() > 0) list.clear();
        fill(list, size);
    }

    public static void fill(it.unimi.dsi.fastutil.ints.IntArrayList list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    public static void refillArithmeticProgression(IntArrayList list, int size, int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(IntArrayList list, int size, int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

    public static void refillArithmeticProgression(TIntArrayList list, int size, int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(TIntArrayList list, int size, int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

    public static void refillArithmeticProgression(it.unimi.dsi.fastutil.ints.IntArrayList list, int size,
                                                   int first, int step) {
        if (list.size() > 0) list.clear();
        fillArithmeticProgression(list, size, first, step);
    }

    public static void fillArithmeticProgression(it.unimi.dsi.fastutil.ints.IntArrayList list, int size,
                                                 int first, int step) {
        int tmp = first;
        for (int i = 0; i < size; i++) {
            list.add(tmp);
            tmp += step;
        }
    }

}
