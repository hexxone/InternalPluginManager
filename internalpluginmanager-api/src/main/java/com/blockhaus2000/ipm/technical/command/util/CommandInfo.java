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

import java.lang.reflect.Method;
import java.util.Map;

import com.blockhaus2000.ipm.technical.command.Command;

/**
 * A {@link CommandInfo} contains the main information about a command.
 *
 */
public interface CommandInfo extends Comparable<CommandInfo> {
    /**
     *
     * @return The {@link Command} annotation that indicates the command.
     */
    public Command getCommandAnot();

    /**
     *
     * @return The {@link Class} that contains the command method.
     */
    public Class<?> getClazz();

    /**
     *
     * @return The {@link Object} of the class that contains the command method.
     *         May be <code>null</code>, if no object where given (The method
     *         has to be static!).
     */
    public Object getObject();

    /**
     *
     * @return The {@link Method} that is tagged with the {@link Command}
     *         annotation.
     */
    public Method getMethod();

    /**
     *
     * @return The flag data, containing all information about flags that are
     *         important to parse them whilest a execution.
     */
    public Map<Character, SyntaxType> getFlagData();
}
