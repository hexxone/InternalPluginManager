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

import com.blockhaus2000.ipm.minecraft.command.CommandSender;
import com.blockhaus2000.ipm.technical.format.StringFormatKeyPrefix;
import com.blockhaus2000.ipm.technical.format.StringFormatMappable;
import com.blockhaus2000.ipm.technical.format.StringFormatMapping;
import com.blockhaus2000.ipm.technical.format.StringFormatMappingKey;
import com.blockhaus2000.ipm.technical.format.StringFormatter;

/**
 * The {@link MessageManager} for this server that manages broadcasting and
 * message formatting.
 *
 */
public interface MessageManager {
    /**
     * Formats the given message with the {@link StringFormatter} using
     * {@link ChatColor} as format mapping and send the given message to all
     * online players that have the given permission.
     *
     * @param message
     *            The message to format and broadcast.
     * @param permission
     *            The permission that the receivers should have.
     */
    void broadcast(final String message, final String permission);

    /**
     * Delegates to {@link MessageManager#broadcast(String, String)} with
     * <code>message = message</code> and <code>permission = null</code>.
     *
     * @param message
     *            Is passed into
     *            {@link MessageManager#broadcast(String, String)}
     * @see com.blockhaus2000.ipm.minecraft.MessageManager#broadcast(java.lang.String,
     *      java.lang.String)
     */
    void broadcast(final String message);

    /**
     * Formats the given message with the {@link StringFormatter} using
     * {@link ChatColor} as format mapping and sends the formatted string to the
     * given receiver.
     *
     * @param receiver
     *            The {@link CommandSender} that has to receive the given
     *            message.
     * @param message
     *            The message to format and send to the given receiver.
     */
    void sendMessage(final CommandSender receiver, final String message);

    /**
     *
     * @return The {@link StringFormatter} that is to fromat strings when
     *         sending messages.
     */
    StringFormatter getFormatter();

    /**
     * Al chat colors that can be used within a message.
     *
     */
    public static enum ChatColor implements StringFormatMappable {
        /**
         * Black. Color code: <code>$0</code>.
         *
         */
        BLACK('0'),
        /**
         * Dark blue. Color code: <code>$1</code>.
         *
         */
        DARK_BLUE('1'),
        /**
         * Dark green. Color code: <code>$2</code>.
         *
         */
        DARK_GREEN('2'),
        /**
         * Dark aqua. Color code: <code>$3</code>.
         *
         */
        DARK_AQUA('3'),
        /**
         * Darg red. Color code: <code>$4</code>.
         *
         */
        DARK_RED('4'),
        /**
         * Dark purple. Color code: <code>$5</code>.
         *
         */
        DARK_PURPLE('5'),
        /**
         * Gold. Color code: <code>$6</code>.
         *
         */
        GOLD('6'),
        /**
         * Gray. Color code: <code>$7</code>.
         *
         */
        GRAY('7'),
        /**
         * Dark gray. Color code: <code>$8</code>.
         *
         */
        DARK_GRAY('8'),
        /**
         * Blue. Color code: <code>$9</code>.
         *
         */
        BLUE('9'),
        /**
         * Green. Color code: <code>$a</code>.
         *
         */
        GREEN('a'),
        /**
         * Aqua. Color code: <code>$b</code>.
         *
         */
        AQUA('b'),
        /**
         * Red. Color code: <code>$c</code>.
         *
         */
        RED('c'),
        /**
         * Light purple. Color code: <code>$d</code>.
         *
         */
        LIGHT_PURPLE('d'),
        /**
         * Yellow. Color code: <code>$e</code>.
         *
         */
        YELLOW('e'),
        /**
         * White. Color code: <code>$f</code>.
         *
         */
        WHITE('f');

        /**
         * The color indicator char.
         *
         */
        private final char colorIndicator;

        /**
         * Constructor of ChatColor.
         *
         * @param colorIndicator
         *            The color indicator char.
         */
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
