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
package com.blockhaus2000.ipm.minecraft.bukkit.util;

import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

/**
 * A utility class to work with inventories.
 *
 */
public final class InventoryUtil {
    /**
     * Constructor of InventoryUtil.
     *
     */
    public InventoryUtil() {
        // Nothing to do.
    }

    /**
     * Copies the content of the Bukkit {@link org.bukkit.inventory.Inventory}
     * into the {@link Inventory}.
     *
     * <p>
     * <b> NOTE: Every setting will be copied, except the {@link UUID}. </b>
     * </p>
     *
     * @param ipmInventory
     *            The {@link Inventory} to copy the items into.
     * @param bukkitInventory
     *            The Bukkit {@link org.bukkit.inventory.Inventory} to copy the
     *            items from.
     */
    public static void copyContent(final Inventory ipmInventory, final org.bukkit.inventory.Inventory bukkitInventory) {
        // TODO: Implement inventory copying.
    }
}
