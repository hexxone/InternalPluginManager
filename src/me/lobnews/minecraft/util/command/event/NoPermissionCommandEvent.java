/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.minecraft.util.command.event;

import me.lobnews.util.command.CommandContext;

import org.bukkit.event.HandlerList;

/**
 * 
 * @author Blockhaus2000
 */
public class NoPermissionCommandEvent extends CommandEvent {
    private static final HandlerList handlers = new HandlerList();

    public NoPermissionCommandEvent(final CommandContext context) {
        super(context);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public CommandContext getContext() {
        return (CommandContext) commandInfo;
    }
}
