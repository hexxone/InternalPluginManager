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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.minecraft.util.command.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.blockhaus2000.util.command.RawCommandContext;

/**
 * This class represents an {@link Event} (specifically {@link CommandEvent})
 * that will be callen if a command has been executed and too many arguments
 * were given.
 * 
 * @author Blockhaus2000
 */
public class TooManyArgumentsCommandEvent extends CommandEvent<RawCommandContext> {
    private static final HandlerList handlers = new HandlerList();

    /**
     * This will initialize a new {@link TooManyArgumentsCommandEvent} with the
     * given {@link RawCommandContext}.
     * 
     * @param command
     *            The specific {@link RawCommandContext}.
     */
    public TooManyArgumentsCommandEvent(final RawCommandContext command) {
        super(command);
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
