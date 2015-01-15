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
 */
package com.blockhaus2000.ipm.base.injection.exception;

/**
 * This exception is thrown in the
 * {@link com.blockhaus2000.ipm.base.injection.InjectionManager} if a value is
 * requested that is not mapped (yet).
 *
 */
public class NotAddedInjectionException extends InjectionException {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = 4661674843310112612L;

    /**
     * Constructor of NotAddedInjectionException.
     *
     */
    public NotAddedInjectionException() {
        super();
    }

    /**
     * Constructor of NotAddedInjectionException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public NotAddedInjectionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of NotAddedInjectionException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     */
    public NotAddedInjectionException(final String message) {
        super(message);
    }

    /**
     * Constructor of NotAddedInjectionException.
     *
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public NotAddedInjectionException(final Throwable cause) {
        super(cause);
    }
}
