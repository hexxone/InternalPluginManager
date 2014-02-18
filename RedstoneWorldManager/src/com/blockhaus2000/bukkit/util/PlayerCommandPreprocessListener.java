/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.bukkit.util;

import org.bukkit.command.CommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * 
 * @author Blockhaus2000
 */
public class PlayerCommandPreprocessListener implements Listener {
    private final CommandMap commandMap;

    public PlayerCommandPreprocessListener(final CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (commandMap.dispatch(event.getPlayer(), event.getMessage())) {
            event.setCancelled(true);
        }
    }
}
