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
package com.blockhaus2000.ipm.technical.plugin.dependency.exception;

import com.blockhaus2000.ipm.technical.plugin.dependency.DependencyBuilder;

/**
 * This Exception is thrown, if the {@link DependencyBuilder} can not resolve a
 * plugin name to a (loaded) plugin.
 *
 * @see com.blockhaus2000.ipm.technical.plugin.dependency.DependencyBuilder
 */
public class UnresolveableDependencyException extends Exception {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 7354496454317271516L;

    /**
     * Constructor of UnresolveableDependencyException.
     *
     * @param message
     *            Is passed into <code>super</code>-call.
     */
    public UnresolveableDependencyException(final String message) {
        super(message);
    }
}
