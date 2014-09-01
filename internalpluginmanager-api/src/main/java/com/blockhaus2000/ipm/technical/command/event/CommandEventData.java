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

import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.command.util.RawCommandContext;

public class CommandEventData {
    private final CommandInfo commandInfo;
    private final CommandEventType eventType;

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
     * @return {@link CommandEventData#commandInfo}.
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

    public static enum CommandEventType {
        NO_PERMISSION(RawCommandContext.class),
        NOT_ENOUGH_ARGUMENTS(RawCommandContext.class),
        TOO_MANY_ARGUMENTS(RawCommandContext.class),
        MISSING_FLAG_VALUE(RawCommandContext.class),
        INCONSTISTENT_FLAG_VALUE(RawCommandContext.class);

        private final Class<? extends CommandInfo> commandInfoType;

        private CommandEventType(final Class<? extends CommandInfo> commandInfoType) {
            this.commandInfoType = commandInfoType;
        }

        public Class<? extends CommandInfo> getCommandInfoType() {
            return commandInfoType;
        }
    }
}
