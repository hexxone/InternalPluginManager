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
package com.blockhaus2000.ipm.technical.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

import com.blockhaus2000.ipm.base.CommonConstants;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginIOException;
import com.blockhaus2000.tagstoragesystem.TSS;

/**
 * The loader for plugins.
 *
 * <p>
 * <b> NOTE: Do NOT interact with methods via reflection! </b>
 * </p>
 *
 */
public final class PluginLoader {
    /**
     * The InternalPluginManager system logger.
     *
     */
    private static final Logger LOGGER = Logger.getLogger(CommonConstants.INTERNALPLUGINMANAGER_SYSTEM_LOGGER_NAME);

    /**
     * THE instance of the {@link PluginLoader}.
     *
     */
    private static final PluginLoader INSTANCE = new PluginLoader();

    /**
     * The plugin meta cache, containing plugin metas associated with the jar
     * names.
     *
     */
    private final TSS pluginMetaCache = new TSS(new File(SimplePluginManager.getClassInstance().getPluginDirectory(),
            "plugin-meta-cache.tss"));

    /**
     * A {@link Map} containing all (plugin) class loaders that can be asked for
     * classes.
     *
     * <p>
     * <b> NOTE: This should be the only place where instances of plugin class
     * loaders are stored. </b>
     * </p>
     *
     */
    private final Map<String, PluginClassLoader> classLoaders = new HashMap<String, PluginClassLoader>();

    /**
     * Constructor of PluginLoader.
     *
     */
    private PluginLoader() {
        try {
            this.pluginMetaCache.load();
        } catch (final FileNotFoundException dummy) {
            // This fails silent.
            //
            // It fails silent because a FileNotFoundException is thrown if the
            // file (the plugin meta cache file) does not exists. The file is
            // created later on.
        } catch (final IOException cause) {
            throw new PluginException("An error occurred whilest loading the plugin meta cache!", cause);
        }
    }

    /**
     * Removes the plugin with the given name from the plugin loader.
     *
     * <p>
     * <b> NOTE: This does not delete the plugin in the plugin directory. </b>
     * </p>
     * <p>
     * <b> NOTE: This does not disable the plugin. </b>
     * </p>
     *
     * @param rawName
     *            The name of the plugin to remove from the plugin loader.
     */
    synchronized void remove(final String rawName) {
        final String name = rawName.toLowerCase();

        final PluginClassLoader classLoader = this.classLoaders.get(name);
        if (classLoader != null) {
            classLoader.clear();
            this.classLoaders.remove(name);
            System.gc();
        }
    }

    /**
     * Delegates to {@link PluginLoader#remove(String)} with
     * <code>name = plugin.getName()</code>.
     *
     * @param plugin
     *            Is passed into {@link PluginLoader#remove(String)}.
     */
    synchronized void remove(final Plugin plugin) {
        this.remove(plugin.getName());
    }

    /**
     * Delegates to {@link PluginLoader#remove(String)} with
     * <code>name = plugin.getName()</code> and deletes the plugin jar file.
     *
     * @param plugin
     *            The plugin to delete.
     */
    synchronized void delete(final Plugin plugin) {
        this.remove(plugin.getName());
        plugin.getPluginMeta().getFile().delete();
    }

    /**
     * Parses the meta data for the given plugin.
     *
     * @param file
     *            The {@link File} that mai contains the plugin meta.
     * @param update
     *            Ff <code>true</code>, the plugin meta will be updated (read
     *            from the plugin jar) and added to the plugin meta cache. If
     *            <code>false</code>, the plugin meta is read from the plugin
     *            meta cache (if the cache does not contain the plugin meta, the
     *            plugin meta is read from the jar file and added to the cache).
     * @return The parsed {@link PluginMeta}.
     * @throws IOException
     *             is thrown if
     *             <ol>
     *             <li>the {@link JarFile} constructor throws it.</li>
     *             <li>{@link JarFile#close()} throws it.</li>
     *             <li>{@link TSS#save()} throws it.</li>
     *             <li>the plugin meta file cannot be found.</li>
     *             </ol>
     */
    PluginMeta getMeta(final File file, final boolean update) throws IOException {
        final PluginMeta pluginMeta;
        if (update) {
            final JarFile jarFile = new JarFile(file);

            final ZipEntry pluginMetaEntry = jarFile.getEntry("plugin-meta");
            if (pluginMetaEntry == null) {
                jarFile.close();
                throw new PluginIOException("The plugin meta file (\"plugin-meta\") cannot be found in \"" + jarFile.getName()
                        + "\"!");
            }
            pluginMeta = new PluginMeta(file, jarFile.getInputStream(pluginMetaEntry));

            this.pluginMetaCache.setData(file.getName(), pluginMeta);
            this.pluginMetaCache.save();
        } else {
            pluginMeta = this.pluginMetaCache.getData(file.getName());

            if (pluginMeta == null) {
                PluginLoader.LOGGER.warning("Could not find plugin meta for \"" + file.getAbsolutePath()
                        + "\" in plugin meta cache! Updating plugin meta.");
                return this.getMeta(file, true);
            }

            pluginMeta.setFile(file);
        }
        return pluginMeta;
    }

    /**
     * Loads the plugin for the given plugin meta.
     *
     * <p>
     * <b> NOTE: This does not invoke {@link Plugin#onLoad()}. </b>
     * </p>
     *
     * @param meta
     *            The {@link PluginMeta} of the plugin to load.
     * @return The loaded {@link Plugin}.
     */
    synchronized Plugin load(final PluginMeta meta) {
        final String name = meta.getName();
        final String main = meta.getMain();

        this.remove(name);

        PluginClassLoader classLoader;
        try {
            classLoader = new PluginClassLoader(meta.getFile());
        } catch (final MalformedURLException cause) {
            throw new PluginException("This should NEVER happen. How have you made that???", cause);
        }

        final Class<? extends SimplePlugin> pluginClass;
        try {
            pluginClass = classLoader.findClass(main, false).asSubclass(SimplePlugin.class);
        } catch (final NullPointerException cause) {
            throw new PluginException("Cannot find main class \"" + main + "\" for plugin \"" + name + "\"!", cause);
        } catch (final ClassCastException cause) {
            throw new PluginException("The main class \"" + main + "\" from plugin \"" + name + "\" is no instance of \""
                    + SimplePlugin.class.getName() + "\"!", cause);
        }
        this.classLoaders.put(name.toLowerCase(), classLoader);

        final SimplePlugin plugin;
        try {
            plugin = pluginClass.newInstance();
        } catch (final InstantiationException cause) {
            throw new PluginException(cause);
        } catch (final IllegalAccessException cause) {
            throw new PluginException(cause);
        }
        plugin.init(meta);

        return plugin;
    }

    /**
     * Search in all stored class loaders for the given full-qualified class
     * name.
     *
     * @param name
     *            The full-qualified class name.
     * @return The {@link Class}, if found.
     * @throws ClassNotFoundException
     *             Is thrown if the class cannot be found.
     */
    Class<?> findClass(final String name) throws ClassNotFoundException {
        for (final PluginClassLoader classLoader : this.classLoaders.values()) {
            final Class<?> clazz = classLoader.findClass(name, false);
            if (clazz != null) {
                return clazz;
            }
        }
        throw new ClassNotFoundException("Class \"" + name + "\" was not found!");
    }

    /**
     *
     * @return {@link PluginLoader#INSTANCE}.
     */
    static PluginLoader getInstance() {
        return PluginLoader.INSTANCE;
    }
}
