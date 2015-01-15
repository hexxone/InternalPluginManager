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
package com.blockhaus2000.ipm.minecraft.bukkit.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

/**
 * The Bukkit implementation of {@link Inventory}.
 *
 */
public class BukkitInventory implements Inventory {
    /**
     * The {@link UUID} of this {@link Inventory}.
     *
     */
    private final UUID uuid = UUID.randomUUID();

    /**
     * Constructor of BukkitInventory.
     *
     * <p>
     * <b> NOTE: Do not invoke this constructor directly. Use The
     * {@link BukkitInventory.Factory} instead. </b>
     * </p>
     *
     * @param bukkitInventory
     *            The Bukkit {@link org.bukkit.inventory.Inventory} to retrieve
     *            required information from to create an {@link Inventory} from
     *            an existing {@link org.bukkit.inventory.Inventory}.
     */
    public BukkitInventory(final org.bukkit.inventory.Inventory bukkitInventory) {
        assert bukkitInventory != null : "BukkitInventory cannot be null!";

        // TODO: Implement inventory wrapping.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.inventory.Inventory#getUUID()
     */
    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * The factory for {@link BukkitInventory}s.
     *
     */
    public static final class Factory {
        /**
         * A list of all created {@link Inventory}s.
         *
         */
        private static final Map<UUID, BukkitInventory> INVENTORIES = new HashMap<UUID, BukkitInventory>();

        /**
         * Constructor of BukkitInventory.Factory.
         *
         */
        private Factory() {
            // Nothing to do.
        }

        /**
         * Creates a new {@link Inventory} that wraps the given
         * {@link org.bukkit.inventory.Inventory} from Bukkit.
         *
         * @param bukkitInventory
         *            The Bukkit {@link org.bukkit.inventory.Inventory} to wrap.
         * @return The newly created {@link org.bukkit.inventory.Inventory}.
         */
        public static Inventory create(final org.bukkit.inventory.Inventory bukkitInventory) {
            assert bukkitInventory != null : "BukkitInventory cannot be null!";

            final BukkitInventory inventory = new BukkitInventory(bukkitInventory);

            Factory.INVENTORIES.put(inventory.getUUID(), inventory);

            return inventory;
        }
    }
}
