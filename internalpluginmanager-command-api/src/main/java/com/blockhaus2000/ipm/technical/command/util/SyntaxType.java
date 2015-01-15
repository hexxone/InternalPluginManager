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
package com.blockhaus2000.ipm.technical.command.util;

import com.blockhaus2000.ipm.base.Tag;

/**
 * All these syntax types can be used to indicate the type of a flag, or (as the
 * name says) of a syntax element.
 *
 */
public enum SyntaxType {
    /**
     * This is used mostly, and it is the safest, because no parsing has to be
     * processed. Will be passed into a {@link Tag} with the type {@link String}
     *
     * @see java.lang.String
     */
    STRING,
    /**
     * Use this for numbers without decimals (like <code>1</code>,
     * <code>2</code>, <code>3</code>, etc.). Will be passed into a {@link Tag}
     * with the type {@link Long}. A {@link Long} is used because maybe you want
     * to get higher numbers than {@link Integer#MAX_VALUE}.
     *
     * @see java.lang.Long
     */
    LONG,
    /**
     * Use this for number with decimals (like <code>12.34</code>,
     * <code>56.78</code>, etc.). Will be passed into a {@link Tag} with the
     * type {@link Double}.
     *
     * @see java.lang.Double
     */
    DOUBLE,
    /**
     * Use this to get a joined string, like <code>"Hello World!"</code>, which
     * is present in two different arguments. Will be passed into a {@link Tag}
     * with the type {@link String}.
     *
     * @see java.lang.String
     */
    STRING_VARARG;

    /**
     *
     * @return A name that can be parsed by
     *         {@link SyntaxType#getFromName(String)}.
     */
    public String getUseableName() {
        return this.toString().toLowerCase();
    }

    /**
     *
     * @param name
     *            The name of the {@link SyntaxType} to get.
     * @return The {@link SyntaxType} that is associated with the given
     *         <code>name</code>. Returns <code>null</code>, if nothing was
     *         found.
     */
    public static SyntaxType getFromName(final String name) {
        for (final SyntaxType syntaxType : SyntaxType.values()) {
            if (syntaxType.getUseableName().equalsIgnoreCase(name)) {
                return syntaxType;
            }
        }
        return null;
    }
}
