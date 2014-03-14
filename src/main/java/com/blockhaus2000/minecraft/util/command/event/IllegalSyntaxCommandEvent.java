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

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.blockhaus2000.util.command.RawCommandContext;

/**
 * A {@link CommandEvent} that is fired if the syntax of a command is
 * wrong/illegal. See the {@link IllegalSyntaxType} for a detailed error
 * message.
 * 
 * @author Blockhaus2000
 * @see com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxCommandEvent
 */
public class IllegalSyntaxCommandEvent extends CommandEvent<RawCommandContext> {
    private static final HandlerList handlers = new HandlerList();

    private final IllegalSyntaxType illegalSyntaxType;

    /**
     * Instances a new {@link IllegalSyntaxCommandEvent} of the given
     * {@link RawCommandContext} and the given {@link IllegalSyntaxType}.
     * 
     * @param command
     *            The {@link RawCommandContext} where the event has been fired
     *            in the execution.
     * @param illegalSyntaxType
     *            The {@link IllegalSyntaxType} that specifies the event.
     */
    public IllegalSyntaxCommandEvent(final RawCommandContext command, final IllegalSyntaxType illegalSyntaxType) {
        super(command);

        this.illegalSyntaxType = illegalSyntaxType;
    }

    /**
     * 
     * @return The {@link IllegalSyntaxType} that specifies the event.
     */
    public IllegalSyntaxType getIllegalSyntaxType() {
        return illegalSyntaxType;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.event.Event#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return IllegalSyntaxCommandEvent.handlers;
    }

    /**
     * 
     * @return The {@link HandlerList} of this {@link Event}.
     */
    public static HandlerList getHandlerList() {
        return IllegalSyntaxCommandEvent.handlers;
    }
}
