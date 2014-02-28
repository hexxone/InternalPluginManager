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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.ExceptionHandler;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPluginLoader implements IpmPluginLoader {
    private static IpmPluginLoader instance = new SimpleIpmPluginLoader();

    private final List<IpmPluginClassLoader> classLoaders = new ArrayList<IpmPluginClassLoader>();

    private final File pluginFolder;

    @SuppressWarnings("deprecation")
    private SimpleIpmPluginLoader() {
        pluginFolder = new File(IpmMain.getInstance().getDataFolder() + File.separator + "plugins" + File.separator);
        pluginFolder.mkdirs();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginLoader#load(java.io.File)
     */
    @Override
    public IpmPlugin load(final File file) throws PluginException {
        assert file.exists() : "File does not exist!";

        JarFile jarFile;

        try {
            jarFile = new JarFile(file);
        } catch (IOException ex) {
            throw new PluginException(ex);
        }

        InputStream stream;

        ZipEntry pluginYml = jarFile.getEntry("plugin.yml");

        if (pluginYml == null) {
            throw new InvalidPluginDescriptionException("The plugin.yml cannot be found in \"" + file.getAbsolutePath() + "\"!");
        }

        try {
            stream = jarFile.getInputStream(pluginYml);
        } catch (IOException ex) {
            try {
                jarFile.close();
            } catch (IOException ex1) {
                ExceptionHandler.handle(ex);
            }

            throw new PluginException(ex);
        }

        IpmPluginDescription desc = new SimpleIpmPluginDescription();
        desc.load(stream);

        try {
            stream.close();
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        }

        try {
            jarFile.close();
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        }

        SimpleIpmPluginClassLoader classLoader;

        try {
            classLoader = new SimpleIpmPluginClassLoader(this, file, Thread.currentThread().getContextClassLoader());
        } catch (MalformedURLException ex) {
            throw new PluginException(ex);
        }

        Class<? extends SimpleIpmPlugin> pluginClass;

        try {
            pluginClass = Class.forName(desc.getMain(), true, classLoader).asSubclass(SimpleIpmPlugin.class);
        } catch (ClassNotFoundException ex) {
            throw new InvalidPluginDescriptionException("The main class \"" + desc.getMain() + "\" does not exist!");
        } catch (ClassCastException ex) {
            throw new InvalidPluginDescriptionException("The main class \"" + desc.getMain()
                    + "\" does not extend SimpleIpmPlugin!");

        }

        IpmPlugin plugin;

        try {
            plugin = pluginClass.newInstance();
        } catch (InstantiationException ex) {
            throw new PluginException(ex);
        } catch (IllegalAccessException ex) {
            throw new PluginException(ex);
        }

        classLoaders.add(classLoader);

        plugin.init(desc);
        plugin.onLoad();

        return plugin;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginLoader#load(java.lang.String)
     */
    @Override
    public IpmPlugin load(final String pluginName) throws PluginException {
        return load(new File(pluginFolder.getPath() + pluginName + ".jar"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginLoader#loadAll()
     */
    @Override
    public Set<IpmPlugin> loadAll() throws PluginException {
        Set<IpmPlugin> plugins = new HashSet<IpmPlugin>();

        for (File target : pluginFolder.listFiles()) {
            if (target.isDirectory() || !target.getName().endsWith(".jar")) {
                continue;
            }

            plugins.add(load(target));
        }

        return plugins;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginLoader#getClassByName(java.lang.String)
     */
    @Override
    public Class<?> getClassByName(final String className) {
        Class<?> clazz = null;

        for (IpmPluginClassLoader target : classLoaders) {
            try {
                clazz = target.findClass(className, false);
                break;
            } catch (ClassNotFoundException ex) {
                // fails silent
            }
        }

        return clazz;
    }

    /**
     * Will provide singleton.
     * 
     * @return An instance of {@link SimpleIpmServer}.
     */
    public static IpmPluginLoader getInstance() {
        return SimpleIpmPluginLoader.instance;
    }
}
