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
package com.blockhaus2000.ipm.minecraft.block.meta;

import com.blockhaus2000.ipm.minecraft.SkullType;

/**
 * The {@link BlockMeta} of a mob head.
 *
 */
public interface MobHeadBlockMeta extends BlockMeta {
    /**
     *
     * @return The {@link SkullType} of this mob head.
     */
    SkullType getSkullType();

    /**
     *
     * @return The player name, if this is from type {@link SkullType#PLAYER}
     *         and is set to another player. If not, this returns
     *         <code>null</code>.
     */
    String getPlayerName();
}
