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

/**
 * Implement this interface and register it to the {@link CommandEventManager}
 * to listent to events.
 *
 */
public interface CommandEventListener {
    /**
     * This method will be invoked if a {@link CommandEvent} was fired.
     *
     * @param commandEvent
     *            The {@link CommandEvent} that was fired.
     */
    public void onCommandEvent(final CommandEvent commandEvent);
}
