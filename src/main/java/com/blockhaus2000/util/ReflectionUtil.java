/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.util;

import java.lang.reflect.Field;

/**
 * This class is to simplify the usage of reflection.
 *
 */
public class ReflectionUtil {
    private ReflectionUtil() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    /**
     * Searchs in the given {@link Class} for the given field name. if it is not
     * found, it will search in the superclass and so on, until the superclass
     * is <code>null</code>.
     *
     * <p>
     * <b> NOTE: No exception will be thrown here, because the only exception
     * {@link NoSuchFieldException} fails silent. This fails silent because this
     * method checks that the field is available. If not, this method gets the
     * superclass and checks again. If no field has been found, this method
     * returns null. </b>
     * </p>
     *
     * @param clazz
     *            The {@link Class} where the method has to search for the given
     *            field name.
     * @param fieldName
     *            The field name to search for.
     * @return The field if foudn, otherwise <code>null</code>.
     */
    public static Field getField(Class<?> clazz, final String fieldName) {
        assert clazz != null : "Clazz cannot be null!";
        assert fieldName != null : "FieldName cannot be null!";
        assert fieldName.length() != 0 : "FieldName cannot be empty!";

        while (clazz != null) {
            try {
                final Field field = clazz.getDeclaredField(fieldName);

                clazz = clazz.getSuperclass();

                return field;
            } catch (NoSuchFieldException ex) {
                clazz = clazz.getSuperclass();

                /*
                 * This fails silent.
                 *
                 * See JavaDoc for explanation.
                 */
            }
        }

        return null;
    }

    /**
     * Gets the value of the given field name in the given {@link Object}. Will
     * call {@link ReflectionUtil#getField(Class, String)} to get the field.
     *
     * <p>
     * <b> NOTE: This method does not throw any exception. If an exception is
     * thrown internal, this will return <code>null</code>.</b>
     * </p>
     *
     * @param obj
     *            The {@link Object} where the field is stored.
     * @param fieldName
     *            The field name to search for.
     * @param <T>
     *            The data type that has to be returned.
     * @return The value of the field if found, otherwise <code>null</code>.
     * @see com.blockhaus2000.util.ReflectionUtil#getField(java.lang.Class,
     *      java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object obj, final String fieldName) {
        assert obj != null : "Obj cannot be null!";
        assert fieldName != null : "FieldName cannot be null!";
        assert fieldName.length() != 0 : "FieldName cannot be empty!";

        final Field field = ReflectionUtil.getField(obj.getClass(), fieldName);
        field.setAccessible(true);

        try {
            return (T) field.get(obj);
        } catch (Exception e) {
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

    /**
     * Sets the value of the given field name in the given {@link Object} to the
     * given value. Will call {@link ReflectionUtil#getField(Class, String)} to
     * get the field.
     *
     * @param obj
     *            The {@link Object} where the field name can be found.
     * @param fieldName
     *            The field name to search for.
     * @param value
     *            The new value for the given field.
     * @throws IllegalAccessException
     *             Will be thrown, if the field access is not allowed.
     * @see com.blockhaus2000.util.ReflectionUtil#getField(java.lang.Class,
     *      java.lang.String)
     */
    public static void setField(final Object obj, final String fieldName, final Object value) throws IllegalAccessException {
        assert obj != null : "Obj cannot be null!";
        assert fieldName != null : "FieldName cannot be null!";
        assert fieldName.length() != 0 : "FieldName cannot be empty!";
        assert value != null : "Value cannot be null!";

        final Field field = ReflectionUtil.getField(obj.getClass(), fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    /**
     * Checks that the given {@link Class} has the given super {@link Class}.
     *
     * @param clazz
     *            The {@link Class} to check for the given super {@link Class}.
     * @param superClass
     *            The super {@link Class} that has to be implemented by the
     *            given {@link Class}.
     * @return A boolean value if the given {@link Class} has the given super
     *         {@link Class}.
     */
    public static boolean hasSuperclass(final Class<?> clazz, final Class<?> superClass) {
        assert clazz != null : "Clazz cannot be null!";
        assert superClass != null : "SuperClass cannot be null!";

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
