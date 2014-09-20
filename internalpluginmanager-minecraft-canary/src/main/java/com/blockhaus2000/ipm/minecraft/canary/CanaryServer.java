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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import net.canarymod.Canary;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;
import com.blockhaus2000.ipm.minecraft.canary.command.CanaryCommandManager;
import com.blockhaus2000.ipm.minecraft.canary.entity.CanaryPlayer;
import com.blockhaus2000.ipm.minecraft.event.EventManager;
import com.blockhaus2000.ipm.minecraft.event.SimpleEventManager;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;

/**
 * The implementation of {@link Server} for Canary.
 *
 */
public class CanaryServer implements Server {
    /**
     * THE instance of the {@link CanaryServer}.
     *
     */
    private static final Server INSTANCE = new CanaryServer();

    private final PluginCommandManager commandManager;
    private final EventManager eventManager;
    private final PluginManager pluginManager;
    private final Logger logger;

    /**
     * Constructor of BukkitServer.
     *
     * <p>
     * Initilizes all fields and registers itself to the injection manager.
     * </p>
     *
     */
    private CanaryServer() {
        InjectionManager.addResource(this, Server.class);

        this.commandManager = new CanaryCommandManager();
        this.eventManager = new SimpleEventManager();
        this.pluginManager = PluginManager.getInstance();
        this.logger = Logger.getLogger(this.getClass().getPackage().getName() + ".LOGGER@" + System.currentTimeMillis());
    }

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
     * @see com.blockhaus2000.ipm.minecraft.Server#getEventManager()
     */
    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPluginManager()
     */
    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getLogger()
     */
    @Override
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public Player getPlayer(final String name) {
        for (final Player player : this.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.util.UUID)
     */
    @Override
    public Player getPlayer(final UUID uuid) {
        for (final Player player : this.getOnlinePlayers()) {
            if (player.getUniqueId().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getOnlinePlayers()
     */
    @Override
    public Set<Player> getOnlinePlayers() {
        final Set<Player> players = new HashSet<Player>();

        for (final net.canarymod.api.entity.living.humanoid.Player player : Canary.getServer().getPlayerList()) {
            players.add(CanaryPlayer.CanaryPlayerFactory.getCanaryPlayer(player));
        }

        return players;
    }

    /**
     *
     * @return {@link CanaryServer#INSTANCE}
     */
    public static Server getInstance() {
        return CanaryServer.INSTANCE;
    }
}
