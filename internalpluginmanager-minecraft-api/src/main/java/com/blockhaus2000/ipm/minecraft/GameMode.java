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
 */
package com.blockhaus2000.ipm.minecraft;

/**
 * An enum of all available game modes.
 *
 * <p>
 * Every game mode is assoiated with an ID. The associations are:
 * <p>
 * <table border="1">
 * <tr>
 * <th>GameMode</th>
 * <th>ID</th>
 * </tr>
 * <tr>
 * <td>{@link GameMode#SURVIVAL}</td>
 * <td>0</td>
 * </tr>
 * <tr>
 * <td>{@link GameMode#CREATIVE}</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>{@link GameMode#ADVENTURE}</td>
 * <td>2</td>
 * </tr>
 * </table>
 * </p>
 * </p>
 *
 */
public enum GameMode {
    /**
     * The survival game mode.
     *
     */
    SURVIVAL(0),
    /**
     * The creative game mode.
     *
     */
    CREATIVE(1),
    /**
     * The adventure game mode.
     *
     */
    ADVENTURE(3);

    /**
     * The id of this game mode.
     *
     */
    private final int id;

    /**
     * Constructor of GameMode.
     *
     * @param id
     *            The id of this game mode.
     */
    private GameMode(final int id) {
        this.id = id;
    }

    /**
     *
     * @return The id of this game mode.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Search for the game mode with the given ID.
     *
     * @param id
     *            The ID to search for.
     * @return The game mode, if found. Otherwise <code>null</code>.
     */
    public static GameMode getById(final int id) {
        for (final GameMode gameMode : GameMode.values()) {
            if (gameMode.getId() == id) {
                return gameMode;
            }
        }
        return null;
    }
}
