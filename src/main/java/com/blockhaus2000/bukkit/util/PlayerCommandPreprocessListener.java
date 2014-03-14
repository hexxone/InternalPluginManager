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
package com.blockhaus2000.bukkit.util;

import org.bukkit.command.CommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Will be used for command execution if something goes wrong with the
 * {@link DynamicPluginCommandManager} reflection usage.
 * 
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class PlayerCommandPreprocessListener implements Listener {
    private final CommandMap commandMap;

    public PlayerCommandPreprocessListener(final CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        if (commandMap.dispatch(event.getPlayer(), event.getMessage())) {
            event.setCancelled(true);
        }
    }
}
