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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;
import com.blockhaus2000.ipm.util.CollectionUtil;
import com.blockhaus2000.ipm.util.CommonStringConstants;

public class PluginMeta implements Cloneable {
    private final File file;

    // Required Parameters
    private final String name;
    private final String version;
    private final String main;

    // Optional Parameters
    private final String author;
    private final String[] dependencies;

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

    private PluginMeta(final PluginMeta meta) {
        this.file = meta.file;
        this.name = meta.name;
        this.version = meta.version;
        this.main = meta.main;
        this.author = meta.author;
        this.dependencies = meta.dependencies;
    }

    private Map<PluginMetaEntry, String> parse(final InputStream in) {
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

    private static enum PluginMetaEntry {
        NAME(true, "name"),
        VERSION(true, "version"),
        MAIN(true, "main"),
        AUTHOR(false, "author"),
        DEPENDENCIES(false, "depends");

        private final boolean required;
        private final String key;

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
