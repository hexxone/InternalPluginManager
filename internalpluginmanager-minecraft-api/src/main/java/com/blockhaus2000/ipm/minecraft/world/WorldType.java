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
package com.blockhaus2000.ipm.minecraft.world;

/**
 * All available world types.
 *
 */
public enum WorldType {
    /**
     * The default world type.
     *
     */
    NORMAL("DEFAULT"),
    /**
     * The type that represents a flat world.
     *
     */
    FLAT("FLAT"),
    /**
     * The large biomes world type.
     *
     */
    LARGE_BIOMES("LARGEBIOMES"),
    /**
     * The amplified biomes world type.
     *
     */
    AMPLIFIED("AMPLIFIED"),
    /**
     * Represents a customized world.
     *
     */
    CUSTOMIZED("CUSTOMIZED");

    /**
     * The name of this world type.
     *
     */
    private final String name;

    /**
     * Constructor of WorldType.
     *
     * @param name
     *            The name of this world type.
     */
    private WorldType(final String name) {
        this.name = name;
    }

    /**
     * Searchs for the {@link WorldType} that is associated with the given name.
     *
     * @param name
     *            The name to search for.
     * @return The {@link WorldType} that is associated with the given name. If
     *         nothing was found, this returns <code>null</code>.
     */
    public static WorldType getByName(final String name) {
        for (final WorldType worldType : WorldType.values()) {
            if (worldType.getName().equalsIgnoreCase(name)) {
                return worldType;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link WorldType#name}
     */
    public String getName() {
        return this.name;
    }
}
