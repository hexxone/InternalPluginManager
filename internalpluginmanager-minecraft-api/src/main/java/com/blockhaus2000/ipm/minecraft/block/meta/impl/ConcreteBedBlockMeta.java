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

import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.block.meta.BedBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.entity.Player;

/**
 * Concrete {@link BedBlockMeta} implementation.
 *
 */
public class ConcreteBedBlockMeta extends ConcreteBlockMeta implements BedBlockMeta {
    /**
     * <code>player</code>
     *
     */
    private final UUID player;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param player
     *            <code>player</code>
     */
    public ConcreteBedBlockMeta(final BlockMeta blockMeta, final Player player) {
        super(blockMeta);

        this.player = player.getUUID();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BedBlockMeta#getPlayer()
     */
    @Override
    public Player getPlayer() {
        return InternalPluginManager.getServer().getPlayerManager().getPlayer(this.player);
    }
}
