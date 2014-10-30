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
package com.blockhaus2000.ipm.minecraft.entity;

public enum EntityType {
    // TODO: Add entities.
    ;

    private final String entityName;

    private final Class<? extends Entity> entityClass;

    private EntityType(final String entityName, final Class<? extends Entity> entityClass) {
        this.entityName = entityName;
        this.entityClass = entityClass;
    }

    public static EntityType getByName(final String entityName) {
        for (final EntityType entityType : EntityType.values()) {
            if (entityType.getEntityName().equals(entityName)) {
                return entityType;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link EntityType#entityName}.
     */
    public String getEntityName() {
        return this.entityName;
    }

    /**
     *
     * @return {@link EntityType#entityClass}.
     */
    public Class<? extends Entity> getEntityClass() {
        return this.entityClass;
    }
}
