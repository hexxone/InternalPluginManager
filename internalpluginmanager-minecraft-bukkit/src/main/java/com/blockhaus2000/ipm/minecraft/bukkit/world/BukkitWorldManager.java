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
package com.blockhaus2000.ipm.minecraft.bukkit.world;

import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.world.World;
import com.blockhaus2000.ipm.minecraft.world.WorldManager;

/**
 * The Bukkit implementation of {@link WorldManager}.
 *
 */
public class BukkitWorldManager implements WorldManager {
    /**
     * Constructor of BukkitWorldManager.
     *
     */
    public BukkitWorldManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.WorldManager#getWorld(java.util.UUID)
     */
    @Override
    public World getWorld(final UUID uuid) {
        assert uuid != null : "Uuid cannot be null!";

        return BukkitWorld.Factory.create(uuid);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.WorldManager#getWorld(java.lang.String)
     */
    @Override
    public World getWorld(final String name) {
        assert name != null : "Name cannot be null!";

        return this.getWorld(Bukkit.getServer().getWorld(name).getUID());
    }
}
