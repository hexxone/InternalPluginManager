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
package com.blockhaus2000.ipm.api.dummy;

/**
 * Each project has to have at least one class, because the JaCoCo plugin of the
 * Jenkins CI server searchs for classes in the folder
 * <code>target/classes/</code>. If no class exists, the folder is empty and an
 * exception is thrown during the publishing of the JaCoCo test coverage
 * results.
 *
 */
public class DummyClass {
    // Nothing to do here. Please read Javadoc for more information.
}
