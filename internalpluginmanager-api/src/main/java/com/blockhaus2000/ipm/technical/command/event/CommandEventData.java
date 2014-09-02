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
package com.blockhaus2000.ipm.technical.command.event;

import com.blockhaus2000.ipm.technical.command.CommandSender;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.command.util.RawCommandContext;

/**
 * The {@link CommandEventData} is used to store informations about one
 * {@link CommandEvent} part. That is because one command execution can fire
 * more than one event. With this system, you only have to catch them once for
 * each execution.
 *
 */
public class CommandEventData {
    /**
     * The common information about the command execution that fired the event.
     *
     */
    private final CommandInfo commandInfo;
    /**
     * The {@link CommandEventType}.
     *
     */
    private final CommandEventType eventType;

    /**
     * Constructor of CommandEventData.
     *
     * @param commandInfo
     *            The common information about the command execution that fired
     *            the event. The {@link CommandInfo} object has to be an
     *            instance of the class that is associated with the given
     *            {@link CommandEventType}. So
     *            <code>eventType.getCommandInfoType().isInstance(commandInfo)</code>
     *            has to return <code>true</code>.
     * @param eventType
     *            The {@link CommandEventType}.
     */
    public CommandEventData(final CommandInfo commandInfo, final CommandEventType eventType) {
        assert commandInfo != null : "CommandInfo cannot be null!";
        assert eventType != null : "EventType cannot be null!";
        assert eventType.getCommandInfoType().isInstance(commandInfo) : "CommandInfo has to be an instance of <"
                + eventType.getClass().getName() + ">!";

        this.commandInfo = commandInfo;
        this.eventType = eventType;
    }

    /**
     *
     * @return {@link CommandEventData#commandInfo}. This can be casted safely
     *         to the {@link CommandInfo} type that is associated with the
     *         {@link CommandEventType}.
     */
    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    /**
     *
     * @return {@link CommandEventData#eventType}.
     */
    public CommandEventType getEventType() {
        return eventType;
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
        result = prime * result + (commandInfo == null ? 0 : commandInfo.hashCode());
        result = prime * result + (eventType == null ? 0 : eventType.hashCode());
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
        if (!(obj instanceof CommandEventData)) {
            return false;
        }
        final CommandEventData other = (CommandEventData) obj;
        if (commandInfo == null) {
            if (other.commandInfo != null) {
                return false;
            }
        } else if (!commandInfo.equals(other.commandInfo)) {
            return false;
        }
        if (eventType != other.eventType) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[commandInfo=" + commandInfo + ", eventType=" + eventType + "]";
    }

    /**
     * The {@link CommandEventType} makes it easy to check what is the real
     * cause of the command event.
     *
     */
    public static enum CommandEventType {
        /**
         * If the {@link CommandSender} has not enough permissions.
         *
         */
        NO_PERMISSION(RawCommandContext.class),
        /**
         * If not enough arguments where passed.
         *
         */
        NOT_ENOUGH_ARGUMENTS(RawCommandContext.class),
        /**
         * If too many arguments where passed.
         *
         */
        TOO_MANY_ARGUMENTS(RawCommandContext.class),
        /**
         * If a flag value is missing.
         *
         */
        UNAVAILABLE_FLAG_VALUE(RawCommandContext.class),
        /**
         * If a flag value is inconsistent with its type.
         *
         */
        INCONSTISTENT_FLAG_VALUE(RawCommandContext.class),
        UNAVAILABLE_SECOND_LEVEL_COMMAND(CommandInfo.class),
        UNKNOWN_SECOND_LEVEL_COMMAND(CommandInfo.class);

        /**
         * The specific type of the {@link CommandInfo} that has to be passed
         * info a new {@link CommandEventData}.
         *
         */
        private final Class<? extends CommandInfo> commandInfoType;

        /**
         * Constructor of CommandEventType.
         *
         * @param commandInfoType
         *            The specific {@link CommandInfo} type.
         */
        private CommandEventType(final Class<? extends CommandInfo> commandInfoType) {
            this.commandInfoType = commandInfoType;
        }

        /**
         *
         * @return {@link CommandEventType#commandInfoType}.
         */
        public Class<? extends CommandInfo> getCommandInfoType() {
            return commandInfoType;
        }
    }
}
