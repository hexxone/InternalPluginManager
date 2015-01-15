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
package com.blockhaus2000.ipm.minecraft.material;

/**
 * Represents a material (a material is a block, an item, etc.).
 *
 */
public interface Material {
    /**
     *
     * @return The name of this material.
     */
    String getMaterialName();

    /**
     *
     * @deprecated Data values should not be used longer.
     * @return The ID of this material.
     */
    @Deprecated
    int getMaterialId();

    /**
     *
     * @deprecated Data values should not be used longer.
     * @return The data value of this material.
     */
    @Deprecated
    byte getMaterialData();
}
