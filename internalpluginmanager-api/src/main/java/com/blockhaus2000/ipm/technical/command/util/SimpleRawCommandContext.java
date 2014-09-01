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

import java.util.Arrays;

import com.blockhaus2000.ipm.technical.command.CommandSender;

/**
 * Add implementation of {@link RawCommandContext}.
 *
 */
public class SimpleRawCommandContext extends SimpleCommandInfo implements RawCommandContext {
    /**
     * The command label.
     *
     */
    private final String label;
    /**
     * The command arguments (non-parsed).
     *
     */
    private final String[] rawArgs;
    /**
     * The command sender (type).
     *
     */
    private final CommandSender sender;

    /**
     * Constructor of SimpleRawCommandContext.
     *
     * @param commandInfo
     *            Will be used in <code>super</code> call.
     * @param label
     *            The command label.
     * @param rawArgs
     *            The command arguments (non-parsed).
     * @param sender
     *            The command sender (type).
     */
    public SimpleRawCommandContext(final CommandInfo commandInfo, final String label, final String[] rawArgs,
            final CommandSender sender) {
        super(commandInfo.getCommandAnot(), commandInfo.getClazz(), commandInfo.getObject(), commandInfo.getMethod(), commandInfo
                .getFlagData());

        assert label != null && !label.isEmpty() : "Label cannot be null or empty!";
        assert rawArgs != null : "RawArgs cannot be null!";
        assert sender != null : "Sender cannot be null!";

        this.label = label;
        this.rawArgs = rawArgs;
        this.sender = sender;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.RawCommandContext#getLabel()
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.RawCommandContext#getRawArgs()
     */
    @Override
    public String[] getRawArgs() {
        return rawArgs;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.RawCommandContext#getSender()
     */
    @Override
    public CommandSender getSender() {
        return sender;
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
        result = prime * result + (label == null ? 0 : label.hashCode());
        result = prime * result + Arrays.hashCode(rawArgs);
        result = prime * result + (sender == null ? 0 : sender.hashCode());
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
        if (!(obj instanceof SimpleRawCommandContext)) {
            return false;
        }
        final SimpleRawCommandContext other = (SimpleRawCommandContext) obj;
        if (label == null) {
            if (other.label != null) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        if (!Arrays.equals(rawArgs, other.rawArgs)) {
            return false;
        }
        if (sender == null) {
            if (other.sender != null) {
                return false;
            }
        } else if (!sender.equals(other.sender)) {
            return false;
        }
        return true;
    }
}
