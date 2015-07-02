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
import java.util.SortedMap;
import java.util.TreeMap;

import com.blockhaus2000.ipm.technical.interception.exception.InterceptionRuntimeException;

public abstract class AbstractMethodEvent extends AbstractInterceptionEvent {
    private final String invokedMethodName;

    protected AbstractMethodEvent(final String invokedClassName, final String invokedMethodName, final Object[] parameters) {
        super(invokedClassName, parameters);

        this.invokedMethodName = invokedMethodName;
    }

    public Method getInvokedMethod() {
        final Class<?>[] invokedParameterTypes = this.getParameterTypes();

        final SortedMap<Integer, Method> matchedMethods = new TreeMap<Integer, Method>();

        for (final Method method : this.getInvokedClass().getDeclaredMethods()) {
            if (!method.getName().equals(this.getInvokedMethodName())) {
                continue;
            }

            final Class<?>[] paramTypes = method.getParameterTypes();
            if (invokedParameterTypes.length <= paramTypes.length) {
                int correctParamCount = 0;
                for (int i = 0; i < invokedParameterTypes.length; i++) {
                    if (invokedParameterTypes[i].isAssignableFrom(paramTypes[i])) {
                        correctParamCount++;
                    } else {
                        break;
                    }
                }
                if (paramTypes.length > 0) {
                    matchedMethods.put(100 / paramTypes.length * correctParamCount, method);
                } else {
                    matchedMethods.put(100, method);
                }
            }
        }

        final Method method = matchedMethods.get(matchedMethods.lastKey());
        if (method == null) {
            throw new InterceptionRuntimeException("Could not find any method matching the parameters!");
        }
        return method;
    }

    public String getInvokedMethodName() {
        return this.invokedMethodName;
    }
}
