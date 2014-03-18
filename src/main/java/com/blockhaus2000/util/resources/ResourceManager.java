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
package com.blockhaus2000.util.resources;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will initialize the resources from the given {@link Object}s. See
 * JavaDoc below to see the exact implementation.
 * 
 * @author Blockhaus2000
 */
public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();

    private final Map<Class<? extends Annotation>, Object> anots = new HashMap<Class<? extends Annotation>, Object>();

    /**
     * Initializes the resources from the given {@link Class} for the
     * {@link Object} that are marked with a resource-annotation (fro example,
     * {@link MainPluginResource}). If one of the fields that have to be
     * initialized is non-static, the given {@link Object} cannot be
     * <code>null</code>.
     * 
     * @param clazz
     *            The {@link Class} where the fields with the annotations are.
     * @param obj
     *            The {@link Object} that has to be initialized.
     * @throws IllegalArgumentException
     *             Will be throwed if one of the {@link Field}s is non-static
     *             and the given {@link Object} is <code>null</code> or
     *             {@link Field#set(Object, Object)} will throw it.
     * @throws IllegalAccessException
     *             Will be throwed if {@link Field#set(Object, Object)} will
     *             throw it.
     */
    public static void initializeResources(final Class<?> clazz, final Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        assert clazz != null : "The given class is null!";

        for (Field target : clazz.getDeclaredFields()) {
            for (Class<? extends Annotation> targetAnot : ResourceManager.getInstance().anots.keySet()) {
                if (target.isAnnotationPresent(targetAnot)) {
                    target.setAccessible(true);
                    target.set(obj, ResourceManager.getInstance().anots.get(targetAnot));
                    break;
                }
            }
        }
    }

    /**
     * Initializes the {@link Field}s in the given {@link Class} that are marked
     * with a resource annotation (for example, {@link MainPluginResource}).
     * This method will call
     * {@link ResourceManager#initializeResources(Class, Object)}. See there for
     * the full JavaDoc.
     * 
     * @param clazz
     *            The {@link Class} with the fields that has to be initialized.
     * @throws IllegalArgumentException
     *             Will be throwed if
     *             {@link ResourceManager#initializeResources(Class, Object)}
     *             will throw it.
     * @throws IllegalAccessException
     *             Will be throwed if
     *             {@link ResourceManager#initializeResources(Class, Object)}
     *             will throw it.
     * @see ResourceManager#initializeResources(Class, Object)
     */
    public static void initializeResources(final Class<?> clazz) throws IllegalArgumentException, IllegalAccessException {
        ResourceManager.getInstance();
        ResourceManager.initializeResources(clazz, null);
    }

    /**
     * Initializes the {@link Field}s in the given {@link Object} that are
     * marked with a resource annotation (for example,
     * {@link MainPluginResource}). This method will call
     * {@link ResourceManager#initializeResources(Class, Object)}. See there for
     * the full JavaDoc.
     * 
     * @param obj
     *            The {@link Object} with the field that has to be initialized.
     * @throws IllegalArgumentException
     *             Will be throwed if
     *             {@link ResourceManager#initializeResources(Class, Object)}
     *             will throw it.
     * @throws IllegalAccessException
     *             Will be throwed if
     *             {@link ResourceManager#initializeResources(Class, Object)}
     *             will throw it.
     * @see ResourceManager#initializeResources(Class, Object)
     */
    public static void initializeResources(final Object obj) throws IllegalAccessException {
        ResourceManager.getInstance();
        ResourceManager.initializeResources(obj.getClass(), obj);
    }

    /**
     * Adds a resource annotation and an object to use for this
     * {@link ResourceManager} (the usage if like the usage of the
     * {@link MainPluginResource} annotation).
     * 
     * @param anot
     *            The resource-annotation.
     * @param obj
     *            The object that has to be associated with the given
     *            annotation.
     */
    public void registerResource(final Class<? extends Annotation> anot, final Object obj) {
        anots.put(anot, obj);
    }

    /**
     * 
     * @return An instance of the {@link ResourceManager}.
     */
    public static ResourceManager getInstance() {
        return ResourceManager.instance;
    }

    // /**
    // * Checks that the given {@link Field} is non-static and the given
    // * {@link Object} is <code>null</code>. If
    // * <code>throwException = true</code>, an {@link IllegalArgumentException}
    // * is throwed.
    // *
    // * @param field
    // * The {@link Field} that has to be checked
    // * @param obj
    // * The {@link Field} that has to be checked.
    // * @param throwException
    // * The flag if an {@link IllegalArgumentException} have to be
    // * throwed if this will return <code>false</code>.
    // * @return <code>!Modifier.isStatic(field.getModifiers()) && obj ==
    // null</code>
    // * @throws IllegalArgumentException
    // * Will be throwed if this will return <code>false</code> and
    // * <code>throwException == true</code>.
    // */
    // private static boolean isFieldCallValid(final Field field, final Object
    // obj, final boolean throwException)
    // throws IllegalArgumentException {
    // final boolean isValid = !Modifier.isStatic(field.getModifiers()) && obj
    // == null;
    //
    // if (throwException && !isValid) {
    // throw new IllegalArgumentException("The field " + field +
    // " is non-static and the given Object is null!");
    // }
    //
    // return !Modifier.isStatic(field.getModifiers()) && obj == null;
    // }
}
