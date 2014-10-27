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
package com.blockhaus2000.ipm.minecraft.item;

import com.blockhaus2000.ipm.minecraft.material.Material;

/**
 * All materials that are items.
 *
 */
public enum ItemMaterial implements Material {
    // TODO: Add items.
    ;

    /**
     * The name of this item.
     *
     */
    private final String materialName;

    /**
     * The ID of this item.
     *
     */
    private final int materialId;
    /**
     * The data value of this item.
     *
     */
    private final byte materialData;

    /**
     * Constructor of ItemMaterial.
     *
     * @param materialName
     *            The name of this item.
     * @param materialId
     *            The ID of this item.
     * @param materialData
     *            The data value of this item.
     */
    private ItemMaterial(final String materialName, final int materialId, final byte materialData) {
        this.materialName = materialName;
        this.materialId = materialId;
        this.materialData = materialData;
    }

    /**
     * Constructor of ItemMaterial.
     *
     * @param materialName
     *            The name of this item.
     * @param materialId
     *            The ID of this item.
     * @param materialData
     *            The data value of this item (will be casted to a
     *            <code>byte</code>).
     */
    private ItemMaterial(final String materialName, final int materialId, final int materialData) {
        this(materialName, materialId, (byte) materialData);
    }

    /**
     * Constructor of ItemMaterial.
     *
     * @param materialName
     *            The name of this item.
     * @param materialId
     *            The ID of this item.
     */
    private ItemMaterial(final String materialName, final int materialId) {
        this(materialName, materialId, -1);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialName()
     */
    @Override
    public String getMaterialName() {
        return this.materialName;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialId()
     */
    @Deprecated
    @Override
    public int getMaterialId() {
        return this.materialId;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialData()
     */
    @Deprecated
    @Override
    public byte getMaterialData() {
        return this.materialData;
    }
}
