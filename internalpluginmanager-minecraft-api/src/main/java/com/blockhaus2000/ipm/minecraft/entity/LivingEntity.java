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
package com.blockhaus2000.ipm.minecraft.entity;

/**
 * An enity that lives.
 *
 */
public interface LivingEntity {
    /**
     *
     * @return The total lives of this entity. One life is a half heart.
     */
    int getTotalLife();

    /**
     *
     * @return All lifes that this entity have. One life is a half heart.
     */
    int getLifes();

    /**
     * Damages this enity.
     *
     * @param damage
     *            The lifes to subtract from their lifes.
     */
    void hit(final int damage);
}
