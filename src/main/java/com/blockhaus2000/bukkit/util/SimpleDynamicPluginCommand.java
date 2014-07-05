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

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;

/**
 * Represents a {@link DynamicPluginCommand}.
 *
 * @see org.bukkit.command.Command
 * @see com.blockhaus2000.bukkit.util.DynamicPluginCommand
 */
public class SimpleDynamicPluginCommand extends Command implements DynamicPluginCommand {
    private final CommandExecutor executor;
    private final Plugin plugin;

    /**
     * Instances a new {@link SimpleDynamicPluginCommand} with the given
     * arguments. Will call the super constructor.
     *
     * @param aliases
     *            Will be used in the <code>super</code>-call.
     * @param desc
     *            Will be used in the <code>super</code>-call.
     * @param usage
     *            Will be used in the <code>super</code>-call.
     * @param executor
     *            The {@link CommandExecutor} that executes the command.
     * @param plugin
     *            The {@link Plugin} that holds the command.
     */
    public SimpleDynamicPluginCommand(final String[] aliases, final String desc, final String usage,
            final CommandExecutor executor, final Plugin plugin) {
        super(aliases[0], desc, usage, Arrays.asList(aliases));
        this.executor = executor;
        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.Command#execute(org.bukkit.command.CommandSender,
     *      java.lang.String, java.lang.String[])
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommand#execute(org.bukkit.command.CommandSender,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public boolean execute(final CommandSender sender, final String label, final String[] args) {
        return executor.onCommand(sender, this, label, args);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.Command#tabComplete(org.bukkit.command.CommandSender,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public List<String> tabComplete(final CommandSender sender, final String label, final String[] args)
            throws IllegalArgumentException {
        if (executor instanceof TabExecutor) {
            return ((TabExecutor) executor).onTabComplete(sender, this, label, args);
        }

        return super.tabComplete(sender, label, args);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommand#getExecutor()
     */
    @Override
    public CommandExecutor getExecutor() {
        return executor;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.PluginIdentifiableCommand#getPlugin()
     */
    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
