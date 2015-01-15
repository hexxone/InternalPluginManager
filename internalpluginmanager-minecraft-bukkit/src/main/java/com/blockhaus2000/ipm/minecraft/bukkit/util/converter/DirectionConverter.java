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
package com.blockhaus2000.ipm.minecraft.bukkit.util.converter;

import org.bukkit.block.BlockFace;

import com.blockhaus2000.ipm.minecraft.Direction;

/**
 * A utility class to convert Bukkit {@link BlockFace}s into IPM
 * {@link Direction}s and the other way.
 *
 */
public final class DirectionConverter {
    /**
     * Constructor of DirectionConverter.
     *
     */
    private DirectionConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link BlockFace} into an IPM {@link Direction}
     * .
     *
     * @param bukkitDirection
     *            The Bukkit {@link BlockFace} to convert.
     * @return The converted IPM {@link Direction}.
     */
    public static Direction convertToIpmDirection(final BlockFace bukkitDirection) {
        final Direction direction;
        switch (bukkitDirection == null ? BlockFace.SELF : bukkitDirection) {
            case UP:
                direction = Direction.UP;
                break;
            case DOWN:
                direction = Direction.DOWN;
                break;

            case NORTH:
                direction = Direction.NORTH;
                break;
            case NORTH_NORTH_EAST:
                direction = Direction.NORTH_NORTH_EAST;
                break;
            case NORTH_EAST:
                direction = Direction.NORTH_EAST;
                break;
            case EAST_NORTH_EAST:
                direction = Direction.NORTH_EAST_EAST;
                break;

            case EAST:
                direction = Direction.EAST;
                break;
            case EAST_SOUTH_EAST:
                direction = Direction.EAST_EAST_SOUTH;
                break;
            case SOUTH_EAST:
                direction = Direction.EAST_SOUTH;
                break;
            case SOUTH_SOUTH_EAST:
                direction = Direction.EAST_SOUTH_SOUTH;
                break;

            case SOUTH:
                direction = Direction.SOUTH;
                break;
            case SOUTH_SOUTH_WEST:
                direction = Direction.SOUTH_SOUTH_WEST;
                break;
            case SOUTH_WEST:
                direction = Direction.SOUTH_WEST;
                break;
            case WEST_SOUTH_WEST:
                direction = Direction.SOUTH_WEST_WEST;
                break;

            case WEST:
                direction = Direction.WEST;
                break;
            case WEST_NORTH_WEST:
                direction = Direction.WEST_WEST_NORTH;
                break;
            case NORTH_WEST:
                direction = Direction.WEST_NORTH;
                break;
            case NORTH_NORTH_WEST:
                direction = Direction.WEST_NORTH_NORTH;
                break;

            case SELF:
            default:
                direction = null;
                break;
        }
        return direction;
    }

    /**
     * Converts the given IPM {@link Direction} into a Bukkit {@link BlockFace}.
     *
     * @param ipmDirection
     *            The IPM {@link Direction} to convert.
     * @return The converted Bukkit {@link BlockFace}.
     */
    public static BlockFace convertToBukkitDirection(final Direction ipmDirection) {
        final BlockFace direction;
        if (ipmDirection != null) {
            switch (ipmDirection) {
                case UP:
                    direction = BlockFace.UP;
                    break;
                case DOWN:
                    direction = BlockFace.DOWN;
                    break;

                case NORTH:
                    direction = BlockFace.NORTH;
                    break;
                case NORTH_NORTH_EAST:
                    direction = BlockFace.NORTH_NORTH_EAST;
                    break;
                case NORTH_EAST:
                    direction = BlockFace.NORTH_EAST;
                    break;
                case NORTH_EAST_EAST:
                    direction = BlockFace.EAST_NORTH_EAST;
                    break;

                case EAST:
                    direction = BlockFace.EAST;
                    break;
                case EAST_EAST_SOUTH:
                    direction = BlockFace.EAST_SOUTH_EAST;
                    break;
                case EAST_SOUTH:
                    direction = BlockFace.SOUTH_EAST;
                    break;
                case EAST_SOUTH_SOUTH:
                    direction = BlockFace.SOUTH_SOUTH_EAST;
                    break;

                case SOUTH:
                    direction = BlockFace.SOUTH;
                    break;
                case SOUTH_SOUTH_WEST:
                    direction = BlockFace.SOUTH_SOUTH_WEST;
                    break;
                case SOUTH_WEST:
                    direction = BlockFace.SOUTH_WEST;
                    break;
                case SOUTH_WEST_WEST:
                    direction = BlockFace.WEST_SOUTH_WEST;
                    break;

                case WEST:
                    direction = BlockFace.WEST;
                    break;
                case WEST_WEST_NORTH:
                    direction = BlockFace.WEST_NORTH_WEST;
                    break;
                case WEST_NORTH:
                    direction = BlockFace.NORTH_WEST;
                    break;
                case WEST_NORTH_NORTH:
                    direction = BlockFace.NORTH_NORTH_WEST;
                    break;

                default:
                    direction = BlockFace.SELF;
                    break;
            }
        } else {
            direction = BlockFace.SELF;
        }
        return direction;
    }
}
