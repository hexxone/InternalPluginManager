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
package com.blockhaus2000.ipm.technical.command.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blockhaus2000.ipm.technical.command.Command;

/**
 * An implementation of {@link CommandInfo}.
 *
 */
// TODO: Remove Class storing.
public class SimpleCommandInfo implements CommandInfo {
    /**
     * The {@link Command} annotation.
     *
     */
    private final Command commandAnot;
    /**
     * An {@link Object} of the class where the command execution method is
     * located.
     *
     */
    private final Object object;
    /**
     * The command execution {@link Method}.
     *
     */
    private final Method method;
    /**
     * The command flag data.
     *
     */
    private final Map<Character, SyntaxType> flagData;
    /**
     * The command syntax data
     *
     */
    private final List<SyntaxType> syntaxData;

    /**
     * Constructor of SimpleCommandInfo.
     *
     * @param commandAnot
     *            The {@link Command} annotation.
     * @param object
     *            An {@link Object} of the class where the command execution
     *            method is located.
     * @param method
     *            The command executin {@link Method}.
     * @param flagData
     *            The command flag data.
     * @param syntaxData
     *            The command syntax data.
     */
    public SimpleCommandInfo(final Command commandAnot, final Object object, final Method method,
            final Map<Character, SyntaxType> flagData, final List<SyntaxType> syntaxData) {
        assert commandAnot != null : "CommandAnot cannot be null!";
        assert method != null : "Method cannot be null!";
        assert flagData != null : "FlagData cannot be null!";

        this.commandAnot = commandAnot;
        this.object = object;
        this.method = method;
        this.flagData = flagData;
        this.syntaxData = syntaxData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getCommandAnot()
     */
    @Override
    public Command getCommandAnot() {
        return this.commandAnot;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getObject()
     */
    @Override
    public Object getObject() {
        return this.object;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getMethod()
     */
    @Override
    public Method getMethod() {
        return this.method;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getFlagData()
     */
    @Override
    public Map<Character, SyntaxType> getFlagData() {
        return new HashMap<Character, SyntaxType>(this.flagData);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getSyntaxData()
     */
    @Override
    public List<SyntaxType> getSyntaxData() {
        return this.syntaxData == null ? null : new ArrayList<SyntaxType>(this.syntaxData);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.commandAnot == null ? 0 : this.commandAnot.hashCode());
        result = prime * result + (this.method == null ? 0 : this.method.hashCode());
        result = prime * result + (this.object == null ? 0 : this.object.hashCode());
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
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SimpleCommandInfo)) {
            return false;
        }
        final SimpleCommandInfo other = (SimpleCommandInfo) obj;
        if (this.commandAnot == null) {
            if (other.commandAnot != null) {
                return false;
            }
        } else if (!this.commandAnot.equals(other.commandAnot)) {
            return false;
        }
        if (this.method == null) {
            if (other.method != null) {
                return false;
            }
        } else if (!this.method.equals(other.method)) {
            return false;
        }
        if (this.object == null) {
            if (other.object != null) {
                return false;
            }
        } else if (!this.object.equals(other.object)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final CommandInfo that) {
        final int thisPriority = this.getCommandAnot().priority().getPriority();
        final int thatPriority = that.getCommandAnot().priority().getPriority();
        return thisPriority < thatPriority ? -1 : thisPriority == thatPriority ? 0 : 1;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[commandAnot=" + this.commandAnot + ", object=" + this.object + ", method="
                + this.method + ", flagData=" + this.flagData + "]";
    }
}
