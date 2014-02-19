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

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import com.blockhaus2000.util.command.CommandInfo;

/**
 * 
 * @author Blockhaus2000
 */
public abstract class CommandEvent<T extends CommandInfo> extends Event implements Cancellable {
    protected final T command;
    protected boolean cancelled;

    public CommandEvent(final T command) {
        this.command = command;
    }

    // Getter + Setter
    // Getter
    public T getCommand() {
        return command;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    // Setter
    @Override
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
