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

import java.io.Console;

import com.blockhaus2000.ipm.technical.command.CommandSender;

/**
 * A mock implementation of the {@link Console} interface.
 *
 */
public class MockedCommandSender implements CommandSender {
    /**
     * The test permission that this {@link CommandSender} has.
     *
     */
    public static final String JUNIT_TEST_PERIMSSION = "junit.test.permission";

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String permission) {
        return permission == null || permission.equals("") || permission.equals(MockedCommandSender.JUNIT_TEST_PERIMSSION);
    }
}
