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
public abstract class CommandEvent<T extends CommandInfo> extends Event {
    protected final T command;
    protected boolean executed;

    public CommandEvent(final T command) {
        this.command = command;
    }

    public CommandEvent(final T command, final boolean executed) {
        this(command);
        this.executed = executed;
    }

    // Getter + Setter
    // Getter
    public T getCommand() {
        return command;
    }

    public boolean isExecuted() {
        return executed;
    }

    // Setter
    public void setExecuted(final boolean executed) {
        this.executed = executed;
    }
}
