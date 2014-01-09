/*
 * This file is part of RedstoneWorldManager
 * 
 */
/**
 * 
 */
/**
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
    // /**
    // * Initializes the resources from the given {@link Object} that are marked
    // * with a resource-annotation (fro example, {@link MainPluginResource}).
    // * Only call this with an object, that is not <code>null</code>.
    // otherwise,
    // * this will throw an {@link IllegalArgumentException}.
    // *
    // * The <code>@SuppressWarnings("deprecation")</code> is to suppress the
    // * warning from {@link Main#getInstance()}. It is used to get the main
    // * plugin instance.
    // *
    // * @param obj
    // * The {@link Object} that has to be initialized.
    // * @throws IllegalArgumentException
    // * Will be throwed if the given {@link Object} is
    // * <code>null</code> or {@link Field#set(Object, Object)} will
    // * throw it.
    // * @throws IllegalAccessException
    // * Will be throwed if {@link Field#set(Object, Object)} will
    // * throw it.
    // */
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

    public static void initializeResources(final Class<?> clazz) throws IllegalArgumentException, IllegalAccessException {
        initializeResources(clazz, null);
    }

    public static void initializeResources(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        initializeResources(obj.getClass(), obj);
    }

    private static boolean isFieldCallValid(final Field field, final Object obj, final boolean throwException)
            throws IllegalArgumentException {
        final boolean isValid = !Modifier.isStatic(field.getModifiers()) && obj == null;

        if (throwException && !isValid) {
            throw new IllegalArgumentException("The field " + field + " is non-static and the given Object is null!");
        }

        return !Modifier.isStatic(field.getModifiers()) && obj == null;
    }
}
