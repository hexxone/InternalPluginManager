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
package com.blockhaus2000.ipm.technical.format;

import java.util.Formatter;

/**
 * The {@link StringFormatter} is a tool to easily "format" Strings with custom
 * mappings for String-variables (like <code>%s</code> for a String for the
 * {@link Formatter}).
 *
 */
public interface StringFormatter {
    /**
     * Formats the given String with the given string mappings.
     *
     * @param str
     *            The String to format.
     * @param mappings
     *            The {@link StringFormatMapping}s to use for the given String.
     * @return The formatted String.
     */
    String format(final String str, final StringFormatMapping... mappings);

    /**
     * Gets all format mappings from the given format mappables and delegates to
     * {@link StringFormatter#format(String, StringFormatMapping...)}.
     *
     * @param str
     *            The String to format.
     * @param mappables
     *            The {@link StringFormatMappable}s storing the
     *            {@link StringFormatMapping}s to use for the given String.
     * @return The formatted String.
     */
    String format(final String str, final StringFormatMappable... mappables);
}
