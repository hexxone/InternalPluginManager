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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.plugin.exception.PluginNotFoundException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class IpmPluginLoader {
    private static IpmPluginLoader instance;

    @MainPluginResource
    private IpmMain main;

    private final String mainPluginPath;

    private IpmPluginLoader() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }

        System.out.println(main);

        mainPluginPath = main.getDataFolder().getAbsolutePath() + File.separator + "plugins" + File.separator
                + "%pluginName%.jar";

        File mainDir = new File(mainPluginPath.replace("%pluginName%.jar", ""));
        if (!mainDir.exists()) {
            mainDir.mkdirs();
        }
    }

    public IpmPluginDescriptionFile loadPlugin(final String pluginName) throws PluginNotFoundException,
            InvalidPluginDescriptionException, PluginException, InstantiationException, IllegalAccessException, IOException {
        String pluginPath = mainPluginPath.replace("%pluginName%", pluginName);

        File file = new File(pluginPath);

        if (!file.exists()) {
            throw new PluginNotFoundException("The plugin " + pluginName + " cannot be found at " + pluginPath);
        }

        JarFile pluginJar = null;
        InputStream stream = null;

        try {
            pluginJar = new JarFile(pluginPath);

            ZipEntry pluginDescJarEntry = pluginJar.getEntry("plugin.yml");

            if (pluginDescJarEntry == null) {
                pluginJar.close();

                throw new InvalidPluginDescriptionException("The plugin.yml cannot be found in \"" + pluginPath + "\"!");
            }

            stream = pluginJar.getInputStream(pluginDescJarEntry);
        } catch (IOException ex) {
            throw new PluginException(ex);
        }

        IpmPluginDescriptionFile descFile = new IpmPluginDescriptionFile(stream, file);

        stream.close();
        pluginJar.close();

        return descFile;
    }

    public Set<IpmPluginDescriptionFile> loadAllPlugins() throws PluginNotFoundException, InvalidPluginDescriptionException,
            InstantiationException, IllegalAccessException, PluginException, IOException {
        Set<IpmPluginDescriptionFile> plugins = new HashSet<IpmPluginDescriptionFile>();

        for (File target : new File(mainPluginPath.replace("%pluginName%.jar", "")).listFiles()) {
            if (target.isDirectory()) {
                continue;
            }

            plugins.add(loadPlugin(target.getName().replace(".jar", "")));
        }

        return plugins;
    }

    public static IpmPluginLoader getInstance() {
        if (instance == null) {
            instance = new IpmPluginLoader();
        }

        return instance;
    }
}
