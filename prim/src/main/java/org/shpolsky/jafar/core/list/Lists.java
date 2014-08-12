package org.shpolsky.jafar.core.list;

import org.shpolsky.jafar.core.list.prim.JList;
import org.shpolsky.jafar.core.prim.PrimType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shpolsky on 19.07.14.
 */
public final class Lists {

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

}
