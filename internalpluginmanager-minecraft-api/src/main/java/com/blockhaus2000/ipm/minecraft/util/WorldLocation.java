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
package com.blockhaus2000.ipm.minecraft.util;

import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.world.World;

/**
 * Represents a location in world.
 *
 */
public class WorldLocation {
    /**
     * The {@link Vector} in the world.
     *
     */
    private final Vector vector;
    /**
     * The {@link UUID} of the world of this location.
     *
     */
    private final UUID world;

    /**
     * Constructor of WorldLocation.
     *
     * @param vector
     *            The {@link Vector} this location is located in the given
     *            world.
     * @param world
     *            The {@link World} this location is located in.
     */
    public WorldLocation(final Vector vector, final World world) {
        assert world != null : "World cannot be null!";

        this.vector = vector;
        this.world = world.getUUID();
    }

    /**
     * Constructor of WorldLocation.
     *
     * @param x
     *            The <code>x</code> value.
     * @param y
     *            The <code>x</code> value.
     * @param z
     *            The <code>z</code> value.
     * @param world
     *            The world this location is located in.
     */
    public WorldLocation(final int x, final int y, final int z, final World world) {
        this(new Vector(x, y, z), world);
    }

    /**
     *
     * @return {@link WorldLocation#vector}
     */
    public Vector getVector() {
        return this.vector;
    }

    /**
     *
     * @return {@link WorldLocation#world}
     */
    public World getWorld() {
        return InternalPluginManager.getServer().getWorldManager().getWorld(this.world);
    }
}
