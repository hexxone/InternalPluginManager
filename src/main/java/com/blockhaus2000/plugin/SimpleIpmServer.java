/* This file is part of InternalPluginManager
 * 
 * Copyright 2014 Blockhaus2000
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Server;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmServer implements IpmServer {
    private static final IpmServer instance = new SimpleIpmServer();

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmServer#getPluginManager()
     */
    @Override
    public IpmPluginManager getPluginManager() {
        return SimpleIpmPluginManager.getInstance();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmServer#getBukkitServer()
     */
    @Override
    public Server getBukkitServer() {
        return Bukkit.getServer();
    }

    /**
     * Will provide singleton.
     * 
     * @return An instance of {@link SimpleIpmServer}.
     */
    public static IpmServer getInstance() {
        return SimpleIpmServer.instance;
    }
}
