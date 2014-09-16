/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
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
package com.blockhaus2000.ipm.minecraft.bukkit;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;

/**
 * The main class of the Bukkit implementation of the InternalPluginManager.
 *
 */
public class Main extends JavaPlugin {
    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
     */
    @Override
    public void onEnable() {
        InjectionManager.addResource(BukkitServer.getInstance(), Server.class);
        PluginManager.getInstance().start(new File(super.getDataFolder(), "plugins"));
    }
}
