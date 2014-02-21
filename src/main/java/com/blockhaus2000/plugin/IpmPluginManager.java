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
package com.blockhaus2000.plugin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.plugin.exception.PluginNotFoundException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;

/**
 * 
 * @author Blockhaus2000
 */
public class IpmPluginManager implements Iterable<IpmPluginData> {
    private static IpmPluginManager instance;

    @MainPluginResource
    private IpmMain main;

    private Set<IpmPluginData> plugins = new HashSet<IpmPluginData>();

    private IpmPluginManager() {
        // Nothing to do (only to provide singleton pattern)
    }

    public IpmPluginData getPlugin(final String name) {
        for (IpmPluginData target : plugins) {
            if (target.getDescription().getName().equalsIgnoreCase(name)) {
                return target;
            }
        }

        return null;
    }

    /**
     * This will add the given {@link IpmPlugin} to
     * {@link IpmPluginManager#plugins}.
     * 
     * <p>
     * <b> Note: This will NOT register the given plugin (does not call
     * {@link IpmPlugin#onEnable()} or something like that). </b>
     * </p>
     * 
     * @param plugin
     *            The {@link IpmPlugin} that has to be added to the {@link Set}
     *            {@link IpmPluginManager#plugins}.
     * @throws IllegalArgumentException
     *             Will be throwed if the given {@link IpmPlugin} is
     *             <code>null</code>.
     */
    public void addPlugin(final IpmPlugin plugin, final IpmPluginDescriptionFile desc) {
        assert plugin != null : "Plugin cannot be null!";

        plugins.add(new IpmPluginData(plugin, desc));
    }

    public void registerPlugin(final IpmPlugin plugin, final IpmPluginDescriptionFile desc) {
        assert plugin != null : "Plugin cannot be null!";

        plugin.onLoad();
        plugin.setPluginDescriptionFile(desc);
        plugin.init();
        addPlugin(plugin, desc);
        plugin.onEnable();
    }

    public void registerAllPlugins() {
        try {
            for (IpmPluginDescriptionFile target : IpmPluginLoader.getInstance().loadAllPlugins()) {
                registerPlugin(target.getMain().newInstance(), target);
            }
        } catch (PluginNotFoundException ex) {
            ExceptionHandler.handle(ex);
        } catch (InvalidPluginDescriptionException ex) {
            ExceptionHandler.handle(ex);
        } catch (InstantiationException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        } catch (PluginException ex) {
            ExceptionHandler.handle(ex);
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    public void removePlugin(final IpmPlugin plugin) {
        assert plugin != null : "Plugin cannot be null!";

        plugins.remove(plugin);
    }

    public void unregisterPlugin(final IpmPlugin plugin) {
        assert plugin != null : "Plugin cannot be null!";

        plugin.onDisable();
        removePlugin(plugin);
    }

    public void unregisterAllPlugins() {
        for (IpmPluginData target : plugins) {
            removePlugin(target.getPlugin());
        }
    }

    @Override
    public Iterator<IpmPluginData> iterator() {
        return plugins.iterator();
    }

    public static IpmPluginManager getInstance() {
        if (instance == null) {
            instance = new IpmPluginManager();
        }

        return instance;
    }

    // Getter + Setter
    // Getter
    public Set<IpmPluginData> getPlugins() {
        return plugins;
    }
}
