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
package com.blockhaus2000.ipm.minecraft.util;

/**
 * A Vector in a 3D room.
 *
 */
public class Vector {
    /**
     * The <code>x</code> value.
     *
     */
    private final int x;
    /**
     * The <code>y</code> value.
     *
     */
    private final int y;
    /**
     * The <code>z</code> value.
     *
     */
    private final int z;

    /**
     * Constructor of Vector.
     *
     * @param x
     *            The <code>x</code> value.
     * @param y
     *            The <code>y</code> value.
     * @param z
     *            The <code>z</code> value.
     */
    public Vector(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds this {@link Vector} and the given {@link Vector}.
     *
     * @param vec
     *            The {@link Vector} to add to this {@link Vector},
     * @return The calculated {@link Vector}.
     */
    public Vector add(final Vector vec) {
        return new Vector(this.x + vec.getX(), this.y + vec.getY(), this.z + vec.getZ());
    }

    /**
     *
     * @return {@link Vector#x}.
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return {@link Vector#y}.
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @return {@link Vector#z}.
     */
    public int getZ() {
        return this.z;
    }
}
