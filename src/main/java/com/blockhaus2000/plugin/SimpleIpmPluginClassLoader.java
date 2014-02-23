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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPluginClassLoader extends URLClassLoader implements IpmPluginClassLoader {
    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
    private final IpmPluginLoader loader;

    public SimpleIpmPluginClassLoader(final IpmPluginLoader loader, final File file, final ClassLoader parent)
            throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, parent);

        this.loader = loader;
    }

    public SimpleIpmPluginClassLoader(final IpmPluginLoader loader, final File file) throws MalformedURLException {
        this(loader, file, ClassLoader.getSystemClassLoader());
    }

    /**
     * 
     * @throws ClassNotFoundException
     * @see com.blockhaus2000.plugin.IpmPluginClassLoader#findClass(java.lang.String,
     *      boolean)
     */
    @Override
    public Class<?> findClass(final String name, final boolean global) throws ClassNotFoundException {
        Class<?> clazz;

        if (name.startsWith("com.blockhaus2000.") || name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
            clazz = Class.forName(name);
        } else {
            clazz = classes.get(name);
        }

        if (clazz == null) {
            if (global) {
                clazz = loader.getClassByName(name);
            }

            if (clazz == null) {
                clazz = super.findClass(name);
            }

            classes.put(name, clazz);
        }

        if (clazz == null) {
            throw new ClassNotFoundException("Cannot find class \"" + name + "\"!");
        }

        return clazz;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see java.net.URLClassLoader#findClass(java.lang.String)
     * @see com.blockhaus2000.plugin.IpmPluginClassLoader#findClass(java.lang.String)
     */
    @Override
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        return findClass(name, true);
    }
}
