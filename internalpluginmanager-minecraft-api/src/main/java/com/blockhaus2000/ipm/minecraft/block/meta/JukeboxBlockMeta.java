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
package com.blockhaus2000.ipm.minecraft.block.meta;

import com.blockhaus2000.ipm.minecraft.record.RecordMaterial;

/**
 * The {@link BlockMeta} of a jukebox.
 *
 */
public interface JukeboxBlockMeta extends BlockMeta {
    /**
     *
     * @return Whether this jukebox is playing or not.
     */
    boolean isPlaying();

    /**
     *
     * @return The record that is within this jukebox. If no record is in the
     *         jukebox, this returns <code>null</code>.
     */
    RecordMaterial getRecord();
}
