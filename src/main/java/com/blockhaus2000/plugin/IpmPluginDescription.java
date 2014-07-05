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

import java.io.InputStream;

import org.bukkit.plugin.PluginDescriptionFile;

import com.blockhaus2000.plugin.exception.PluginException;

/**
 * The plugin description file that contains the main information of a
 * {@link IpmPlugin}.
 *
 */
public interface IpmPluginDescription {
    /**
     * Loads all information from the given {@link InputStream} (An
     * {@link InputStream} from the <code>plugin.yml</code>.
     *
     * @param stream
     *            The stream that contains the informations.
     * @throws PluginException
     *             If <code>name</code>, <code>version</code> or
     *             <code>main</code> is not set.
     */
    public void load(final InputStream stream) throws PluginException;

    /**
     * Converts the target {@link IpmPluginDescription} to a Bukkit description
     * file ({@link PluginDescriptionFile}).
     *
     * <p>
     * <b> NOTE: The Bukkit description file only contains the name, the version
     * and the main class name of the {@link IpmPluginDescription} file. </b>
     * </p>
     *
     * @return The converted {@link IpmPluginDescription} file.
     */
    public PluginDescriptionFile convertToBukkit();

    /**
     *
     * @return The name that was setted in the <code>plugin.yml</code>.
     */
    public String getName();

    /**
     *
     * @return The version that was setted in the <code>plugin.yml</code>.
     */
    public String getVersion();

    /**
     *
     * @return The full-qualified name of the main class that was setted in the
     *         <code>plugin.yml</code>.
     */
    public String getMain();

    /**
     *
     * @return The authors that where setted in the <code>plugin.yml</code>. Is
     *         <code>null</code> if nothing was setted.
     */
    public String[] getAuthors();

    /**
     *
     * @return The depends that where setted in the <code>plugin.yml</code>. Is
     *         <code>null</code> if nothing was setted.
     */
    public String[] getDepends();

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString();
}
