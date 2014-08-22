package com.andreytim.jafar.core.prim;

/**
 * Created by shpolsky on 15.07.14.
 */
public class PrimTypeUtils {

    public static <T> T getTypedPrimInstance(Class<?> primClass, Class<T> instanceClass) {
        String primName = PrimType.valueOf(primClass).primClass.getSimpleName();
        StringBuilder instName = new StringBuilder()
                .append(instanceClass.getPackage().getName())
                .append(".prim.")
                .append(instanceClass.getSimpleName())
                .append((char)(primName.charAt(0) - 'a' + 'A'))
                .append(primName.substring(1));
        try {
            Class<?> clazz = Class.forName(instName.toString());
            return (T) clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
