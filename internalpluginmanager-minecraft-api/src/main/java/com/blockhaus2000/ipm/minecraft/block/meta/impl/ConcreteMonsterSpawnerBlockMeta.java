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
package com.blockhaus2000.ipm.minecraft.block.meta.impl;

import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.MonsterSpawnerBlockMeta;
import com.blockhaus2000.ipm.minecraft.enity.EntityType;

/**
 * Concrete {@link MonsterSpawnerBlockMeta} implementation.
 *
 */
public class ConcreteMonsterSpawnerBlockMeta extends ConcreteBlockMeta implements MonsterSpawnerBlockMeta {
    /**
     * <code>entity</code>
     *
     */
    private final EntityType entity;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param entity
     *            <code>entity</code>
     */
    public ConcreteMonsterSpawnerBlockMeta(final BlockMeta blockMeta, final EntityType entity) {
        super(blockMeta);

        this.entity = entity;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.MonsterSpawnerBlockMeta#getEntity()
     */
    @Override
    public EntityType getEntity() {
        return this.entity;
    }
}
