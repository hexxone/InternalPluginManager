/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.bukkit.util;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author Blockhaus2000
 */
public class DynamicPluginCommand extends Command implements PluginIdentifiableCommand {
    private final CommandExecutor executor;
    private final Plugin plugin;

    public DynamicPluginCommand(String[] aliases, String desc, String usage, CommandExecutor executor, Plugin plugin) {
        super(aliases[0], desc, usage, Arrays.asList(aliases));
        this.executor = executor;
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        return executor.onCommand(sender, this, label, args);
    }

    // Getter
    public CommandExecutor getExecutor() {
        return executor;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
