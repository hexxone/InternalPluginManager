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
package com.blockhaus2000.ipm.technical.command.util;

/**
 * This class contains some constants like regular exrepssions.
 *
 */
public class Constants {
    /**
     * The regular expression that is used to parse flags.
     *
     */
    public static final String FLAG_REGEX = "[a-zA-Z](:(string|long|double|string_vararg|long_vararg|double_vararg|))?";

    /**
     * Constructor of Constants.
     *
     */
    private Constants() {
        // Utility classes should not have a visible constructor.
    }
}
