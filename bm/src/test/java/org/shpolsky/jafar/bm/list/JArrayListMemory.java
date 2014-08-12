package org.shpolsky.jafar.bm.list;

import net.sourceforge.sizeof.SizeOf;
import org.junit.Test;
import org.shpolsky.jafar.core.list.Lists;
import org.shpolsky.jafar.core.prim.PrimType;

import java.util.List;

/**
 * Created by shpolsky on 19.07.14.
 */
public class JArrayListMemory {

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
}
