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

import org.bukkit.event.HandlerList;

import com.blockhaus2000.util.command.RawCommandContext;

/**
 * 
 * @author Blockhaus2000
 */
public class UnknownSecondLevelCommandEvent extends CommandEvent<RawCommandContext> {
    private static final HandlerList handlers = new HandlerList();

    public UnknownSecondLevelCommandEvent(final RawCommandContext command) {
        super(command);
    }

    @Override
    public HandlerList getHandlers() {
        return UnknownSecondLevelCommandEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return UnknownSecondLevelCommandEvent.handlers;
    }
}
