package com.andreytim.jafar.bm.list;

import cern.colt.list.AbstractList;
import com.andreytim.jafar.core.list.ExtLists;
import com.andreytim.jafar.core.list.Lists;
import com.andreytim.jafar.core.prim.PrimType;
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
        Lists.addRands(l1, type, size);
        System.out.printf("ArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l1));
        List<?> l2 = Lists.createJArrayList(type);
        Lists.addRands(l2, type,  size);
        System.out.printf("JArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l2));
        List<?> l3 = ExtLists.createFastutilArrayList(type);
        Lists.addRands(l3, type,  size);
        System.out.printf("FastutilArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l3));
        AbstractList l4 = ExtLists.createColtArrayList(type);
        ExtLists.addColtRands(l4, type, size);
        System.out.printf("ColtArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l4));
        Object l5 = ExtLists.createTroveArrayList(type);
        if (l5 != null) {
            ExtLists.addTroveRands(l5, type, size);
            System.out.printf("TroveArrayList<%s>[%d] size: %s\n", type.boxedClass.getSimpleName(), size, size(l5));
        } else {
            System.out.printf("There's no TroveArrayList<%s>\n", type.boxedClass.getSimpleName());
        }
        System.out.println();
    }

    private static String size(Object o) {
        return SizeOf.humanReadable(SizeOf.deepSizeOf(o));
    }
}
