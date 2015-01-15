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

import java.util.Arrays;
import java.util.List;

/**
 * A {@link CommandEvent} will be fired if a command cannot be executed
 * correctly because of common errors (like not enough permissions, not enough
 * arguments or something like that). These are no exceptions but has to be
 * passed to the user. This class contains a list of {@link CommandEventData},
 * because within one command execution, more than one event can be fired. But
 * you do not want to catch them all and check if there are other events catched
 * for that command and player and blablabla...
 *
 * <p>
 * Events will be fired with {@link CommandEventManager#fire(CommandEvent)}.
 * They will not be fired automaticly.
 * </p>
 *
 */
public final class CommandEvent {
    /**
     * The {@link CommandEventData} list that has been passed whilest
     * constructing.
     *
     */
    private final List<CommandEventData> commandEventData;

    /**
     * Constructor of CommandEvent.
     *
     * <p>
     * <b> NOTE: The event will not be fired automicly. To fire events, invoke
     * {@link CommandEventManager#fire(CommandEvent)}. </b>
     * </p>
     *
     * @param commandEventData
     *            The {@link CommandEventData} list to pass in.
     */
    public CommandEvent(final List<CommandEventData> commandEventData) {
        assert commandEventData != null && !commandEventData.isEmpty() : "CommandEventData cannot be null or empty!";

        this.commandEventData = commandEventData;
    }

    /**
     * Constructor of CommandEvent. Delegates to
     * <code>this(java.util.List)</code>.
     *
     * @param commandEventData
     *            Will be passed into <code>this</code> call.
     */
    public CommandEvent(final CommandEventData... commandEventData) {
        this(Arrays.asList(commandEventData));
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
        result = prime * result + (this.commandEventData == null ? 0 : this.commandEventData.hashCode());
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
        if (!(obj instanceof CommandEvent)) {
            return false;
        }
        final CommandEvent other = (CommandEvent) obj;
        if (this.commandEventData == null) {
            if (other.commandEventData != null) {
                return false;
            }
        } else if (!this.commandEventData.equals(other.commandEventData)) {
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
        return this.getClass().getName() + "[commandEventData=" + this.commandEventData + "]";
    }

    /**
     *
     * @return {@link CommandEvent#commandEventData}.
     */
    public List<CommandEventData> getCommandEventData() {
        return this.commandEventData;
    }
}
