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
import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NoPermissionCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;
import com.blockhaus2000.plugin.SimpleIpmPlugin;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

//import com.blockhaus2000.plugin_old.IpmPlugin;

/**
 * 
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class IpmTestMain extends SimpleIpmPlugin implements Listener {
    @MainPluginResource
    private IpmMain main;

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.SimpleIpmPlugin#onEnable()
     */
    @Override
    public void onEnable() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        }

        registerCommands(new TestCommands());

        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onNotEnoughtArgumentsCommand(final NotEnoughArgumentsCommandEvent event) {
        System.out.println("onNotEnoughtArgumentsCommand: " + event.getCommand().getCommand().getName());
    }

    @EventHandler
    public void onTooManyArgumentsCommand(final TooManyArgumentsCommandEvent event) {
        System.out.println("onTooManyArgumentsCommand: " + event.getCommand().getCommand().getName());
    }

    @EventHandler
    public void onNoPermissionCommand(final NoPermissionCommandEvent event) {
        System.out.println("onNoPermissionCommand: " + event.getCommand().getCommand().getName());
    }

    @EventHandler
    public void onIllegalSyntaxCommand(final IllegalSyntaxCommandEvent event) {
        System.out.print("onIllegalSyntaxCommand: " + event.getCommand().getCommand().getName() + ", : "
                + event.getIllegalSyntaxType());
    }
}
