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

import java.util.List;

import com.blockhaus2000.ipm.util.Tag;

/**
 * An implementation of {@link CommandContext}.
 *
 */
public class SimpleCommandContext extends SimpleRawCommandContext implements CommandContext {
    /**
     * The command arguments (parsed).
     *
     */
    final List<Tag<?>> args;

    /**
     * Constructor of SimpleCommandContext.
     *
     * @param rawCommandContext
     *            Will be used in <code>super</code> call.
     * @param args
     *            The command arguments (parsed).
     */
    public SimpleCommandContext(final RawCommandContext rawCommandContext, final List<Tag<?>> args) {
        super(rawCommandContext, rawCommandContext.getLabel(), rawCommandContext.getRawArgs(), rawCommandContext.getSender());
        this.args = args;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandContext#getArgs()
     */
    @Override
    public List<Tag<?>> getArgs() {
        return args;
    }
}
