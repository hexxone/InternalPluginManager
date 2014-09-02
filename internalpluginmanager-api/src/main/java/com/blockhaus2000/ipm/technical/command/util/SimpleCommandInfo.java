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
import java.util.HashMap;
import java.util.Map;

import com.blockhaus2000.ipm.technical.command.Command;

/**
 * An implementation of {@link CommandInfo}.
 *
 */
public class SimpleCommandInfo implements CommandInfo {
    /**
     * The {@link Command} annotation.
     *
     */
    private final Command commandAnot;
    /**
     * The {@link Class} where the command execution method is located.
     *
     */
    private final Class<?> clazz;
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
     * Constructor of SimpleCommandInfo.
     *
     * @param commandAnot
     *            The {@link Command} annotation.
     * @param clazz
     *            The {@link Class} where the command execution method is
     *            located.
     * @param object
     *            An {@link Object} of the class where the command execution
     *            method is located.
     * @param method
     *            The command executin {@link Method}.
     * @param flagData
     *            The command flag data.
     */
    public SimpleCommandInfo(final Command commandAnot, final Class<?> clazz, final Object object, final Method method,
            final Map<Character, SyntaxType> flagData) {
        assert commandAnot != null : "CommandAnot cannot be null!";
        assert clazz != null : "Clazz cannot be null!";
        assert object == null || clazz.equals(object.getClass()) : "Object has to be null or an object of clazz!";
        assert method != null : "Method cannot be null!";
        assert flagData != null : "FlagData cannot be null!";

        this.commandAnot = commandAnot;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
        this.flagData = flagData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getCommandAnot()
     */
    @Override
    public Command getCommandAnot() {
        return commandAnot;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getClazz()
     */
    @Override
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getObject()
     */
    @Override
    public Object getObject() {
        return object;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getMethod()
     */
    @Override
    public Method getMethod() {
        return method;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.util.CommandInfo#getFlagData()
     */
    @Override
    public Map<Character, SyntaxType> getFlagData() {
        return new HashMap<Character, SyntaxType>(flagData);
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
        result = prime * result + (clazz == null ? 0 : clazz.hashCode());
        result = prime * result + (commandAnot == null ? 0 : commandAnot.hashCode());
        result = prime * result + (method == null ? 0 : method.hashCode());
        result = prime * result + (object == null ? 0 : object.hashCode());
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
        if (clazz == null) {
            if (other.clazz != null) {
                return false;
            }
        } else if (!clazz.equals(other.clazz)) {
            return false;
        }
        if (commandAnot == null) {
            if (other.commandAnot != null) {
                return false;
            }
        } else if (!commandAnot.equals(other.commandAnot)) {
            return false;
        }
        if (method == null) {
            if (other.method != null) {
                return false;
            }
        } else if (!method.equals(other.method)) {
            return false;
        }
        if (object == null) {
            if (other.object != null) {
                return false;
            }
        } else if (!object.equals(other.object)) {
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
        final int thisPriority = getCommandAnot().priority().getPriority();
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
        return getClass().getName() + "[commandAnot=" + commandAnot + ", clazz=" + clazz + ", object=" + object + ", method="
                + method + ", flagData=" + flagData + "]";
    }
}
