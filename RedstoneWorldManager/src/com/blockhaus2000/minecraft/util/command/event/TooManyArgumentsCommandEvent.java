/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.minecraft.util.command.event;

import org.bukkit.event.HandlerList;

import com.blockhaus2000.util.command.RawCommandContext;

/**
 * 
 * @author Blockhaus2000
 */
public class TooManyArgumentsCommandEvent extends CommandEvent<RawCommandContext> {
    private static final HandlerList handlers = new HandlerList();

    public TooManyArgumentsCommandEvent(final RawCommandContext command) {
        super(command);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
