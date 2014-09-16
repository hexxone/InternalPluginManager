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
package com.blockhaus2000.ipm.minecraft.bukkit.command;

import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.command.SimplePluginCommandManager;

/**
 * A special extension for the {@link SimplePluginCommandManager} for Bukkit to
 * hack into the command system of Bukkit.
 *
 */
public class BukkitCommandManager extends SimplePluginCommandManager {
    /**
     * THE instance of the {@link BukkitCommandManager}.
     *
     */
    private static final PluginCommandManager INSTANCE = new BukkitCommandManager();

    /**
     * Constructor of BukkitCommandManager.
     *
     */
    private BukkitCommandManager() {
        // Nothing to do (only to provide singleton).
    }

    public static final PluginCommandManager getInstance() {
        return BukkitCommandManager.INSTANCE;
    }
}
