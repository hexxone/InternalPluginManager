/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.minecraft.util.command.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This {@link Event} is not a regular {@link CommandEvent}, because this will
 * hold a bunch of {@link CommandEvent}s that are fired within a command
 * execution. Use this and not the raw {@link CommandEvent}s to handle
 * {@link CommandEvent}s.
 *
 */
public class CommandEventPackage extends Event implements Iterable<CommandEvent<?>> {
    private static final HandlerList handlers = new HandlerList();

    private final List<CommandEvent<?>> events;

    /**
     * Instances a new {@link CommandEventPackage} with the given
     * {@link CommandEvent}-{@link List}.
     *
     * @param events
     *            The events that are fired in the command execution.
     */
    public CommandEventPackage(final List<CommandEvent<?>> events) {
        this.events = events;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<CommandEvent<?>> iterator() {
        return events.iterator();
    }

    /**
     *
     * @return All events that are fired in the command execution.
     */
    public List<CommandEvent<?>> getEvents() {
        return new ArrayList<CommandEvent<?>>(events);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.event.Event#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return CommandEventPackage.handlers;
    }

    /**
     *
     * @return The {@link HandlerList} of this {@link Event}.
     */
    public static HandlerList getHandlerList() {
        return CommandEventPackage.handlers;
    }
}
