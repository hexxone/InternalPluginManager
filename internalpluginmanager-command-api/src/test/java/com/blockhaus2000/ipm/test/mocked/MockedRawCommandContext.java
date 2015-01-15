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
 */
package com.blockhaus2000.ipm.test.mocked;

import com.blockhaus2000.ipm.technical.command.CommandSender;
import com.blockhaus2000.ipm.technical.command.util.RawCommandContext;

/**
 * A mock implementation of the {@link RawCommandContext}.
 *
 */
public class MockedRawCommandContext extends MockedCommandInfo implements RawCommandContext {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.RawCommandContext#getLabel()
     */
    @Override
    public String getLabel() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.RawCommandContext#getRawArgs()
     */
    @Override
    public String[] getRawArgs() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.RawCommandContext#getSender()
     */
    @Override
    public CommandSender getSender() {
        return null;
    }
}
