package com.andreytim.jafar.bm.list;

import com.andreytim.jafar.core.list.Lists;
import com.andreytim.jafar.core.prim.PrimType;
import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.chars.CharArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import net.sourceforge.sizeof.SizeOf;
import org.junit.Test;

import java.util.List;

/**
 * Created by shpolsky on 19.07.14.
 */
public class JArrayListMemoryFootprintTest {

    private static final int SIZE = 1000000;

    @Test
    public void testMemoryFootprints() {
        SizeOf.setMinSizeToLog(Long.MAX_VALUE);
        checkSizes(PrimType.BYTE, SIZE);
        checkSizes(PrimType.SHORT, SIZE);
        checkSizes(PrimType.INT, SIZE);
        checkSizes(PrimType.LONG, SIZE);
        checkSizes(PrimType.FLOAT, SIZE);
        checkSizes(PrimType.DOUBLE, SIZE);
        checkSizes(PrimType.CHAR, SIZE);
        checkSizes(PrimType.BOOLEAN, SIZE);
    }

    private static void checkSizes(PrimType type, long size) {
        List<?> l1 = Lists.createArrayList(type);
        addRands(l1, type, size);
        System.out.printf("ArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l1));
        List<?> l2 = Lists.createJArrayList(type);
        addRands(l2, type,  size);
        System.out.printf("JArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l2));
        List<?> l3 = createFastutilArrayList(type);
        addRands(l3, type,  size);
        System.out.printf("FastutilArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l3));
        System.out.println();
    }

    private static String size(Object o) {
        return SizeOf.humanReadable(SizeOf.deepSizeOf(o));
    }

    private static <T> void addRands(List<T> list, PrimType type, long amount) {
        for (int i = 0; i < amount; i++) {
            list.add((T) type.nextRand());
        }
    }

    private static List<?> createFastutilArrayList(PrimType primType) {
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
}
