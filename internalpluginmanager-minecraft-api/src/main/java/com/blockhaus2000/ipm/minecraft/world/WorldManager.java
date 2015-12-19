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
package com.blockhaus2000.ipm.minecraft.world;

import java.util.UUID;

/**
 * The {@link WorldManager} for this server.
 *
 */
public interface WorldManager {
    /**
     * Searchs for the {@link World} with the given {@link UUID}.
     *
     * @param uuid
     *            The {@link UUID} to search for.
     * @return The {@link World} that has the given {@link UUID}. Or, if no
     *         {@link World} was found, <code>null</code>.
     */
    World getWorld(final UUID uuid);

    /**
     * Searchs for the {@link World} with the given name.
     *
     * @param name
     *            The name to search for.
     * @return The {@link World} that has the given name. Or, if no
     *         {@link World} was found, <code>null</code>.
     */
    World getWorld(final String name);
}
