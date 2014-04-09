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
package com.blockhaus2000.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import com.blockhaus2000.util.CommandRegistrationUtil;

/**
 * This is a {@link ClassLoader} that extends the {@link URLClassLoader} to
 * provide access to classes from other {@link IpmPlugin}'s and access to the
 * classses of the jar and the main classes (like
 * {@link CommandRegistrationUtil}).
 *
 * @author Blockhaus2000
 */
public class IpmPluginClassLoader extends URLClassLoader {
    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
    private final IpmPluginLoader loader;

    /**
     * Instances a new {@link IpmPluginClassLoader} with the given arguments.
     * This will call the super construtor with {@link URL} of the given
     * {@link File} ({@link File#toURI()} and {@link URI#toURL()}).
     *
     * @param loader
     *            The {@link IpmPluginLoader} where the plugin has been loaded.
     *            This is used to provide communication between
     *            {@link IpmPlugin}s.
     * @param file
     *            The jar file of the plugin. Is used to bet the {@link URL}.
     * @param parent
     *            The parent {@link ClassLoader}. Will be used in the
     *            <code>super</code>-call.
     * @throws MalformedURLException
     *             Will be thrown if {@link URI#toURL()} throws it.
     * @see java.net.URI#toURL()
     */
    public IpmPluginClassLoader(final IpmPluginLoader loader, final File file, final ClassLoader parent)
            throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, parent);
        this.loader = loader;
    }

    /**
     * Instances a new {@link IpmPluginClassLoader} with the given arguments.
     * Will call
     * <code>this({@link IpmPluginLoader}, {@link File}, {@link ClassLoader})</code>
     * .
     *
     * @param loader
     *            Will be used in the <code>this</code>-call.
     * @param file
     *            Will be used in the <code>this</code>-call.
     * @throws MalformedURLException
     *             Will be thrown if the <code>this</code>-call throws it.
     */
    public IpmPluginClassLoader(final IpmPluginLoader loader, final File file) throws MalformedURLException {
        this(loader, file, ClassLoader.getSystemClassLoader());
    }

    /**
     * Search for the given full-qualified class name. If
     * <code>global == true</code>, this will search in all plugins for the
     * class name.
     *
     * @param name
     *            The full-qualified class name to search for.
     * @param global
     *            If <code>true</code>, this will search in all registered
     *            plugins for the given class name.
     * @return The {@link Class}, if found.
     * @throws ClassNotFoundException
     *             Will be thrown if the class can not be found.
     *
     */
    public Class<?> findClass(final String name, final boolean global) throws ClassNotFoundException {
        Class<?> clazz = null;

        if (name.startsWith("com.blockhaus2000.") || name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException ex) {
                // fails silent
            }
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
        }

        classes.put(name, clazz);

        if (clazz == null) {
            throw new ClassNotFoundException("Cannot find class \"" + name + "\"!");
        }

        return clazz;
    }

    /**
     * Searchs for the full-qualified class name. Will call
     * {@link IpmPluginClassLoader}{@link #findClass(String, boolean)} with
     * <code>global = true</code>.
     *
     * @param name
     *            The full-qualified name to search for.
     */
    @Override
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        return findClass(name, true);
    }
}
