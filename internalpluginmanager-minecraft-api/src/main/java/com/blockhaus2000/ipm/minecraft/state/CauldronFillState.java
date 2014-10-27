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
package com.blockhaus2000.ipm.minecraft.state;

/**
 * The fill states of a cauldron.
 *
 */
public enum CauldronFillState {
    /**
     * The couldron is empty.
     *
     * <p>
     * <code>
     * | - | <br />
     * | - | <br />
     * | - | <br />
     * |===| <br />
     * </code>
     * </p>
     */
    EMPTY,
    /**
     * The cauldron is third-full.
     *
     * <p>
     * <code>
     * | - | <br />
     * | - | <br />
     * |ZZZ| <br />
     * |===| <br />
     * </code>
     * </p>
     */
    THIRD_FULL,
    /**
     * The cauldron is three-thrid full.
     *
     * <p>
     * <code>
     * | - | <br />
     * |ZZZ| <br />
     * |ZZZ| <br />
     * |===| <br />
     * </code>
     * </p>
     */
    TWO_THIRD_FULL,
    /**
     * The cauldron is full.
     *
     * <p>
     * <code>
     * |ZZZ| <br />
     * |ZZZ| <br />
     * |ZZZ| <br />
     * |===| <br />
     * </code>
     * </p>
     */
    FULL;
}
