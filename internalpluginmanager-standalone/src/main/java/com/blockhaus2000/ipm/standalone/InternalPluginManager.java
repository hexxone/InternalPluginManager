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
package com.blockhaus2000.ipm.standalone;

import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.command.SimplePluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager;
import com.blockhaus2000.ipm.technical.plugin.event.SimplePluginEventManager;

/**
 * This utility class only provides some methods that are important to use the
 * InternalPluginManager.
 *
 */
public final class InternalPluginManager {
    /**
     * The one and only command manager that should be used.
     *
     */
    private static final PluginCommandManager COMMAND_MANAGER = new SimplePluginCommandManager();

    /**
     * The one and only event manager that should be used.
     *
     */
    private static final PluginEventManager EVENT_MANAGER = new SimplePluginEventManager();

    /**
     * Constructor of InternalPluginManager.
     *
     */
    private InternalPluginManager() {
        // Utility classes should not have a visible constructor.
    }

    /**
     *
     * @return {@link InternalPluginManager#COMMAND_MANAGER}
     */
    public static PluginCommandManager getCommandManager() {
        return InternalPluginManager.COMMAND_MANAGER;
    }

    /**
     *
     * @return {@link InternalPluginManager#EVENT_MANAGER}
     */
    public static PluginEventManager getEventManager() {
        return InternalPluginManager.EVENT_MANAGER;
    }
}
