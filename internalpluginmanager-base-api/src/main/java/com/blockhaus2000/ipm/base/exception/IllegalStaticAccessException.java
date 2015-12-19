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
package com.blockhaus2000.ipm.base.exception;

/**
 * This exception can be thrown whilest using exception and indicates that a
 * non-static element was tried to access staticly.
 *
 */
public class IllegalStaticAccessException extends RuntimeException {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = -4204719741279343505L;

    /**
     * Constructor of IllegalStaticAccessException.
     *
     */
    public IllegalStaticAccessException() {
        super();
    }

    /**
     * Constructor of IllegalStaticAccessException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public IllegalStaticAccessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of IllegalStaticAccessException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     */
    public IllegalStaticAccessException(final String message) {
        super(message);
    }

    /**
     * Constructor of IllegalStaticAccessException.
     *
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public IllegalStaticAccessException(final Throwable cause) {
        super(cause);
    }
}
