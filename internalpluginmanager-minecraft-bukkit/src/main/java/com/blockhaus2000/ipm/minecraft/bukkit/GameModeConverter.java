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
package com.blockhaus2000.ipm.minecraft.bukkit;

import com.blockhaus2000.ipm.minecraft.GameMode;

/**
 * A utility class to convert a Bukkit {@link org.bukkit.GameMode} into an IPM
 * {@link GameMode}s and the other way.
 *
 */
public class GameModeConverter {
    /**
     * Constructor of GameModeConverter.
     *
     */
    private GameModeConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link GameMode} into an IPM {@link GameMode}.
     *
     * @param bukkitGameMode
     *            The Bukkit {@link org.bukkit.GameMode} to convert.
     * @return The converted IPM {@link GameMode}.
     */
    public static GameMode convertToIpmGameMode(final org.bukkit.GameMode bukkitGameMode) {
        assert bukkitGameMode != null : "BukkitGameMode cannot be null!";

        switch (bukkitGameMode) {
            case ADVENTURE:
                return GameMode.ADVENTURE;
            case CREATIVE:
                return GameMode.CREATIVE;
            case SURVIVAL:
                return GameMode.SURVIVAL;
            default:
                return null;
        }
    }

    /**
     * Converts the given IPM {@link GameMode} into an Bukkit {@link GameMode}.
     *
     * @param ipmGameMode
     *            The converted IPM {@link GameMode}.
     * @return The Bukkit {@link org.bukkit.GameMode} to convert.
     */
    public static org.bukkit.GameMode convertToBukkitGameMode(final GameMode ipmGameMode) {
        assert ipmGameMode != null : "IpmGameMode cannot be null!";

        switch (ipmGameMode) {
            case ADVENTURE:
                return org.bukkit.GameMode.ADVENTURE;
            case CREATIVE:
                return org.bukkit.GameMode.CREATIVE;
            case SURVIVAL:
                return org.bukkit.GameMode.SURVIVAL;
            default:
                return null;
        }
    }
}
