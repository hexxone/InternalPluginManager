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
package com.blockhaus2000.bukkit.util;

import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.ReflectionUtil;
import com.blockhaus2000.util.command.CommandInfo;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleDynamicPluginCommandManager implements DynamicPluginCommandManager {
    private final Plugin plugin;
    private final CommandExecutor executor;

    private CommandMap fallbackCommandMap;

    public SimpleDynamicPluginCommandManager(final Plugin plugin, final CommandExecutor executor) {
        this.plugin = plugin;
        this.executor = executor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManager#register(com.blockhaus2000.util.command.CommandInfo)
     */
    @Override
    public void register(final CommandInfo registered) {
        assert registered != null : "Registered cannot be null!";

        Command cmdAnot = registered.getCommandAnot();
        SimpleDynamicPluginCommand cmd = new SimpleDynamicPluginCommand(cmdAnot.aliases(), cmdAnot.desc(), cmdAnot.usage(),
                executor, plugin);
        cmd.setPermission(cmdAnot.permission());
        getCommandMap().register(plugin.getDescription().getName(), cmd);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManager#register(java.util.Collection)
     */
    @Override
    public void register(final Collection<CommandInfo> registered) {
        assert registered != null : "Registered cannot be null!";

        for (CommandInfo target : registered) {
            register(target);
        }
    }

    private CommandMap getCommandMap() {
        CommandMap commandMap = ReflectionUtil.getFieldValue(Bukkit.getServer().getPluginManager(), "commandMap");

        if (commandMap == null) {
            if (fallbackCommandMap != null) {
                ChatOut.log(Level.SEVERE, "Could not retrieve server CommandMap, using fallback instead!");
                commandMap = fallbackCommandMap = new SimpleCommandMap(Bukkit.getServer());
                Bukkit.getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(commandMap), plugin);
            }
        }

        return commandMap;
    }
}
