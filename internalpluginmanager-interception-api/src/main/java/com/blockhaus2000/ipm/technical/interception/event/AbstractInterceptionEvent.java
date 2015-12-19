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

/**
 * The base class of every event relating to the interception API.
 *
 * <p>
 * Any event if triggered because some actions happened relating to method
 * Invocations, constructor invocations and so on. With these events, parameters
 * can be checked, logging can be done and the execution can be stopped also.
 * </p>
 *
 */
public abstract class AbstractInterceptionEvent extends AbstractEvent {
    /**
     * A list of causes why the execution has stopped.
     *
     */
    private final List<Exception> executionStoppingCauses = new LinkedList<Exception>();

    /**
     * The name of the class containing the method which was invoked and
     * triggered this event.
     *
     */
    private final String invokedClassName;
    /**
     * The parameters which were present on the method invocation.
     *
     */
    private final Object[] parameters;

    /**
     * Whether the execution is stopped.
     *
     */
    private boolean executionStopped;

    /**
     * Constructor of AbstractInterceptionEvent.
     *
     * @param invokedClassName
     *            The name of the class containing the method which was invoked
     *            and triggered this event.
     * @param parameters
     *            The parameters which were present on the method invocation.
     */
    protected AbstractInterceptionEvent(final String invokedClassName, final Object[] parameters) {
        assert invokedClassName != null && !invokedClassName.isEmpty() : "InvokedClassName must not be null or empty!";
        assert parameters != null && parameters.length >= 1 : "Parameters must not be null and must contain at leat one element!";

        this.invokedClassName = invokedClassName;
        this.parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
    }

    /**
     * Stops the execution and adds the given cause to the list of execution
     * causes.
     *
     * @param cause
     *            The cause why the execution should be stopped.
     */
    public synchronized void stopExecution(final Exception cause) {
        this.executionStopped = true;

        this.executionStoppingCauses.add(cause);
    }

    /**
     *
     * @return The class which contains the method that was invoked.
     */
    public Class<?> getInvokedClass() {
        try {
            return Class.forName(this.invokedClassName);
        } catch (final ClassNotFoundException cause) {
            throw new InterceptionRuntimeException("An error occurred whilst retrieving the invoked class!", cause);
        }
    }

    /**
     *
     * @return The types of the parameters of the method which was invoked.
     */
    public Class<?>[] getParameterTypes() {
        final Class<?>[] result = new Class<?>[this.parameters.length];
        for (int i = 0; i < this.parameters.length; i++) {
            final Object parameter = this.parameters[i];
            result[i] = parameter == null ? Object.class : parameter.getClass();
        }
        return result;
    }

    /**
     *
     * @return {@link AbstractInterceptionEvent#invokedClassName}
     */
    public String getInvokedClassName() {
        return this.invokedClassName;
    }

    /**
     *
     * @return {@link AbstractInterceptionEvent#parameters}
     */
    public Object[] getParameters() {
        return this.parameters;
    }

    /**
     *
     * @return {@link AbstractInterceptionEvent#executionStopped}
     */
    public boolean isExecutionStopped() {
        return this.executionStopped;
    }

    /**
     *
     * @return {@link AbstractInterceptionEvent#executionStoppingCauses}
     */
    public List<Exception> getExecutionStoppingCauses() {
        return this.executionStoppingCauses;
    }
}
