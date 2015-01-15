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

import org.bukkit.Location;

import com.blockhaus2000.ipm.minecraft.util.WorldLocation;

/**
 * A utility class to convert Bukkit {@link Location}s into IPM
 * {@link WorldLocation}s and the other way.
 *
 */
public final class LocationConverter {
    /**
     * Constructor of LocationConverter.
     *
     */
    private LocationConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link Location} into an IPM
     * {@link WorldLocation}.
     *
     * @param bukkitLocation
     *            The Bukkit {@link Location} to convert.
     * @return The converted IPM {@link WorldLocation}.
     */
    public static WorldLocation convertToIpmLocation(final Location bukkitLocation) {
        assert bukkitLocation != null : "BukkitLocation cannot be null!";

        return new WorldLocation(bukkitLocation.getBlockX(), bukkitLocation.getBlockY(), bukkitLocation.getBlockZ(),
                WorldConverter.convertToIpmWorld(bukkitLocation.getWorld()));
    }

    /**
     * Converts the given IPM {@link WorldLocation} into a Bukkit
     * {@link Location}.
     *
     * @param ipmLocation
     *            The IPM {@link WorldLocation} to convert.
     * @return The converted Bukkit {@link Location}.
     */
    public static Location convertToBukkitLocation(final WorldLocation ipmLocation) {
        assert ipmLocation != null : "IpmLocation cannot be null!";

        return new Location(WorldConverter.convertToBukkitWorld(ipmLocation.getWorld()), ipmLocation.getVector().getX(),
                ipmLocation.getVector().getY(), ipmLocation.getVector().getZ());
    }
}
