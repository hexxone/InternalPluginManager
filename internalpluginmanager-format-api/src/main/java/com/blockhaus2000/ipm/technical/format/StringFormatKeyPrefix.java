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

/**
 * The {@link StringFormatKeyPrefix} is an enum of valid prefixes that can be
 * used to create a complete {@link StringFormatMappingKey}.
 *
 */
public enum StringFormatKeyPrefix {
    /**
     * <code>#</code>
     *
     */
    NUMBER_SIGN('#'),
    /**
     * <code>$</code>
     *
     */
    DOLLAR_SIGN('$'),
    /**
     * <code>%</code>
     *
     */
    PERCENT_SIGN('%'),
    /**
     * <code>&</code>
     *
     */
    AMPERSAND('&'),
    /**
     * <code>@</code>
     *
     */
    COMMERCIAL_AT('@');

    /**
     * The prefix char.
     *
     */
    private final char prefix;

    /**
     * Constructor of StringFormatKeyPrefix.
     *
     * @param prefix
     *            The prefix char.
     */
    private StringFormatKeyPrefix(final char prefix) {
        this.prefix = prefix;
    }

    /**
     *
     * @return {@link StringFormatKeyPrefix#prefix}
     */
    public char getPrefix() {
        return this.prefix;
    }
}
