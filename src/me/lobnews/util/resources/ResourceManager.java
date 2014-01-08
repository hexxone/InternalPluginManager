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
package me.lobnews.util.resources;

import java.lang.reflect.Field;

import org.apache.commons.lang.Validate;

import me.lobnews.rwm.bukkit.Main;

/**
 * This class will initialize the resources from the given {@link Object}s. See
 * JavaDoc below to see the exact implementation.
 * 
 * @author Blockhaus2000
 */
public class ResourceManager {
    /**
     * Initializes the resources from the given {@link Object} that are marked
     * with a resource-annotation (fro example, {@link MainPluginResource}).
     * Only call this with an object, that is not <code>null</code>. otherwise,
     * this will throw an {@link IllegalArgumentException}.
     * 
     * The <code>@SuppressWarnings("deprecation")</code> is to suppress the
     * warning from {@link Main#getInstance()}. It is used to get the main
     * plugin instance.
     * 
     * @param obj
     *            The {@link Object} that has to be initialized.
     * @throws IllegalArgumentException
     *             Will be throwed if the given {@link Object} is
     *             <code>null</code> or {@link Field#set(Object, Object)} will
     *             throw it.
     * @throws IllegalAccessException
     *             Will be throwed if {@link Field#set(Object, Object)} will
     *             throw it.
     */
    @SuppressWarnings("deprecation")
    public static void initializeResources(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        Validate.notNull(obj, "Obj cannot be null.");

        Class<?> clazz = obj.getClass();

        for (Field target : clazz.getFields()) {
            if (target.isAnnotationPresent(MainPluginResource.class)) {
                target.setAccessible(true);
                target.set(obj, Main.getInstance());
            }
        }
    }
}
