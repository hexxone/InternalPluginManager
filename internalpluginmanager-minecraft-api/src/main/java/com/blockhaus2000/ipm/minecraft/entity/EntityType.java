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
 * All available entities.
 *
 */
public enum EntityType {
    // TODO: Add entities.
    ;

    /**
     * The entity name.
     *
     */
    private final String entityName;
    /**
     * The entity class.
     *
     */
    private final Class<? extends Entity> entityClass;

    /**
     * Constructor of EntityType.
     *
     * @param entityName
     *            The entity name.
     * @param entityClass
     *            The entity class.
     */
    private EntityType(final String entityName, final Class<? extends Entity> entityClass) {
        this.entityName = entityName;
        this.entityClass = entityClass;
    }

    /**
     * Searchs for the entity with the give name.
     *
     * @param entityName
     *            The name to search for.
     * @return The entityName associated with the given name.
     */
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
