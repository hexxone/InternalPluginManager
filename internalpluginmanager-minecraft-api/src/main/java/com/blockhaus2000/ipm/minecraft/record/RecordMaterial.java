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
package com.blockhaus2000.ipm.minecraft.record;

import com.blockhaus2000.ipm.minecraft.material.Material;

public enum RecordMaterial implements Material {
    // TODO: Add records.
    ;

    private final String materialName;

    private final int materialId;
    private final byte materialData;

    private RecordMaterial(final String materialName, final int materialId, final byte materialData) {
        this.materialName = materialName;
        this.materialId = materialId;
        this.materialData = materialData;
    }

    private RecordMaterial(final String materialName, final int materialId, final int materialData) {
        this(materialName, materialId, (byte) materialData);
    }

    private RecordMaterial(final String materialName, final int materialId) {
        this(materialName, materialId, -1);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialName()
     */
    @Override
    public String getMaterialName() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialId()
     */
    @Deprecated
    @Override
    public int getMaterialId() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialData()
     */
    @Deprecated
    @Override
    public byte getMaterialData() {
        // TODO Auto-generated method body.
        return 0;
    }
}
