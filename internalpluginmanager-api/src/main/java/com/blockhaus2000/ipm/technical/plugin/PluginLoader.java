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
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginIOException;

public class PluginLoader {
    private static final PluginLoader INSTANCE = new PluginLoader();

    private static final Map<String, PluginClassLoader> CLASS_LOADERS = new HashMap<String, PluginClassLoader>();

    private PluginLoader() {
        // Nothing to do (only to provide singleton).
    }

    synchronized void remove(String name) throws IOException {
        name = name.toLowerCase();

        final PluginClassLoader classLoader = PluginLoader.CLASS_LOADERS.get(name);
        if (classLoader != null) {
            classLoader.clear();
            PluginLoader.CLASS_LOADERS.remove(name);
            System.gc();
        }
    }

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

    synchronized Plugin load(final PluginMeta meta) throws IOException {
        final String name = meta.getName();
        final String main = meta.getMain();

        this.remove(name);

        final PluginClassLoader classLoader = new PluginClassLoader(meta.getFile(), this, Thread.currentThread()
                .getContextClassLoader());

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

    // TODO: Reduce visibility.
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        for (final PluginClassLoader classLoader : PluginLoader.CLASS_LOADERS.values()) {
            final Class<?> clazz = classLoader.findClass(name, false);
            if (clazz != null) {
                return clazz;
            }
        }
        throw new ClassNotFoundException("Class \"" + name + "\" was not found!");
    }

    // TODO: Reduce visibility.
    public static PluginLoader getInstance() {
        return PluginLoader.INSTANCE;
    }
}
