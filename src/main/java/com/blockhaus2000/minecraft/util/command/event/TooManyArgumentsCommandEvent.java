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

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.blockhaus2000.util.command.RawCommandContext;

/**
 * This {@link CommandEvent} will be fired if a command has been executed but
 * the {@link CommandSender} does have too many arguments.
 *
 * @author Blockhaus2000
 */
public class TooManyArgumentsCommandEvent extends CommandEvent<RawCommandContext> {
    private static final HandlerList handlers = new HandlerList();

    /**
     * Instances a new {@link TooManyArgumentsCommandEvent} of the given
     * {@link RawCommandContext}.
     *
     * @param command
     *            The {@link RawCommandContext} where the event has been fired
     *            in the execution.
     */
    public TooManyArgumentsCommandEvent(final RawCommandContext command) {
        super(command, CommandEventType.TOO_MANY_ARGUMENTS);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.event.Event#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return TooManyArgumentsCommandEvent.handlers;
    }

    /**
     *
     * @return The {@link HandlerList} of this {@link Event}.
     */
    public static HandlerList getHandlerList() {
        return TooManyArgumentsCommandEvent.handlers;
    }
}
