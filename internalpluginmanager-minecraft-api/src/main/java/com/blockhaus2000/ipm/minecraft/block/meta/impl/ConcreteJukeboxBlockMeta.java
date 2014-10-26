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
import com.blockhaus2000.ipm.minecraft.block.meta.JukeboxBlockMeta;
import com.blockhaus2000.ipm.minecraft.record.RecordMaterial;

public class ConcreteJukeboxBlockMeta extends ConcreteBlockMeta implements JukeboxBlockMeta {
    private final boolean playing;
    private final RecordMaterial record;

    public ConcreteJukeboxBlockMeta(final BlockMeta blockMeta, final boolean playing, final RecordMaterial record) {
        super(blockMeta);

        this.playing = playing;
        this.record = record;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.JukeboxBlockMeta#isPlaying()
     */
    @Override
    public boolean isPlaying() {
        return this.playing;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.JukeboxBlockMeta#getRecord()
     */
    @Override
    public RecordMaterial getRecord() {
        return this.record;
    }
}
