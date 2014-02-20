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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.ipmtest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.minecraft.util.command.event.NoPermissionCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.util.CommandUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class IpmTestMain extends IpmPlugin implements Listener {
    @MainPluginResource
    private IpmMain main;

    public void onEnable() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        }

        CommandUtil.registerCommands(new TestCommands(), main);

        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onNotEnoughtArgumentsCommand(NotEnoughArgumentsCommandEvent event) {
        System.out.println("onNotEnoughtArgumentsCommand");
    }

    @EventHandler
    public void onTooManyArgumentsCommand(TooManyArgumentsCommandEvent event) {
        System.out.println("onTooManyArgumentsCommand");
    }

    @EventHandler
    public void onNoPermissionCommand(NoPermissionCommandEvent event) {
        System.out.println("onNoPermissionCommand");
    }
}
