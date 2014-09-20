/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
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
package com.blockhaus2000.ipm.minecraft.event;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * An implementation of the {@link EventManager}.
 *
 */
public class SimpleEventManager implements EventManager {
    /**
     * The logger.
     *
     */
    private static final Logger LOGGER = InternalPluginManager.getServer().getLogger();

    /**
     * Constructor of SimpleEventManager.
     *
     */
    public SimpleEventManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.event.EventManager#register(Plugin,
     *      java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> void register(final Plugin plugin, final Class<T> clazz, final T obj) {
        assert plugin != null : "Plugin cannot be null!";
        assert clazz != null : "Clazz cannot be null!";

        for (final Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventListener.class)) {
                continue;
            }

            assert obj != null || Modifier.isStatic(method.getModifiers()) : "Method \"" + method.getName() + "\" in class \""
                    + clazz.getName() + "\" cannot be non-static if obj is null!";
            assert method.getParameterTypes().length == 1 && method.getParameterTypes()[0].equals(EventContext.class) : "The "
                    + "arguments of the method \"" + method.getName() + "\" in class \"" + clazz.getName()
                    + "\" are wrong! The first (and only) argument should have the type \"" + EventContext.class.getName()
                    + "\"!";

            final Class<? extends AbstractEvent> eventClass = method.getAnnotation(EventListener.class).value();

            final HandlerList handlers;
            try {
                final Field handlersField = eventClass.getDeclaredField("HANDLERS");

                final boolean handlersFieldAccessible = handlersField.isAccessible();
                handlersField.setAccessible(true);

                handlers = (HandlerList) handlersField.get(obj);

                handlersField.setAccessible(handlersFieldAccessible);
            } catch (final IllegalArgumentException cause) {
                throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName()
                        + "\"!", cause);
            } catch (final SecurityException cause) {
                throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName()
                        + "\"!", cause);
            } catch (final IllegalAccessException cause) {
                throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName()
                        + "\"!", cause);
            } catch (final NoSuchFieldException cause) {
                throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName()
                        + "\"!", cause);
            }

            handlers.add(new EventHandler(plugin, obj, method));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.event.EventManager#register(Plugin,
     *      java.lang.Class)
     */
    @Override
    public <T> void register(final Plugin plugin, final Class<T> clazz) {
        this.register(plugin, clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.event.EventManager#register(Plugin,
     *      java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void register(final Plugin plugin, final T obj) {
        assert obj != null : "Obj cannot be null!";

        this.register(plugin, (Class<T>) obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.event.EventManager#fire(com.blockhaus2000.ipm.minecraft.event.AbstractEvent)
     */
    @Override
    public void fire(final AbstractEvent event) {
        assert event != null : "Event cannot be null!";

        if (event instanceof AbstractAsynchEvent) {
            this.internalFire(event);
        } else {
            synchronized (this) {
                this.internalFire(event);
            }
        }
    }

    /**
     * Fires the given event.
     *
     * @param event
     *            The event to fire.
     */
    private void internalFire(final AbstractEvent event) {
        final EventContext<AbstractEvent> context = new EventContext<AbstractEvent>(event);
        for (final EventHandler handler : event.getHandlers()) {
            if (!handler.getPlugin().isEnabled()) {
                // Skip listeners from plugins that are disabled.
                continue;
            }

            try {
                handler.getListenerMethod().invoke(handler.getListenerObject(), context);
            } catch (final IllegalArgumentException ex) {
                SimpleEventManager.LOGGER.log(Level.SEVERE, "It seems that the event listener method \""
                        + handler.getListenerMethod().getName() + "\" in class \""
                        + handler.getListenerMethod().getDeclaringClass().getName()
                        + "\" defines wrogn arguments. The one and only argument should be a \"" + EventContext.class.getName()
                        + "\"!", ex);
            } catch (final IllegalAccessException ex) {
                SimpleEventManager.LOGGER.log(Level.SEVERE, "An error occurred whilest fireing event \"" + event
                        + "\" for method \"" + handler.getListenerMethod().getName() + "\" in clas \""
                        + handler.getListenerMethod().getDeclaringClass().getName() + "\"!", ex);
            } catch (final InvocationTargetException ex) {
                SimpleEventManager.LOGGER.log(Level.SEVERE, "An error occurred whilest fireing event \"" + event
                        + "\" for method \"" + handler.getListenerMethod().getName() + "\" in clas \""
                        + handler.getListenerMethod().getDeclaringClass().getName() + "\"!", ex);
            }
        }
    }
}