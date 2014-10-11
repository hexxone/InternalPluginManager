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

import java.util.HashMap;
import java.util.Map;

import com.blockhaus2000.ipm.base.CommonStringConstants;

/**
 * The default implementation of the {@link StringFormatter}.
 *
 */
public class SimpleStringFormatter implements StringFormatter {
    /**
     *
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.format.StringFormatter#format(java.lang.String,
     *      com.blockhaus2000.ipm.technical.format.StringFormatMapping[])
     */
    @Override
    public String format(final String str, final StringFormatMapping... mappings) {
        final Map<String, String> maps = new HashMap<String, String>();
        for (final StringFormatMapping mapping : mappings) {
            maps.put(mapping.getKey().getStringKey(), mapping.getFormatted());
        }

        final StringBuilder builder = new StringBuilder();
        for (final String key : str.split(CommonStringConstants.COLON)) {
            final String value = maps.get(key);
            if (value == null) {
                builder.append(key);
                builder.append(CommonStringConstants.COLON);
            } else {
                if (builder.charAt(builder.length() - 1) == CommonStringConstants.COLON.charAt(0)) {
                    builder.setLength(builder.length() - 1);
                }
                builder.append(value);
            }
        }
        if (!str.endsWith(":")) {
            builder.setLength(builder.length() - 1);
        }

        return builder.toString();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.format.StringFormatter#format(java.lang.String,
     *      com.blockhaus2000.ipm.technical.format.StringFormatMappable[])
     */
    @Override
    public String format(final String str, final StringFormatMappable... mappables) {
        final StringFormatMapping[] mappings = new StringFormatMapping[mappables.length];
        for (int i = 0; i < mappables.length; i++) {
            mappings[i] = mappables[i].getStringFormatMapping();
        }
        return this.format(str, mappings);
    }
}
