/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.rwm.bukkit;

import java.lang.reflect.Field;

import me.lobnews.util.ExceptionHandler;
import me.lobnews.util.PluginUtil;
import me.lobnews.util.resources.MainPluginResource;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author Blockhaus2000
 */
public class Main extends JavaPlugin {
    private static Main instance;

    public void onEnable() {
        try {
            initializeResources(new PluginUtil());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }
    }

    public static void initializeResources(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field target : clazz.getFields()) {
            if (target.isAnnotationPresent(MainPluginResource.class)) {
                target.set(obj, instance);
            }
        }
    }
}
