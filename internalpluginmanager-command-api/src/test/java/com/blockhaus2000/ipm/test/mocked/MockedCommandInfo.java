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
package com.blockhaus2000.ipm.test.mocked;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.blockhaus2000.ipm.technical.command.Command;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.command.util.SyntaxType;

/**
 * A mock implementation of a {@link CommandInfo}.
 *
 */
public class MockedCommandInfo implements CommandInfo {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getCommandAnot()
     */
    @Override
    public Command getCommandAnot() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getObject()
     */
    @Override
    public Object getObject() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getMethod()
     */
    @Override
    public Method getMethod() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getFlagData()
     */
    @Override
    public Map<Character, SyntaxType> getFlagData() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getSyntaxData()
     */
    @Override
    public List<SyntaxType> getSyntaxData() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final CommandInfo o) {
        return 0;
    }
}
