/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.resources;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import me.lobnews.rwm.bukkit.Main;

import org.apache.commons.lang.Validate;

/**
 * This class will initialize the resources from the given {@link Object}s. See
 * JavaDoc below to see the exact implementation.
 * 
 * @author Blockhaus2000
 */
public class ResourceManager {
    /**
     * Initializes the resources from the given {@link Class} for the
     * {@link Object} that are marked with a resource-annotation (fro example,
     * {@link MainPluginResource}). If one of the fields that have to be
     * initialized is non-static, the given {@link Object} cannot be
     * <code>null</code>.
     * 
     * The <code>@SuppressWarnings("deprecation")</code> is to suppress the
     * warning from {@link Main#getInstance()}. It is used to get the main
     * plugin instance.
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
    @SuppressWarnings("deprecation")
    public static void initializeResources(final Class<?> clazz, final Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        Validate.notNull(clazz, "The given class is null!");

        for (Field target : clazz.getFields()) {
            if (target.isAnnotationPresent(MainPluginResource.class)) {
                isFieldCallValid(target, obj, true);

                target.setAccessible(true);
                target.set(obj, Main.getInstance());
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
        initializeResources(clazz, null);
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
    public static void initializeResources(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        initializeResources(obj.getClass(), obj);
    }

    /**
     * Checks that the given {@link Field} is non-static and the given
     * {@link Object} is <code>null</code>. If
     * <code>throwException = true</code>, an {@link IllegalArgumentException}
     * is throwed.
     * 
     * @param field
     *            The {@link Field} that has to be checked
     * @param obj
     *            The {@link Field} that has to be checked.
     * @param throwException
     *            The flag if an {@link IllegalArgumentException} have to be
     *            throwed if this will return <code>false</code>.
     * @return <code>!Modifier.isStatic(field.getModifiers()) && obj == null</code>
     * @throws IllegalArgumentException
     *             Will be throwed if this will return <code>false</code> and
     *             <code>throwException == true</code>.
     */
    private static boolean isFieldCallValid(final Field field, final Object obj, final boolean throwException)
            throws IllegalArgumentException {
        final boolean isValid = !Modifier.isStatic(field.getModifiers()) && obj == null;

        if (throwException && !isValid) {
            throw new IllegalArgumentException("The field " + field + " is non-static and the given Object is null!");
        }

        return !Modifier.isStatic(field.getModifiers()) && obj == null;
    }
}
