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
package com.blockhaus2000.ipm.technical.plugin.dependency;

import java.util.Set;

/**
 * This is a class to dynamicly create a dependency node with a dynamic name.
 *
 */
public class DynamicDependency implements Dependency {
    /**
     * The "parent" dependency. This is used to create a complete list of
     * dependencies.
     *
     */
    private final Dependency parentDependency;
    /**
     * The dependency name.
     *
     */
    private final String dependencyName;

    /**
     * Constructor of DynamicDependency.
     *
     * @param parentDependency
     *            The "parent" dependency.
     * @param dependencyName
     *            The name of this dependency.
     * @see com.blockhaus2000.ipm.technical.plugin.dependency.DynamicDependency#parentDependency
     */
    public DynamicDependency(final Dependency parentDependency, final String dependencyName) {
        this.parentDependency = parentDependency;
        this.dependencyName = dependencyName;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.dependency.Dependency#getDependencyNames()
     */
    @Override
    public Set<String> getDependencyNames() {
        final Set<String> parentDependencyNames = this.parentDependency.getDependencyNames();
        parentDependencyNames.add(this.dependencyName);
        return parentDependencyNames;
    }
}
