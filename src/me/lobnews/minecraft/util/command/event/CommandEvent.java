/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.minecraft.util.command.event;

import me.lobnews.util.command.CommandInfo;

import org.bukkit.event.Event;

/**
 * 
 * @author Blockhaus2000
 */
public abstract class CommandEvent extends Event {
    protected final CommandInfo commandInfo;

    public CommandEvent(final CommandInfo commandInfo) {
        this.commandInfo = commandInfo;
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }
}
