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
package com.blockhaus2000.ipm.minecraft.canary;

import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.canary.command.CanaryCommandManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;

/**
 * The implementation of {@link Server} for Canary.
 *
 */
public class BukkitServer implements Server {
    private final PluginCommandManager commandManager = new CanaryCommandManager();

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getCommandManager()
     */
    @Override
    public PluginCommandManager getCommandManager() {
        return this.commandManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#println(java.lang.Object)
     */
    @Override
    public void println(final Object obj) {
        System.out.println("[CANARY] " + obj.toString());
    }
}
