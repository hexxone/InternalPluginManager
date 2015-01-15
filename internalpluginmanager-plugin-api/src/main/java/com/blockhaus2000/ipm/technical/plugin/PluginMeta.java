/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
 */
package com.blockhaus2000.ipm.technical.plugin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.blockhaus2000.ipm.base.CollectionUtil;
import com.blockhaus2000.ipm.base.CommonStringConstants;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;

/**
 * The {@link PluginMeta} contains main information about a plugin.
 *
 */
public class PluginMeta implements Cloneable, Serializable {
    /**
     * The serial version uid.
     *
     */
    private static final long serialVersionUID = -7497694871564152979L;

    /**
     * The {@link File} that represents the plugin.
     *
     */
    private transient File file;

    // Required Parameters
    /**
     * The plugin name.
     *
     */
    private final String name;
    /**
     * The plugin version.
     *
     */
    private final String version;
    /**
     * The plugin main class (name).
     *
     */
    private final String main;

    // Optional Parameters
    /**
     * The plugin author.
     *
     */
    private final String author;
    /**
     * The plugin dependencies.
     *
     */
    private final String[] dependencies;

    /**
     * Constructor of PluginMeta.
     *
     * @param file
     *            The {@link File} that represents the plugin.
     * @param in
     *            The {@link InputStream} to parse the plugin meta from.
     */
    public PluginMeta(final File file, final InputStream in) {
        assert file != null : "File cannot be null!";
        assert in != null : "In cannot be null!";

        final Map<PluginMetaEntry, String> entries = this.parse(in);

        this.file = file;
        this.name = entries.get(PluginMetaEntry.NAME);
        this.version = entries.get(PluginMetaEntry.VERSION);
        this.main = entries.get(PluginMetaEntry.MAIN);
        this.author = entries.get(PluginMetaEntry.AUTHOR) == null ? "" : entries.get(PluginMetaEntry.AUTHOR);
        this.dependencies = entries.get(PluginMetaEntry.DEPENDENCIES) == null ? new String[0] : entries.get(
                PluginMetaEntry.DEPENDENCIES).split(", *");
    }

    /**
     * A copy constructor.
     *
     * @param meta
     *            The {@link PluginMeta} to get the information from.
     */
    private PluginMeta(final PluginMeta meta) {
        assert meta != null : "Meta cannot be null!";

        this.file = meta.file;
        this.name = meta.name;
        this.version = meta.version;
        this.main = meta.main;
        this.author = meta.author;
        this.dependencies = meta.dependencies;
    }

    /**
     * Parses the plugin meta from the given {@link InputStream}.
     *
     * @param in
     *            The {@link InputStream} to parse the plugin meta from.
     * @return The parsed plugin meta.
     */
    private Map<PluginMetaEntry, String> parse(final InputStream in) {
        assert in != null : "In cannot be null!";

        final List<String> dataLines = new ArrayList<String>();

        final Scanner scanner = new Scanner(new BufferedInputStream(in));
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine().trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                dataLines.add(line);
            }
        }
        scanner.close();

        final Map<PluginMetaEntry, String> entries = new HashMap<PluginMetaEntry, String>();
        for (final PluginMetaEntry metaEntry : PluginMetaEntry.values()) {
            final int index = CollectionUtil.indexOf("^" + metaEntry.getKey() + ".+$", dataLines);

            if (index == -1) {
                if (metaEntry.isRequired()) {
                    throw new PluginException("Missing required plugin meta entry \"" + metaEntry.getKey() + "\"!");
                }
                entries.put(metaEntry, null);
                continue;
            }
            entries.put(metaEntry, dataLines.get(index).replaceFirst(metaEntry.getKey(), CommonStringConstants.EMPTY).trim());
        }

        return entries;
    }

    /**
     *
     * @return Whether {@link PluginMeta#file} has been initilized already.
     */
    boolean isFileInitilized() {
        return this.file != null;
    }

    /**
     * Sets the file for this plugin meta.
     *
     * @param file
     *            The new {@link File}.
     */
    void setFile(final File file) {
        assert file != null : "File cannot be null!";

        if (this.file != null) {
            throw new IllegalStateException("File has been already initilized!");
        }

        this.file = file;
    }

    /**
     *
     * @return {@link PluginMeta#file}.
     */
    public File getFile() {
        return this.file;
    }

    /**
     *
     * @return {@link PluginMeta#name}.
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return {@link PluginMeta#version}.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     *
     * @return {@link PluginMeta#main}.
     */
    public String getMain() {
        return this.main;
    }

    /**
     *
     * @return {@link PluginMeta#author}.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     *
     * @return {@link PluginMeta#dependencies}.
     */
    public String[] getDependencies() {
        return this.dependencies;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#clone()
     */
    @Override
    protected PluginMeta clone() {
        return new PluginMeta(this);
    }

    /**
     * An enum that contains every information (string) that a plugin meta can
     * contain.
     *
     */
    private static enum PluginMetaEntry {
        /**
         * The plugin name.
         *
         */
        NAME(true, "name"),
        /**
         * The plugin version.
         *
         */
        VERSION(true, "version"),
        /**
         * The plugin main class (name).
         *
         */
        MAIN(true, "main"),
        /**
         * The plugin author.
         *
         */
        AUTHOR(false, "author"),
        /**
         * The plugin dependencies.
         *
         */
        DEPENDENCIES(false, "depends");

        /**
         * The required flag. If <code>true</code>, the information is required.
         *
         */
        private final boolean required;
        /**
         * The key that has to be used to indicate the information in the plugin
         * meta.
         *
         */
        private final String key;

        /**
         * Constructor of PluginMetaEntry.
         *
         * @param required
         *            The required flag.
         * @param key
         *            The entry key.
         */
        private PluginMetaEntry(final boolean required, final String key) {
            this.required = required;
            this.key = key;
        }

        /**
         *
         * @return {@link PluginMetaEntry#required}.
         */
        public boolean isRequired() {
            return this.required;
        }

        /**
         *
         * @return {@link PluginMetaEntry#key}.
         */
        public String getKey() {
            return this.key;
        }
    }
}
