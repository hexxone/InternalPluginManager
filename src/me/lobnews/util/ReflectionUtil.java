/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util;

import java.lang.reflect.Field;

/**
 * 
 * @author Blockhaus2000
 */
public class ReflectionUtil {
    @SuppressWarnings("unchecked")
    public static <T> T getField(final Object obj, final String fieldName) {
        Class<?> clazz = obj.getClass();
        
        do {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return (T) field.get(obj);
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e) {
            }
        } while (clazz.getSuperclass() != Object.class && ((clazz = clazz.getSuperclass()) != null));
        
        return null;
    }
}
