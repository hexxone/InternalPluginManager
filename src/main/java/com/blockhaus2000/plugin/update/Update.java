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
import java.util.jar.JarFile;

import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.plugin.IpmPluginDescription;

/**
 * This update class stores important information about an update, like.
 *
 */
public class Update {
    private final IpmPlugin oldPlugin;

    private final IpmPluginDescription newDesc;
    private final JarFile newJarFile;
    private final File newFile;

    /**
     * Instances a new Update.
     *
     * @param oldPlugin
     *            The old {@link IpmPlugin}.
     * @param newDesc
     *            The new {@link IpmPluginDescription}.
     * @param newJarFile
     *            The new {@link JarFile}.
     * @param newFile
     *            The new {@link File}.
     */
    public Update(final IpmPlugin oldPlugin, final IpmPluginDescription newDesc, final JarFile newJarFile, final File newFile) {
        this.oldPlugin = oldPlugin;
        this.newDesc = newDesc;
        this.newJarFile = newJarFile;
        this.newFile = newFile;
    }

    /**
     *
     * @return The old {@link IpmPlugin}.
     */
    public IpmPlugin getOldPlugin() {
        return oldPlugin;
    }

    /**
     *
     * @return The new {@link IpmPluginDescription}.
     */
    public IpmPluginDescription getNewDescription() {
        return newDesc;
    }

    /**
     *
     * @return The new {@link JarFile}.
     */
    public JarFile getNewJarFile() {
        return newJarFile;
    }

    /**
     *
     * @return The new {@link File}.
     */
    public File getNewFile() {
        return newFile;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[oldPlugin=" + oldPlugin + ", newDesc=" + newDesc + "]";
    }
}
