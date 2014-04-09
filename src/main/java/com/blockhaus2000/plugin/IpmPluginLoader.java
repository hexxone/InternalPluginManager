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
import java.util.Set;

import com.blockhaus2000.plugin.exception.PluginException;

/**
 * The plugin loader is used to load {@link IpmPlugin}s.
 *
 * @author Blockhaus2000
 */
public interface IpmPluginLoader {
    /**
     * Loads and returns an instance of the {@link IpmPlugin} that is
     * represented by the given {@link File}.
     *
     * <p>
     * <b> NOTE: Has to contains a <code>plugin.yml</code> if the root folder.
     * </b>
     * </p>
     *
     * @param file
     *            The {@link File} that contains the {@link IpmPlugin}.
     * @return An instance of the {@link IpmPlugin} that is represented by the
     *         {@link File}.
     * @throws PluginException
     *             If an error occurred (for example, if no
     *             <code>plugin.yml</code> can be found).
     */
    public IpmPlugin load(final File file) throws PluginException;

    /**
     * Will call {@link IpmPluginLoader#load(File)} with <code>file</code> as a
     * new {@link File} with the path to the plugin-jar (the suffix
     * <code>.jar</code> will be added automaticly).
     *
     * @param pluginName
     *            The name of the jar file to load.
     * @return The return of {@link IpmPluginLoader#load(File)}.
     * @throws PluginException
     *             Will be thrown if {@link IpmPluginLoader#load(File)} throws
     *             it.
     * @see com.blockhaus2000.plugin.IpmPluginLoader#load(java.io.File)
     */
    public IpmPlugin load(final String pluginName) throws PluginException;

    /**
     * Loads all plugins (represented by jar files) in the plugins folder in the
     * data folder of the root plugin (the InternalPluginManager). Will call
     * {@link IpmPluginLoader#load(File)} with all jar file that was found.
     *
     * @return A {@link Set} of instances {@link IpmPlugin}s that are
     *         represented by the jar files in the plugins folder.
     * @throws PluginException
     *             Will be thrown if {@link IpmPluginLoader#load(File)} throws
     *             it.
     * @see com.blockhaus2000.plugin.IpmPluginLoader#load(java.io.File)
     */
    public Set<IpmPlugin> loadAll() throws PluginException;

    /**
     * Searchs for a (full-qualified) class name in all
     * {@link IpmPluginClassLoader}s from all plugins.
     *
     * @param className
     *            The full-qualified class name to search for.
     * @return The class that was found. If nothing was found, this returns
     *         <code>null</code>.
     */
    public Class<?> getClassByName(final String className);

    /**
     * Search for the {@link IpmPluginClassLoader} for the given
     * {@link IpmPlugin}
     *
     * @param plugin
     *            The {@link IpmPlugin} to search for the class loader.
     * @return The {@link IpmPluginClassLoader} for the given {@link IpmPlugin}.
     */
    public IpmPluginClassLoader getClassLoader(final IpmPlugin plugin);
}
