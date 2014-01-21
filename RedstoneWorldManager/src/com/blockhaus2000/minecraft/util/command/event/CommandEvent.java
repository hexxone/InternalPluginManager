/*
 * This file is part of RedstoneWorldManager
 * 
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
