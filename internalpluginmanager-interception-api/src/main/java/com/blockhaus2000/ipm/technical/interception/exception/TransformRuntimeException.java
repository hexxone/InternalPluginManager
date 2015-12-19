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
package com.blockhaus2000.ipm.technical.interception.exception;

/**
 * The same as {@link TransformException}, except that this one is a
 * {@link RuntimeException}.
 *
 */
public class TransformRuntimeException extends InterceptionRuntimeException {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 1863206145933216365L;

    /**
     * Constructor of TransformRuntimeException.
     *
     */
    public TransformRuntimeException() {
        super();
    }

    /**
     * Constructor of TransformRuntimeException.
     *
     * @param message
     *            The detailed error message.
     * @param cause
     *            The cause of this exception.
     */
    public TransformRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of TransformRuntimeException.
     *
     * @param message
     *            The detailed error message.
     */
    public TransformRuntimeException(final String message) {
        super(message);
    }

    /**
     * Constructor of TransformRuntimeException.
     *
     * @param cause
     *            The cause of this exception.
     */
    public TransformRuntimeException(final Throwable cause) {
        super(cause);
    }
}
