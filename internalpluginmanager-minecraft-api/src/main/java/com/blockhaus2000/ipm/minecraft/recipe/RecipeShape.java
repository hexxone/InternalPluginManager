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
package com.blockhaus2000.ipm.minecraft.recipe;

import com.blockhaus2000.ipm.minecraft.ItemStack;

public interface RecipeShape {
    ItemStack getAt(final RecipeShape.Location loc);

    public static enum Location {
        TOP_LEFT(0),
        TOP_MIDDLE(1),
        TOP_RIGHT(2),

        MIDDLE_LEFT(3),
        MIDDLE_MIDDLE(4),
        MIDDLE_RIGHT(5),

        BOTTOM_LEFT(6),
        BOTTOM_MIDDLE(7),
        BOTTOM_RIGHT(8);

        private final int index;

        private Location(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }
}
