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
package com.blockhaus2000.ipm.minecraft;

import com.blockhaus2000.ipm.technical.format.StringFormatKeyPrefix;
import com.blockhaus2000.ipm.technical.format.StringFormatMappable;
import com.blockhaus2000.ipm.technical.format.StringFormatMapping;
import com.blockhaus2000.ipm.technical.format.StringFormatMappingKey;
import com.blockhaus2000.ipm.technical.format.StringFormatter;

public interface MessageManager {
    void broadcast(final String message, final String permission);

    void broadcast(final String message);

    StringFormatter getFormatter();

    public static enum ChatColor implements StringFormatMappable {
        BLACK('0'),
        DARK_BLUE('1'),
        DARK_GREEN('2'),
        DARK_AQUA('3'),
        DARK_RED('4'),
        DARK_PURPLE('5'),
        GOLD('6'),
        GRAY('7'),
        DARK_GRAY('8'),
        BLUE('9'),
        GREEN('a'),
        AQUA('b'),
        RED('c'),
        LIGHT_PURPLE('d'),
        YELLOW('e'),
        WHITE('f');

        private final char colorIndicator;

        private ChatColor(final char colorIndicator) {
            this.colorIndicator = colorIndicator;
        }

        /**
         * {@inheritDoc}
         *
         * @see com.blockhaus2000.ipm.technical.format.StringFormatMappable#getStringFormatMapping()
         */
        @Override
        public StringFormatMapping getStringFormatMapping() {
            return new StringFormatMapping(new StringFormatMappingKey(StringFormatKeyPrefix.DOLLAR_SIGN, this.colorIndicator),
                    "\u00A7" + this.colorIndicator);
        }
    }
}
