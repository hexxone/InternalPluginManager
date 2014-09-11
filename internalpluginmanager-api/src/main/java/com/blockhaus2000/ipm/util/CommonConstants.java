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
package com.blockhaus2000.ipm.util;

import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * This class contains some common constants.
 *
 */
public final class CommonConstants {
    /**
     * The name of the InternalPluginManager system logger.
     *
     * <p>
     * <b> NOTE: Do NOT use this logger within plugins. Use the plugin logger (
     * {@link Plugin#getLogger()}) instead. </b>
     * </p>
     */
    public static final String INTERNALPLUGINMANAGER_SYSTEM_LOGGER_NAME = CommonConstants.class.getPackage().getName()
            + ".LOGGER";

    /**
     * Constructor of CommonConstants.
     *
     */
    private CommonConstants() {
        // Utility classes should not have a visible constructor.
    }
}
