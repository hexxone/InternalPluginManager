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

public class Location {
    private final int x;
    private final int y;
    private final int z;

    public Location(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location add(final Location loc) {
        return new Location(this.x + loc.getX(), this.y + loc.getY(), this.z + loc.getZ());
    }

    /**
     *
     * @return {@link Location#x}.
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return {@link Location#y}.
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @return {@link Location#z}.
     */
    public int getZ() {
        return this.z;
    }
}
