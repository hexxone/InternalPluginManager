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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.util;

import java.lang.reflect.Field;

/**
 * 
 * @author Blockhaus2000
 */
public class ReflectionUtil {
    @SuppressWarnings("null")
    public static Field getField(Class<?> clazz, final String fieldName) {
        assert clazz != null : "Clazz cannot be null!";
        assert fieldName != null : "FieldName cannot be null!";
        assert fieldName.length() != 0 : "FieldName cannot be empty!";

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
        assert obj != null : "Obj cannot be null!";
        assert fieldName != null : "FieldName cannot be null!";
        assert fieldName.length() != 0 : "FieldName cannot be empty!";

        Field field = ReflectionUtil.getField(obj.getClass(), fieldName);
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

    public static void setField(final Object obj, final String fieldName, final Object value) throws IllegalArgumentException,
            IllegalAccessException {
        assert obj != null : "Obj cannot be null!";
        assert fieldName != null : "FieldName cannot be null!";
        assert fieldName.length() != 0 : "FieldName cannot be empty!";
        assert value != null : "Value cannot be null!";

        Field field = ReflectionUtil.getField(obj.getClass(), fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

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
