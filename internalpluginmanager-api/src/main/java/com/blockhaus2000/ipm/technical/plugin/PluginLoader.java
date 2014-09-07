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
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginIOException;

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
     * THE instance of the {@link PluginLoader}.
     *
     */
    private static final PluginLoader INSTANCE = new PluginLoader();

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
    private static final Map<String, PluginClassLoader> CLASS_LOADERS = new HashMap<String, PluginClassLoader>();

    /**
     * Constructor of PluginLoader.
     *
     */
    private PluginLoader() {
        // Nothing to do (only to provide singleton).
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
     * @param name
     *            The name of the plugin to remove from the plugin loader.
     */
    synchronized void remove(String name) {
        name = name.toLowerCase();

        final PluginClassLoader classLoader = PluginLoader.CLASS_LOADERS.get(name);
        if (classLoader != null) {
            classLoader.clear();
            PluginLoader.CLASS_LOADERS.remove(name);
            System.gc();
        }
    }

    /**
     * Parses the meta data for the given plugin.
     *
     * @param file
     *            The {@link File} that mai contains the plugin meta.
     * @return The parsed {@link PluginMeta}.
     * @throws IOException
     *             is thrown if
     *             <ol>
     *             <li>the {@link JarFile} constructor throws it.</li>
     *             <li>{@link JarFile#close()} throws it.</li>
     *             <li>the plugin meta file cannot be found.</li>
     *             </ol>
     */
    PluginMeta getMeta(final File file) throws IOException {
        final JarFile jarFile = new JarFile(file);

        final ZipEntry pluginMetaEntry = jarFile.getEntry("plugin-meta");
        if (pluginMetaEntry == null) {
            jarFile.close();
            throw new PluginIOException("The plugin meta file (\"plugin-meta\") cannot be found in \"" + jarFile.getName()
                    + "\"!");
        }
        return new PluginMeta(file, jarFile.getInputStream(pluginMetaEntry));
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
        PluginLoader.CLASS_LOADERS.put(name.toLowerCase(), classLoader);

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
        for (final PluginClassLoader classLoader : PluginLoader.CLASS_LOADERS.values()) {
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
