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
package com.blockhaus2000.ipm.minecraft.mocked;

import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;
import com.blockhaus2000.ipm.minecraft.event.EventManager;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;

/**
 * A mock implementation of {@link Server}.
 *
 */
public class MockedServer implements Server {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getCommandManager()
     */
    @Override
    public PluginCommandManager getCommandManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getEventManager()
     */
    @Override
    public EventManager getEventManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPluginManager()
     */
    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getLogger()
     */
    @Override
    public Logger getLogger() {
        return Logger.getLogger(this.getClass().getName() + "_LOGGER");
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public Player getPlayer(final String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.util.UUID)
     */
    @Override
    public Player getPlayer(final UUID uuid) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getOnlinePlayers()
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        return null;
    }
}
