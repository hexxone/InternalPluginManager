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
package com.blockhaus2000.ipm.minecraft;

import com.blockhaus2000.ipm.minecraft.world.World;

/**
 * Represents a gamerule that can be assigned to a {@link World}.
 *
 * @param <T>
 *            The value type of the game rule.
 */
public interface GameRule<T> {
    /**
     *
     * @return The {@link GameRule.Name} of this {@link GameRule}.
     */
    GameRule.Name getName();

    /**
     *
     * @return The value of this {@link GameRule}.
     */
    T getValue();

    /**
     * A list of possible names for a {@link GameRule}.
     *
     */
    public static enum Name {
        // TODO Auto-generated enum body.
        ;

        /**
         * The name of this {@link GameRule.Name}.
         *
         */
        private final String name;

        /**
         * Constructor of Name.
         *
         * @param name
         *            The {@link GameRule} name.
         */
        private Name(final String name) {
            this.name = name;
        }

        /**
         * Searchs for the {@link GameRule.Name} with the given name.
         *
         * @param name
         *            The name to search for.
         * @return The {@link GameRule.Name} associtaed with the given name, if
         *         found. Otherwise <code>null</code>.
         */
        public static Name getByName(final String name) {
            for (final Name gameRuleName : Name.values()) {
                if (gameRuleName.getName().equalsIgnoreCase(name)) {
                    return gameRuleName;
                }
            }
            return null;
        }

        /**
         *
         * @return {@link GameRule.Name#name}
         */
        public String getName() {
            return this.name;
        }
    }
}
