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

import java.lang.reflect.Constructor;

import com.blockhaus2000.ipm.technical.interception.exception.InterceptionRuntimeException;

public abstract class AbstractConstructorEvent extends AbstractInterceptionEvent {
    protected AbstractConstructorEvent(final String invokedClassName, final Object[] parameters) {
        super(invokedClassName, parameters);
    }

    public Constructor<?> getInvokedConstructor() {
        final Class<?>[] invokedParameterTypes = this.getParameterTypes();

        final Constructor<?>[] ctors = this.getInvokedClass().getDeclaredConstructors();
        for (final Constructor<?> ctor : ctors) {
            boolean correct = false;

            final Class<?>[] paramTypes = ctor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (!invokedParameterTypes[i].isAssignableFrom(paramTypes[i])) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                return ctor;
            }
        }

        throw new InterceptionRuntimeException("Could not find any constructor matching the parameters!");
    }
}
