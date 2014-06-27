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
package com.blockhaus2000.plugin.update;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.blockhaus2000.main.bukkit.InternalPluginManager;
import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.plugin.IpmPluginDescription;
import com.blockhaus2000.plugin.SimpleIpmPluginDescription;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;
import com.google.common.io.Files;

/**
 *
 * @author Blockhaus2000
 */
public class SimpleUpdater implements Updater {
    private static final Updater instance = new SimpleUpdater();

    @MainPluginResource
    private IpmMain main;

    private final File updateFolder;

    private SimpleUpdater() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        }

        updateFolder = new File(main.getDataFolder() + File.separator + "updates" + File.separator);
        updateFolder.mkdirs();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.update.Updater#update(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void update(final IpmPlugin oldPlugin, final File updateFile) throws PluginException, IOException {
        final File file = oldPlugin.getFile();

        file.delete();

        try {
            Files.move(updateFile, file);
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
            return;
        }

        InternalPluginManager.getServer().getPluginManager().disable(oldPlugin);
        InternalPluginManager.getServer().getPluginManager().unregister(oldPlugin);

        IpmPlugin plugin = InternalPluginManager.getServer().getPluginLoader().load(file);
        InternalPluginManager.getServer().getPluginManager().register(plugin);
        InternalPluginManager.getServer().getPluginManager().enable(plugin);

        // InternalPluginManager.getServer().getPluginManager().reload(plugin);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.update.Updater#update(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void update(final IpmPlugin plugin) throws PluginException, IOException {
        for (Update target : getUpdates()) {
            IpmPlugin oldPlugin = target.getOldPlugin();

            if (!oldPlugin.getName().equals(plugin.getName())) {
                continue;
            }

            update(oldPlugin, target.getNewFile());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.update.Updater#hasUpdate(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean hasUpdate(final String oldVersion, final String newVersion) {
        return !oldVersion.equals(newVersion);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.update.Updater#hasUpdate(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public boolean hasUpdate(final IpmPlugin plugin) throws PluginException {
        for (Update target : getUpdates()) {
            IpmPlugin oldPlugin = target.getOldPlugin();

            if (!oldPlugin.getName().equals(plugin.getName())) {
                continue;
            }

            if (hasUpdate(oldPlugin.getDescription().getVersion(), target.getNewDescription().getVersion())) {
                return true;
            }
        }

        return false;
    }

    private Set<Update> getUpdates() throws PluginException {
        Set<Update> updates = new HashSet<Update>();

        for (File target : updateFolder.listFiles()) {
            if (target.isDirectory() || !target.getName().endsWith(".jar")) {
                continue;
            }

            JarFile jarFile;

            try {
                jarFile = new JarFile(target);
            } catch (IOException ex) {
                ExceptionHandler.handle(ex);
                continue;
            }

            IpmPluginDescription desc = getDescription(jarFile);
            updates.add(new Update(InternalPluginManager.getServer().getPluginManager().getPlugin(desc.getName()), desc, jarFile,
                    target));
        }

        return updates;
    }

    private IpmPluginDescription getDescription(final JarFile jarFile) throws PluginException {
        ZipEntry pluginYml = jarFile.getEntry("plugin.yml");

        if (pluginYml == null) {
            throw new PluginException("The plugin \"" + jarFile.getName()
                    + "\" to update does not contains the \"plugin.yml\" in the root folder!");
        }

        IpmPluginDescription desc = new SimpleIpmPluginDescription();

        try {
            desc.load(jarFile.getInputStream(pluginYml));
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
            return null;
        }

        return desc;
    }

    /**
     *
     * @return An instance of a {@link Updater}.
     */
    public static Updater getInstance() {
        return SimpleUpdater.instance;
    }
}
