/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util;

import me.lobnews.rwm.bukkit.Main;
import me.lobnews.util.resources.MainPluginResource;

/**
 * 
 * @author Blockhaus2000
 */
public final class ExceptionHandler {
    @MainPluginResource
    private static Main main;

    public static void handle(final Exception ex, final boolean disable) {
        ex.printStackTrace();
        PluginUtil.disable(main);
    }

    public static void handle(final Exception ex) {
        handle(ex, false);
    }
}
