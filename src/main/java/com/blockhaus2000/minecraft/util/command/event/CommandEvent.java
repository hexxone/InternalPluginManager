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

import com.blockhaus2000.util.command.CommandInfo;

/**
 * This is the main class for all {@link CommandEvent}s.
 * 
 * @author Blockhaus2000
 * @param <T>
 *            The specific {@link CommandInfo}.
 */
public abstract class CommandEvent<T extends CommandInfo> extends Event {
    protected final T command;
    protected boolean cancelled;

    /**
     * Instances a new {@link CommandEvent} with the given {@link CommandInfo}
     * (specified by the type <code>T</code>).
     * 
     * @param command
     *            The {@link CommandInfo}.
     */
    public CommandEvent(final T command) {
        this.command = command;
    }

    /**
     * 
     * @return {@link CommandEvent#command}
     */
    public T getCommand() {
        return command;
    }
}
