/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import java.lang.reflect.Field;

/**
 * 
 * @author Blockhaus2000
 */
public class ReflectionUtil {
    @SuppressWarnings("unchecked")
    public static <T> T getField(final Object obj, final String fieldName) {
        Class<?> clazz = obj.getClass();

        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);

                clazz = clazz.getSuperclass();

                field.setAccessible(true);
                return (T) field.get(obj);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                /*
                 * This fails silent.
                 * 
                 * This is because this method checks that the field is
                 * available. If not, this method gets the superclass and checks
                 * again. If no field has been found, this method returns null.
                 */
            }
        }

        return null;
    }
}
