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
package com.blockhaus2000.ipm.minecraft;

import com.blockhaus2000.ipm.base.injection.Inject;
import com.blockhaus2000.ipm.base.injection.InjectionManager;

/**
 * This is the main class of the InternalPluginManager. This class cotains some
 * utility methods to access the implementation for the specific server versions
 * and some other stuff.
 *
 */
public class InternalPluginManager {
    /**
     * The {@link Server} instance. This is injected by the
     * {@link InjectionManager}, cause this is the connector to the underlying
     * implementation.
     *
     */
    @Inject
    private static Server server;

    static {
        InjectionManager.init(InternalPluginManager.class);
    }

    /**
     *
     * @return {@link InternalPluginManager#server}
     */
    public static Server getServer() {
        return InternalPluginManager.server;
    }
}
