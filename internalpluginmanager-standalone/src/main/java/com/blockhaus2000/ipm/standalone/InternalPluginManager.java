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
package com.blockhaus2000.ipm.standalone;

import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.command.SimplePluginCommandManager;

/**
 * This utility class only provides some methods that are important to use the
 * InternalPluginManager.
 *
 */
public class InternalPluginManager {
    /**
     * The one and only command manager that should be used.
     *
     */
    private static final PluginCommandManager COMMAND_MANAGER = new SimplePluginCommandManager();

    /**
     *
     * @return {@link InternalPluginManager#COMMAND_MANAGER}
     */
    public static PluginCommandManager getCommandManager() {
        return InternalPluginManager.COMMAND_MANAGER;
    }
}
