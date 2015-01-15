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
package com.blockhaus2000.ipm.minecraft.bukkit.util.converter;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.world.World;

/**
 * A utility class to convert Bukkit {@link org.bukkit.World}s into IPM
 * {@link World}s and the other way.
 *
 */
public final class WorldConverter {
    /**
     * Constructor of WorldConverter.
     *
     */
    private WorldConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link org.bukkit.World} into an IPM
     * {@link World}.
     *
     * @param bukkitWorld
     *            The Bukkit {@link org.bukkit.World} to convert.
     * @return The converted IPM {@link World}.
     */
    public static World convertToIpmWorld(final org.bukkit.World bukkitWorld) {
        assert bukkitWorld != null : "BukkitWorld cannot be null!";

        return InternalPluginManager.getServer().getWorldManager().getWorld(bukkitWorld.getName());
    }

    /**
     * Converts the given IPM {@link World} into a Bukkit
     * {@link org.bukkit.World}.
     *
     * @param ipmWorld
     *            The IPM {@link World} to convert.
     * @return The converted Bukkit {@link org.bukkit.World}.
     */
    public static org.bukkit.World convertToBukkitWorld(final World ipmWorld) {
        assert ipmWorld != null : "IpmWorld cannot be null!";

        return Bukkit.getServer().getWorld(ipmWorld.getUUID());
    }
}
