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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.blockhaus2000.ipm.technical.event.AbstractEvent;
import com.blockhaus2000.ipm.technical.interception.exception.InterceptionRuntimeException;

public abstract class AbstractInterceptionEvent extends AbstractEvent {
    private final List<Exception> executionStoppingCauses = new LinkedList<Exception>();

    private final String invokedClassName;
    private final Object[] parameters;

    private boolean executionStopped = false;

    protected AbstractInterceptionEvent(final String invokedClassName, final Object[] parameters) {
        assert invokedClassName != null && !invokedClassName.isEmpty() : "InvokedClassName must not be null or empty!";
        assert parameters != null && parameters.length >= 1 : "Parameters must not be null and must contain at leat one element!";

        this.invokedClassName = invokedClassName;
        this.parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
    }

    public synchronized void stopExecution(final Exception cause) {
        this.executionStopped = true;

        this.executionStoppingCauses.add(cause);
    }

    public Class<?> getInvokedClass() {
        try {
            return Class.forName(this.invokedClassName);
        } catch (final ClassNotFoundException cause) {
            throw new InterceptionRuntimeException("An error occurred whilst retrieving the invoked class!", cause);
        }
    }

    public Class<?>[] getParameterTypes() {
        final Class<?>[] result = new Class<?>[this.parameters.length];
        for (int i = 0; i < this.parameters.length; i++) {
            result[i] = this.parameters[i].getClass();
        }
        return result;
    }

    public String getInvokedClassName() {
        return this.invokedClassName;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    public boolean isExecutionStopped() {
        return this.executionStopped;
    }

    public List<Exception> getExecutionStoppingCauses() {
        return this.executionStoppingCauses;
    }
}
