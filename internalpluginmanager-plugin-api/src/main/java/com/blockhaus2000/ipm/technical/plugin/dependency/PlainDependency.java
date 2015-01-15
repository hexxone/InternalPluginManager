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
package com.blockhaus2000.ipm.technical.plugin.dependency;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of {@link Dependency} that does not provide any dependency
 * names. This is used as the start "parent" dependency.
 *
 */
public class PlainDependency implements Dependency {
    /**
     * Constructor of PlainDependency.
     *
     */
    public PlainDependency() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.dependency.Dependency#getDependencyNames()
     */
    @Override
    public Set<String> getDependencyNames() {
        return new HashSet<String>();
    }
}
