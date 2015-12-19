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
package com.blockhaus2000.ipm.minecraft.record;

import com.blockhaus2000.ipm.minecraft.material.Material;

/**
 * All records that can be played within a jukebox.
 *
 */
public enum RecordMaterial implements Material {
    // TODO: Add records.
    ;

    /**
     * The material name of this record.
     *
     */
    private final String materialName;

    /**
     * The material ID of this record.
     *
     */
    private final int materialId;

    /**
     * Constructor of RecordMaterial.
     *
     * @param materialName
     *            The record name.
     * @param materialId
     *            The record ID.
     */
    private RecordMaterial(final String materialName, final int materialId) {
        this.materialName = materialName;
        this.materialId = materialId;
    }

    /**
     * Searchs for the record with the give name.
     *
     * @param name
     *            The name to search for.
     * @return The record associated with the given name.
     */
    public static RecordMaterial getByName(final String name) {
        for (final RecordMaterial recordMaterial : RecordMaterial.values()) {
            if (recordMaterial.getMaterialName().equalsIgnoreCase(name)) {
                return recordMaterial;
            }
        }
        return null;
    }

    /**
     * Searchs for the record with the give ID.
     *
     * @deprecated Data values should not be used longer.
     * @param id
     *            The ID to search for.
     * @return The record associated with the given ID.
     */
    @Deprecated
    public static RecordMaterial getById(final int id) {
        for (final RecordMaterial recordMaterial : RecordMaterial.values()) {
            if (recordMaterial.getMaterialId() == id) {
                return recordMaterial;
            }
        }
        return null;
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
     * @deprecated Data values should not be used longer.
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
     * @deprecated Data values should not be used longer.
     * @see com.blockhaus2000.ipm.minecraft.material.Material#getMaterialData()
     */
    @Deprecated
    @Override
    public byte getMaterialData() {
        return -1;
    }
}
