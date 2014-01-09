/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.minecraft.util.command.event;

import org.bukkit.event.Event;

import com.blockhaus2000.util.command.CommandInfo;

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
