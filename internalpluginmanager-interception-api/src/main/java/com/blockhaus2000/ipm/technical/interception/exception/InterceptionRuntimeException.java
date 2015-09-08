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
 * The root (runtime) exception for any runtime exception thrown by the
 * interception API.
 *
 */
public class InterceptionRuntimeException extends RuntimeException {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -5055332499529506254L;

    /**
     * Constructor of InterceptionRuntimeException.
     *
     */
    public InterceptionRuntimeException() {
        super();
    }

    /**
     * Constructor of InterceptionRuntimeException.
     *
     * @param message
     *            The detailed error message.
     * @param cause
     *            The cause of this exception.
     */
    public InterceptionRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of InterceptionRuntimeException.
     *
     * @param message
     *            The detailed error message.
     */
    public InterceptionRuntimeException(final String message) {
        super(message);
    }

    /**
     * Constructor of InterceptionRuntimeException.
     *
     * @param cause
     *            The cause of this exception.
     */
    public InterceptionRuntimeException(final Throwable cause) {
        super(cause);
    }
}
