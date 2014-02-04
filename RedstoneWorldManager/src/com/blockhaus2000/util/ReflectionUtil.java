/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.Validate;

/**
 * 
 * @author Blockhaus2000
 */
public class ReflectionUtil {
    public static Field getField(Class<?> clazz, final String fieldName) {
        Validate.notNull(clazz, "Clazz cannot be null!");
        Validate.notNull(fieldName, "FieldName cannot be null!");
        Validate.notEmpty(fieldName, "FieldName cannot be empty!");

        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);

                clazz = clazz.getSuperclass();

                return field;
            } catch (NoSuchFieldException ex) {
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

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object obj, final String fieldName) {
        Validate.notNull(obj, "Obj cannot be null!");
        Validate.notNull(fieldName, "FieldName cannot be null!");
        Validate.notEmpty(fieldName, "FieldName cannot be empty!");

        Field field = getField(obj.getClass(), fieldName);
        field.setAccessible(true);

        try {
            return (T) field.get(obj);
        } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
            /*
             * This fails silent.
             * 
             * This is because thid method does not throw any exception
             * (excepted IllegalArgumentException), because it returns null if
             * nothing can be found.
             */

            return null;
        }
    }

    public static void setField(final Object obj, final String fieldName, final Object value) throws IllegalArgumentException,
            IllegalAccessException {
        Validate.notNull(obj, "Obj cannot be null!");
        Validate.notNull(fieldName, "FieldName cannot be null!");
        Validate.notEmpty(fieldName, "FieldName cannot be empty!");
        Validate.notNull(value, "Value cannot be null!");

        Field field = getField(obj.getClass(), fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static boolean hasSuperclass(final Class<?> clazz, final Class<?> superClass) {
        Validate.notNull(clazz, "Clazz cannot be null!");
        Validate.notNull(superClass, "SuperClass cannot be null!");

        Class<?> targetClass = clazz;

        while (targetClass != null) {
            if (targetClass == superClass) {
                return true;
            }

            targetClass = targetClass.getSuperclass();
        }

        return false;
    }
}
