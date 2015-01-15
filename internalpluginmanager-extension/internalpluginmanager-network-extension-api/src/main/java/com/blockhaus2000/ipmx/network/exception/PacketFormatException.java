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
package com.blockhaus2000.ipmx.network.exception;

/**
 * The packet format exception can be thrown if an illegal packet format was
 * detected.
 *
 */
public class PacketFormatException extends Exception {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -1554031991012449067L;

    /**
     * Constructor of PacketFormatException.
     *
     * @param message
     *            Is passed into <code>super</code>-call.
     */
    public PacketFormatException(final String message) {
        super(message);
    }
}
