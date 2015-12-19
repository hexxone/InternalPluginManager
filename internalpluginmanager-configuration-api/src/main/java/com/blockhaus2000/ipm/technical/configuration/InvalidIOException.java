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
package com.blockhaus2000.ipm.technical.configuration;

import java.io.IOException;

/**
 * The {@link InvalidIOException} is just a mock exception for the usage in the
 * constructor the the {@link HrTssConfiguration}. Please read there for a
 * detailed explanation.
 *
 * <p>
 * <b> NOTE: Do NOT throw this exception! </b>
 * </p>
 *
 */
public class InvalidIOException extends IOException {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 5562544791972343141L;
}
