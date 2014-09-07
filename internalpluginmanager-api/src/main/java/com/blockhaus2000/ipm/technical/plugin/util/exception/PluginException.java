/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
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
package com.blockhaus2000.ipm.technical.plugin.util.exception;

/**
 * A general {@link Exception} that can be thrown if an error occurres in the
 * context of a plugins.
 *
 */
public class PluginException extends RuntimeException {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = 8512715140829394436L;

    /**
     * Constructor of PluginException.
     *
     */
    public PluginException() {
        super();
    }

    /**
     * Constructor of PluginException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public PluginException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of PluginException.
     *
     * @param message
     *            Will be used in <code>super</code> call.
     */
    public PluginException(final String message) {
        super(message);
    }

    /**
     * Constructor of PluginException.
     *
     * @param cause
     *            Will be used in <code>super</code> call.
     */
    public PluginException(final Throwable cause) {
        super(cause);
    }
}
