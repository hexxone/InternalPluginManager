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

import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

/**
 * A utility class to convert Bukkit {@link org.bukkit.inventory.Inventory}s
 * into IPM {@link Inventory}s and the other way.
 *
 */
public class InventoryConverter {
    /**
     * Converts the given Bukkit {@link org.bukkit.inventory.Inventory} into an
     * IPM {@link Inventory}.
     *
     * @param bukkitInventory
     *            The Bukkit {@link org.bukkit.inventory.Inventory} to convert.
     * @return The converted IPM {@link Inventory}.
     */
    public static Inventory convertToIpmInventory(final org.bukkit.inventory.Inventory bukkitInventory) {
        assert bukkitInventory != null : "BukkitInventory cannot be null!";

        return null; // TODO
    }

    /**
     * Converts the given IPM {@link Inventory} into a Bukkit
     * {@link org.bukkit.inventory.Inventory}.
     *
     * @param ipmInventory
     *            The IPM {@link Inventory} to convert.
     * @return The converted Bukkit {@link org.bukkit.inventory.Inventory}.
     */
    public static org.bukkit.inventory.Inventory convertToBukkitInventory(final Inventory ipmInventory) {
        assert ipmInventory != null : "IpmInventory cannot be null!";

        return null; // TODO
    }
}
