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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link URLClassLoader} to load plugins.
 *
 * <p>
 * <b> NOTE: Do NOT interact directly with this class via reflection! </b>
 * </p>
 *
 */
public final class PluginClassLoader extends URLClassLoader {
    /**
     * A cache for the already loaded classes. Speeds everything up.
     *
     */
    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

    /**
     * Constructor of PluginClassLoader.
     *
     * <p>
     * <b> NOTE: Do NOT invoke this constructor via reflection! </b>
     * </p>
     *
     * @param file
     *            The {@link File} that has to be added to the class lookup path
     *            (the classpath).
     * @throws MalformedURLException
     *             Is thrown if {@link URI#toURL()} throws it. Should never be
     *             thrown cause the {@link URI} is created by
     *             {@link File#toURI()}.
     */
    PluginClassLoader(final File file) throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Searchs for the given, full-qualified, class name.
     *
     * @param name
     *            The full-qualified class name.
     * @param global
     *            If <code>true</code>, the {@link PluginClassLoader} asks the
     *            {@link PluginLoader} to search for the class. Maked it
     *            possible to share classes over different plugins.
     * @return The {@link Class} that has been found for the given name. If it
     *         cannot be found, it returns <code>null</code>.
     */
    Class<?> findClass(final String name, final boolean global) {
        assert name != null : "Name cannot be null!";

        Class<?> clazz = this.classes.get(name);
        if (clazz == null) {
            try {
                if (global) {
                    clazz = PluginLoader.getInstance().findClass(name);
                } else {
                    // Throw Exception to go into the catch block.
                    throw new ClassNotFoundException();
                }
            } catch (final ClassNotFoundException d0) {
                try {
                    clazz = super.findClass(name);
                } catch (final ClassNotFoundException d1) {
                    try {
                        clazz = Class.forName(name);
                    } catch (final ClassNotFoundException d2) {
                        // Fails silent. Class not found.
                    }
                }
            }
        }

        if (clazz != null) {
            this.classes.put(name, clazz);
        }

        return clazz;
    }

    /**
     * {@inheritDoc}
     *
     * Delegates to {@link PluginClassLoader#findClass(String, boolean)} with
     * <code>global = true</code>.
     *
     * @see java.net.URLClassLoader#findClass(java.lang.String)
     */
    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        return this.findClass(name, true);
    }

    /**
     * Clears the plugin class loader.
     *
     */
    void clear() {
        this.classes.clear();
    }
}
