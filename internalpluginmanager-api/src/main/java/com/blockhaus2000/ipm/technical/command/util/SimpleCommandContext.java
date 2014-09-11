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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final List<Tag<?>> args;
    /**
     * The command flags.
     *
     */
    private final Map<Character, Tag<?>> flags;

    /**
     * Constructor of SimpleCommandContext.
     *
     * @param rawCommandContext
     *            Will be used in <code>super</code> call.
     * @param args
     *            The command arguments (parsed).
     * @param flags
     *            The command flags.
     */
    public SimpleCommandContext(final RawCommandContext rawCommandContext, final List<Tag<?>> args,
            final Map<Character, Tag<?>> flags) {
        super(rawCommandContext, rawCommandContext.getLabel(), rawCommandContext.getRawArgs(), rawCommandContext.getSender());

        assert args != null : "Args cannot be null!";
        assert flags != null : "Flags cannot be null!";

        this.args = args;
        this.flags = flags;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandContext#getArgs()
     */
    @Override
    public List<Tag<?>> getArgs() {
        return this.args;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandContext#getFlags()
     */
    @Override
    public Map<Character, Tag<?>> getFlags() {
        return new HashMap<Character, Tag<?>>(this.flags);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.args == null ? 0 : this.args.hashCode());
        result = prime * result + (this.flags == null ? 0 : this.flags.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof SimpleCommandContext)) {
            return false;
        }
        final SimpleCommandContext other = (SimpleCommandContext) obj;
        if (this.args == null) {
            if (other.args != null) {
                return false;
            }
        } else if (!this.args.equals(other.args)) {
            return false;
        }
        if (this.flags == null) {
            if (other.flags != null) {
                return false;
            }
        } else if (!this.flags.equals(other.flags)) {
            return false;
        }
        return true;
    }
}
