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
package com.blockhaus2000.ipm.technical.interception.event;

import java.lang.reflect.Method;

import com.blockhaus2000.ipm.base.parameterized.ParameterizedFactory;
import com.blockhaus2000.ipm.base.parameterized.ParameterizedMethod;
import com.blockhaus2000.ipm.base.parameterized.ParameterizedUtil;
import com.blockhaus2000.ipm.technical.interception.exception.InterceptionRuntimeException;

/**
 * This is the base class for every event that relates on a method event (like
 * method invocations).
 *
 */
public abstract class AbstractMethodEvent extends AbstractInterceptionEvent {
    /**
     * The name of the method that was invoked.
     *
     */
    private final String invokedMethodName;

    /**
     * Constructor of AbstractMethodEvent.
     *
     * @param invokedClassName
     *            The name of the class the invoked method is contained by.
     * @param invokedMethodName
     *            The name of the method that was invoked.
     * @param parameters
     *            The parameters which where used whilst the method invocation.
     */
    protected AbstractMethodEvent(final String invokedClassName, final String invokedMethodName, final Object[] parameters) {
        super(invokedClassName, parameters);

        this.invokedMethodName = invokedMethodName;
    }

    /**
     * Fetches the invoked method.
     *
     * @return The invoked {@link Method}.
     */
    public Method getInvokedMethod() {
        final Class<?>[] invokedParameterTypes = this.getParameterTypes();

        final ParameterizedMethod[] methods = ParameterizedFactory.create(this.getInvokedClass().getDeclaredMethods());
        final Method method = ParameterizedUtil.calculateMostMatching(methods, invokedParameterTypes).getMethod();
        if (method == null) {
            throw new InterceptionRuntimeException("Could not find any method matching the parameters!");
        }
        return method;
    }

    /**
     *
     * @return {@link AbstractMethodEvent#invokedMethodName}
     */
    public String getInvokedMethodName() {
        return this.invokedMethodName;
    }
}
