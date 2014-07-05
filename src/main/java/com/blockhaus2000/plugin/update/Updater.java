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
package com.blockhaus2000.plugin.update;

import java.io.File;
import java.io.IOException;

import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.plugin.exception.PluginException;

/**
 * With the updater, you can update plugins in runtime.
 *
 */
public interface Updater {
    /**
     * Updates the given {@link IpmPlugin} with the given {@link File}.
     *
     * @param plugin
     *            The {@link IpmPlugin} to update.
     * @param newFile
     *            The {@link File} where the update is located.
     * @throws IOException
     *             Will be thrown if an {@link IOException} occurs.
     * @throws PluginException
     *             Will be thrown if the <code>plugin.yml</code> is invalid.
     */
    public void update(final IpmPlugin plugin, final File newFile) throws IOException, PluginException;

    /**
     * Searchs for the update file in the update folder and calls
     * {@link Updater#update(IpmPlugin, File)}.
     *
     * @param plugin
     *            The {@link IpmPlugin} to update.
     * @throws PluginException
     *             Will be thrown if {@link Updater#update(IpmPlugin, File)}
     *             throws it.
     * @throws IOException
     *             Will be thrown if {@link Updater#update(IpmPlugin, File)}
     *             throws it.
     */
    public void update(final IpmPlugin plugin) throws PluginException, IOException;

    /**
     * Checks that the new version is newer than the old version.
     *
     * @param oldVersion
     *            The old version {@link String}.
     * @param newVersion
     *            The new Version {@link String}.
     * @return <code>true</code> if the new version is newer than the old
     *         version.
     */
    public boolean hasUpdate(final String oldVersion, final String newVersion);

    /**
     * Checks that an update is available for the given {@link IpmPlugin}.
     *
     * @param plugin
     *            The {@link IpmPlugin} to check for updates.
     * @return <code>true</code> if an update is available.
     * @throws PluginException
     *             Will be thrown if the <code>plugin.yml</code> is invalid.
     */
    public boolean hasUpdate(final IpmPlugin plugin) throws PluginException;
}
