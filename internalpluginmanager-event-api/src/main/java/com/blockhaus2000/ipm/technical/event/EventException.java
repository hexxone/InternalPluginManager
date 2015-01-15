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

/**
 * This exception is thrown if an error occurres whilest registering events.
 *
 */
public class EventException extends RuntimeException {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = -4134588475064755920L;

    /**
     * Constructor of EventException.
     *
     */
    public EventException() {
        super();
    }

    /**
     * Constructor of EventException.
     *
     * @param message
     *            Is passed into <code>super</code> call.
     * @param cause
     *            Is passed into <code>super</code> call.
     */
    public EventException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of EventException.
     *
     * @param message
     *            Is passed into <code>super</code> call.
     */
    public EventException(final String message) {
        super(message);
    }

    /**
     * Constructor of EventException.
     *
     * @param cause
     *            Is passed into <code>super</code> call.
     */
    public EventException(final Throwable cause) {
        super(cause);
    }
}
