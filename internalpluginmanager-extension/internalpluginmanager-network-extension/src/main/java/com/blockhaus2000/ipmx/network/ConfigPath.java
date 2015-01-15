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
package com.blockhaus2000.ipmx.network;

/**
 * The paths for the configuration.
 *
 */
public enum ConfigPath {
    /**
     * The connection/server port.
     *
     */
    CONNECTION_PORT("connection.port"),

    /**
     * Whether the whitelist is enabled.
     *
     */
    WHITELIST_ENABLED("whitelist.enabled"),
    /**
     * The whitelist.
     *
     */
    WHITELIST_WHITELIST("whitelist.whitelist");

    /**
     * The config path.
     *
     */
    private final String path;

    /**
     * Constructor of ConfigPath.
     *
     * @param path
     *            The config path.
     */
    private ConfigPath(final String path) {
        this.path = path;
    }

    /**
     *
     * @return {@link ConfigPath#path}
     */
    public String getPath() {
        return this.path;
    }
}
