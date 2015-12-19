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
package com.blockhaus2000.ipm.minecraft.bukkit.util.converter;

import com.blockhaus2000.ipm.minecraft.entity.Entity;

/**
 * A utility class to convert Bukkit {@link org.bukkit.entity.Entity}s into IPM
 * {@link Entity}s and the other way.
 *
 */
public class EntityConverter {
    /**
     * Constructor of EntityConverter.
     *
     */
    private EntityConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link org.bukkit.entity.Entity} into an IPM
     * {@link Entity}.
     *
     * @param bukkitEntity
     *            The Bukkit {@link org.bukkit.entity.Entity} to convert.
     * @return The converted IPM {@link Entity}.
     */
    public static Entity convertToIpmEntity(final org.bukkit.entity.Entity bukkitEntity) {
        return null; // TODO
    }

    /**
     * Converts the given IPM {@link Entity} into a Bukkit
     * {@link org.bukkit.entity.Entity}.
     *
     * @param ipmEntity
     *            The IPM {@link Entity} to convert.
     * @return The converted Bukkit {@link org.bukkit.entity.Entity}.
     */
    public static org.bukkit.entity.Entity convertToBukkitEntity(final Entity ipmEntity) {
        return null; // TODO
    }
}
