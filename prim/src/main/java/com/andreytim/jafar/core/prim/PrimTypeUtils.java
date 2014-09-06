package com.andreytim.jafar.core.prim;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

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

    public static <T> T getTypedPrimInstance(Class<?> primClass, Class<T> instanceClass, Object ... primArgs) {
        String primName = PrimType.valueOf(primClass).primClass.getSimpleName();
        StringBuilder instName = new StringBuilder()
                .append(instanceClass.getPackage().getName())
                .append(".prim.")
                .append(instanceClass.getSimpleName())
                .append((char)(primName.charAt(0) - 'a' + 'A'))
                .append(primName.substring(1));
        try {
            Class<?> clazz = Class.forName(instName.toString());
            List<Class<?>> argTypes = new ArrayList<>();
            for (Object arg : primArgs) {
                argTypes.add(PrimType.valueOf(arg.getClass()).primClass);
            }
            Constructor<?> constr = clazz.getConstructor(argTypes.toArray(new Class<?>[]{}));
            return (T) constr.newInstance(primArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
