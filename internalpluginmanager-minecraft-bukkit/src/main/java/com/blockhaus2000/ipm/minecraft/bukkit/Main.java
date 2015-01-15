/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 */
package com.blockhaus2000.ipm.minecraft.bukkit;

import java.io.File;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.blockhaus2000.ipm.technical.plugin.PluginManager;

/**
 * The main class of the InternalPluginManager Minecraft API implementation for
 * Bukkit.
 *
 */
public class Main extends JavaPlugin {
    /**
     * The instance of this class.
     *
     */
    private static Plugin plugin;

    /**
     * Constructor of Main.
     *
     */
    public Main() {
        if (Main.plugin != null) {
            throw new IllegalStateException("Only one instance of this class can exist!");
        }
        Main.plugin = this;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
     */
    @Override
    public void onEnable() {
        this.addInjectionResources();

        PluginManager.getInstance().start(new File(this.getDataFolder(), "plugins"));
    }

    /**
     * Adds all injectable resources to the injection manager.
     *
     */
    private void addInjectionResources() {
        // For safety reasons, BukkitServer registers itself.
        BukkitServer.getInstance();
    }

    /**
     *
     * @return {@link Main#plugin}
     */
    public static Plugin getPlugin() {
        return Main.plugin;
    }
}
