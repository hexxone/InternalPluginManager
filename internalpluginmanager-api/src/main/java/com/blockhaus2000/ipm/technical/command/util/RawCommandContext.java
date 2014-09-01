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

import com.blockhaus2000.ipm.technical.command.CommandSender;

/**
 * A {@link RawCommandContext} contains more information about a command as
 * {@link CommandInfo}, like the raw arguments.
 *
 */
public interface RawCommandContext extends CommandInfo {
    /**
     *
     * @return The command alias that the command was executed with.
     */
    public String getLabel();

    /**
     *
     * @return The unparsed arguments.
     */
    public String[] getRawArgs();

    /**
     *
     * @return The {@link CommandSender} that has executed the command.
     */
    public CommandSender getSender();
}
