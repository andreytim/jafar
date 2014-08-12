package org.shpolsky.jafar.core.prim;

import java.util.Random;

/**
 * Created by shpolsky on 19.07.14.
 */
public enum PrimType {

    BYTE(byte.class, Byte.class) {
        @Override
        public Object nextRand() {
            return Byte.valueOf(Integer.valueOf(RAND.nextInt()).byteValue());
        }
    },
    CHAR(char.class, Character.class) {
        @Override
        public Object nextRand() {
            return Character.valueOf((char) Integer.valueOf(RAND.nextInt()).shortValue());
        }
    },
    SHORT(short.class, Short.class) {
        @Override
        public Object nextRand() {
            return Short.valueOf(Integer.valueOf(RAND.nextInt()).byteValue());
        }
    },
    INT(int.class, Integer.class) {
        @Override
        public Object nextRand() {
            return Integer.valueOf(RAND.nextInt());
        }
    },
    LONG(long.class, Long.class) {
        @Override
        public Object nextRand() {
            return Long.valueOf(RAND.nextLong());
        }
    },
    FLOAT(float.class, Float.class) {
        @Override
        public Object nextRand() {
            return Float.valueOf(RAND.nextFloat());
        }
    },
    DOUBLE(double.class, Double.class) {
        @Override
        public Object nextRand() {
            return Double.valueOf(RAND.nextDouble());
        }
    },
    BOOLEAN(boolean.class, Boolean.class) {
        @Override
        public Object nextRand() {
            return RAND.nextInt(2) == 1;
        }
    };

    public final Class<?> primClass;
    public final Class<?> boxedClass;
    private static final Random RAND = new Random();

    private PrimType(Class<?> primClass, Class<?> boxedClass) {
        this.primClass = primClass;
        this.boxedClass = boxedClass;
    }

    public abstract Object nextRand();

    public static PrimType valueOf(Class<?> clazz) {
        if (isByte(clazz)) {
            return BYTE;
        } else if (isChar(clazz)) {
            return CHAR;
        } else if (isShort(clazz)) {
            return SHORT;
        } else if (isInt(clazz)) {
            return INT;
        } else if (isLong(clazz)) {
            return LONG;
        } else if (isFloat(clazz)) {
            return FLOAT;
        } else if (isDouble(clazz)) {
            return DOUBLE;
        } else if (isBoolean(clazz)) {
            return BOOLEAN;
        }
        throw new IllegalArgumentException("Unsupported primitive or boxed type! Type: " + clazz);
    }

    public static boolean isByte(Class<?> clazz) {
        return clazz.equals(BYTE.primClass) || clazz.equals(BYTE.boxedClass);
    }

    public static boolean isChar(Class<?> clazz) {
        return clazz.equals(CHAR.primClass) || clazz.equals(CHAR.boxedClass);
    }

    public static boolean isShort(Class<?> clazz) {
        return clazz.equals(SHORT.primClass) || clazz.equals(SHORT.boxedClass);
    }

    public static boolean isInt(Class<?> clazz) {
        return clazz.equals(INT.primClass) || clazz.equals(INT.boxedClass);
    }

    public static boolean isLong(Class<?> clazz) {
        return clazz.equals(LONG.primClass) || clazz.equals(LONG.boxedClass);
    }

    public static boolean isFloat(Class<?> clazz) {
        return clazz.equals(FLOAT.primClass) || clazz.equals(FLOAT.boxedClass);
    }

    public static boolean isDouble(Class<?> clazz) {
        return clazz.equals(DOUBLE.primClass) || clazz.equals(DOUBLE.boxedClass);
    }

    public static boolean isBoolean(Class<?> clazz) {
        return clazz.equals(BOOLEAN.primClass) || clazz.equals(BOOLEAN.boxedClass);
    }

}
