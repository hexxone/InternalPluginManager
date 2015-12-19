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
 * This exception can be thrown whilest using reflection, if a method signature
 * is invalid (if you will invoke that method, and you check the argument types
 * before).
 *
 * <p>
 * <b>Example:</b>
 * <p>
 * You are excpeting a method signature like <code>XXXX(java.lang.String)</code>
 * , and you get a signature like <code>XXXX(java.lang.Integer</code>, you can
 * throw this exception.
 * </p>
 * </p>
 *
 */
public class IllegalMethodSignatureException extends RuntimeException {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = -8802427634853307312L;

    /**
     * Constructor of IllegalMethodSignatureException.
     *
     */
    public IllegalMethodSignatureException() {
        super();
    }

    /**
     * Constructor of IllegalMethodSignatureException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public IllegalMethodSignatureException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of IllegalMethodSignatureException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     */
    public IllegalMethodSignatureException(final String message) {
        super(message);
    }

    /**
     * Constructor of IllegalMethodSignatureException.
     *
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public IllegalMethodSignatureException(final Throwable cause) {
        super(cause);
    }
}
