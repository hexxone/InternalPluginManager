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
package com.blockhaus2000.ipm.technical.plugin.util.exception;

import java.util.Arrays;

import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * If a plugin misses dependencies whilest enabling, this exception is thrown.
 *
 */
public class MissingDependencyPluginException extends PluginException {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = 9010284243773593378L;

    /**
     * Constructor of MissingDependencyPluginException.
     *
     * @param plugin
     *            The {@link Plugin} that misses dependencies.
     * @param missingDependencies
     *            The names of the missing dependencies.
     */
    public MissingDependencyPluginException(final Plugin plugin, final String... missingDependencies) {
        super("Missing dependencies (" + Arrays.toString(missingDependencies) + ") for plugin \"" + plugin.getName() + "\"!");
    }
}
