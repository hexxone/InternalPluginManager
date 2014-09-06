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
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class PluginClassLoader extends URLClassLoader {
    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
    private final PluginLoader pluginLoader;

    public PluginClassLoader(final File file, final PluginLoader pluginLoader, final ClassLoader parent)
            throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, parent);

        assert pluginLoader != null : "PluginLoader cannot be null!";

        this.pluginLoader = pluginLoader;
    }

    protected Class<?> findClass(final String name, final boolean global) {
        assert name != null : "Name cannot be null!";

        Class<?> clazz = this.classes.get(name);
        if (clazz == null) {
            try {
                if (global) {
                    clazz = this.pluginLoader.findClass(name);
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

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        return this.findClass(name, true);
    }

    protected void clear() {
        this.classes.clear();
    }

    // /**
    // * {@inheritDoc}
    // *
    // * @see java.lang.Object#finalize()
    // */
    // @Override
    // protected void finalize() throws Throwable {
    // System.out.println(this.getClass().getName() + ": finalize()");
    // super.finalize();
    // }
}
