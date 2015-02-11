/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.base.injection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockhaus2000.ipm.base.exception.IllegalStaticAccessException;
import com.blockhaus2000.ipm.base.injection.exception.InjectionException;
import com.blockhaus2000.ipm.base.injection.exception.NotAddedInjectionException;

/**
 * The {@link InjectionManager} manages injection of field that are specified
 * with an interface and has implementations that are unknown whilest compile
 * time. The {@link InjectionManager} provides methods to add implementations
 * whilest runtime, and injects them into the marked field (with the annotation
 * {@link Inject}) that has the type that is associated with an object of an
 * implementation.
 *
 */
public final class InjectionManager {
    /**
     * The Logger for this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InjectionManager.class);

    /**
     * This {@link Map} stores the instances of all registered resources that
     * can be injected.
     *
     * <ul>
     * <li>Key (Class<?>): The type of the field that can be injected.</li>
     * <li>Value (Object): The object that will be injected.</li>
     * </ul>
     *
     */
    private static final Map<String, Object> INSTANCES = new HashMap<String, Object>();

    /**
     * Constructor of InjectionManager.
     *
     */
    private InjectionManager() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Injects all fields in the given {@link Class} that are marked with the
     * annotation {@link Inject} with saved objects.
     *
     * <p>
     * The following (Runtime) Exceptions can be thrown:
     * <ul>
     * <li>{@link IllegalStaticAccessException}: Will be thrown if a static call
     * tries to inject a non-static field.</li>
     * <li>{@link NotAddedInjectionException}: Will be thrown if a call tries to
     * inject a field that has no associated object.</li>
     * <li>
     * {@link InjectionException}: Will be thrown if:
     * <ol>
     * <li>the type of a field is inconsistent with the type of the associated
     * object.</li>
     * <li>you do not have enough permissions to access a field.</li>
     * </ol>
     * </ul>
     * </p>
     *
     * @param clazz
     *            The {@link Class} that contains the fields to inject.
     * @param obj
     *            An {@link Object} of the given {@link Class}. Can be
     *            <code>null</code> if any field can be accessed static.
     */
    public static synchronized void init(final Class<?> clazz, final Object obj) {
        assert clazz != null : "Clazz cannot be null!";
        assert obj == null || clazz.equals(obj.getClass()) : "Obj has to be null or an object of clazz!";

        InjectionManager.LOGGER.info("Initilizing class " + clazz);

        for (final Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Inject.class)) {
                InjectionManager.LOGGER.debug("Skipping field " + field);

                continue;
            }

            InjectionManager.LOGGER.debug("Processing field " + field);

            if (!Modifier.isStatic(field.getModifiers()) && obj == null) {
                throw new IllegalStaticAccessException("The field <" + field + "> is non-static and the given object (<" + obj
                        + ">) is null!");
            }

            final Class<?> type = field.getType();
            if (!InjectionManager.INSTANCES.containsKey(type.getName())) {
                throw new NotAddedInjectionException("No object found that is associated with the  type <" + type + ">!");
            }

            final boolean oldAccessible = field.isAccessible();

            field.setAccessible(true);
            try {
                final Object object = InjectionManager.INSTANCES.get(type.getName());

                InjectionManager.LOGGER.debug("Injecting field " + field + "with value value " + object);

                field.set(obj, InjectionManager.INSTANCES.get(type.getName()));
            } catch (final IllegalArgumentException cause) {
                throw new InjectionException("The type of the saved object is inconsistent with the type of the field!", cause);
            } catch (final IllegalAccessException cause) {
                throw new InjectionException("You do not have enough rights to set the field value!", cause);
            }

            field.setAccessible(oldAccessible);
        }

        InjectionManager.LOGGER.info("Finished initilization");
    }

    /**
     * Delegates to {@link InjectionManager#init(Class, Object)} with
     * <code>clazz = obj.getClass()</code> and <code>obj = obj</code>.
     *
     * @param obj
     *            The {@link Object} that contains the fields to inject. Cannot
     *            be <code>null</code>.
     * @see com.blockhaus2000.ipm.base.injection.InjectionManager#init(java.lang.Class,
     *      java.lang.Object)
     */
    public static synchronized void init(final Object obj) {
        assert obj != null : "Object cannot be null!";

        InjectionManager.init(obj.getClass(), obj);
    }

    /**
     * Delegates to {@link InjectionManager#init(Class, Object)} with
     * <code>clazz = clazz</code> and <code>obj = null</code>.
     *
     * @param clazz
     *            The {@link Class} that contains the fields to inject.
     * @see com.blockhaus2000.ipm.base.injection.InjectionManager#init(java.lang.Class,
     *      java.lang.Object)
     */
    public static synchronized void init(final Class<?> clazz) {
        InjectionManager.init(clazz, null);
    }

    /**
     * Associates the given {@link Class} with the given object to be used while
     * injections.
     *
     * @param <T>
     *            The class type to associated the given {@link Object} with.
     * @param <C>
     *            The type of the {@link Object} to associate with the given
     *            class.
     * @param obj
     *            The object that has to be associated with the given
     *            {@link Class}. This will be injected at
     *            {@link InjectionManager#init(Class, Object)}.
     * @param clazz
     *            The {@link Class} that has to be key of the association.
     * @return <code>true</code> if association was added, <code>false</code> if
     *         not.
     */
    public static synchronized <T, C extends T> boolean addResource(final C obj, final Class<T> clazz) {
        assert obj != null : "Obj cannot be null!";
        assert clazz != null : "Clazz cannot be null!";

        InjectionManager.LOGGER.info("Adding resource " + obj + " to type " + clazz);

        if (InjectionManager.INSTANCES.containsKey(clazz.getName())) {
            InjectionManager.LOGGER.warn("Skipping resource" + obj + " because a value for type " + clazz + " is already added");
            return false;
        }
        InjectionManager.INSTANCES.put(clazz.getName(), obj);

        return true;
    }
}
