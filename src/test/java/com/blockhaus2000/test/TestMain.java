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
package com.blockhaus2000.test;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.minecraft.util.command.event.CommandEvent;
import com.blockhaus2000.minecraft.util.command.event.CommandEventPackage;
import com.blockhaus2000.plugin.SimpleIpmPlugin;
import com.blockhaus2000.util.CommandRegistrationUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.StringUtil;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class TestMain extends SimpleIpmPlugin implements Listener {
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

        CommandRegistrationUtil.registerCommands(new TestCommands(), main);

        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onCommandEventPackage(final CommandEventPackage eventPackage) {
        final List<String> events = new ArrayList<String>();

        for (CommandEvent<?> target : eventPackage) {
            events.add(target.getEventName());
        }

        System.out.println("Events: <" + StringUtil.joinString(",", events) + ">");
    }
}
