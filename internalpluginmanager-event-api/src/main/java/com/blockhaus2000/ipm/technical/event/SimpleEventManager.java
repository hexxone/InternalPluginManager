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
package com.blockhaus2000.ipm.technical.event;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the {@link EventManager}.
 *
 */
public class SimpleEventManager implements EventManager {
    /**
     * The Logger of this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventManager.class);

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
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public synchronized <T> void register(final Class<T> clazz, final T obj) {
        assert clazz != null : "Clazz cannot be null!";
        assert obj == null || obj.getClass().equals(clazz) : "Obj has to be an instance of clazz!";

        SimpleEventManager.LOGGER.debug("Registering listeners of class " + clazz);

        for (final Method method : clazz.getDeclaredMethods()) {
            this.register(new SimpleEventHandler(obj, method));
        }

        SimpleEventManager.LOGGER.debug("Registration finished");
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class)
     */
    @Override
    public synchronized <T> void register(final Class<T> clazz) {
        this.register(clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public synchronized <T> void register(final T obj) {
        assert obj != null : "Obj cannot be null!";

        this.register((Class<T>) obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#fire(com.blockhaus2000.ipm.technical.event.AbstractEvent)
     */
    @Override
    public void fire(final AbstractEvent event) {
        assert event != null : "Event cannot be null!";

        SimpleEventManager.LOGGER.debug("Firing event " + event);

        if (event instanceof AbstractAsynchEvent) {
            this.internalFire(event);
        } else {
            synchronized (this) {
                this.internalFire(event);
            }
        }

        SimpleEventManager.LOGGER.debug("Firing finished");
    }

    /**
     * Registers the given event handler.
     *
     * @param handler
     *            The {@link EventHandler} to register.
     */
    protected synchronized void register(final EventHandler handler) {
        assert handler != null : "Handler cannot be null!";

        SimpleEventManager.LOGGER.debug("Registering event handler " + handler);

        final Object obj = handler.getListenerObject();
        final Method method = handler.getListenerMethod();
        final Class<?> clazz = method.getDeclaringClass();

        if (!method.isAnnotationPresent(EventListener.class)) {
            SimpleEventManager.LOGGER.debug("Skipping event handler " + handler);

            return;
        }

        SimpleEventManager.LOGGER.debug("Processing event handler " + handler);

        assert obj != null || Modifier.isStatic(method.getModifiers()) : "Method \"" + method.getName() + "\" in class \""
                + clazz.getName() + "\" cannot be non-static if obj is null!";
        assert method.getParameterTypes().length == 1 && method.getParameterTypes()[0].equals(EventContext.class) : "The "
                + "arguments of the method \"" + method.getName() + "\" in class \"" + clazz.getName()
                + "\" are wrong! The first (and only) argument should have the type \"" + EventContext.class.getName() + "\"!";

        final Class<? extends AbstractEvent> eventClass = method.getAnnotation(EventListener.class).value();

        final HandlerList handlers;
        try {
            final Field handlersField = eventClass.getDeclaredField("HANDLERS");

            final boolean handlersFieldAccessible = handlersField.isAccessible();
            handlersField.setAccessible(true);

            handlers = (HandlerList) handlersField.get(obj);

            handlersField.setAccessible(handlersFieldAccessible);
        } catch (final IllegalArgumentException cause) {
            throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName() + "\"!",
                    cause);
        } catch (final SecurityException cause) {
            throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName() + "\"!",
                    cause);
        } catch (final IllegalAccessException cause) {
            throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName() + "\"!",
                    cause);
        } catch (final NoSuchFieldException cause) {
            throw new EventException("An error occurred whilest retriving handler list from class \"" + clazz.getName() + "\"!",
                    cause);
        }

        handlers.add(new SimpleEventHandler(obj, method));

        SimpleEventManager.LOGGER.debug("Registration of event handler finished");
    }

    /**
     * Fires the given event.
     *
     * @param event
     *            The event to fire.
     */
    private void internalFire(final AbstractEvent event) {
        assert event != null : "Event cannot be null!";

        final EventContext<AbstractEvent> context = new EventContext<AbstractEvent>(event);
        for (final EventHandler handler : event.getHandlers()) {
            if (!handler.isEnabled()) {
                // If the event handler is disabled, skip the execution.
                continue;
            }

            try {
                handler.getListenerMethod().invoke(handler.getListenerObject(), context);
            } catch (final IllegalArgumentException ex) {
                SimpleEventManager.LOGGER.error("It seems that the event listener method \""
                        + handler.getListenerMethod().getName() + "\" in class \""
                        + handler.getListenerMethod().getDeclaringClass().getName()
                        + "\" defines wrong arguments. The one and only argument should be a \"" + EventContext.class.getName()
                        + "\"!", ex);
            } catch (final IllegalAccessException ex) {
                SimpleEventManager.LOGGER.error("An error occurred whilst firing event \"" + event + "\" for method \""
                        + handler.getListenerMethod().getName() + "\" in class \""
                        + handler.getListenerMethod().getDeclaringClass().getName() + "\"!", ex);
            } catch (final InvocationTargetException ex) {
                SimpleEventManager.LOGGER.error("An error occurred whilst firing event \"" + event + "\" for method \""
                        + handler.getListenerMethod().getName() + "\" in class \""
                        + handler.getListenerMethod().getDeclaringClass().getName() + "\"!", ex);
            }
        }
    }
}
