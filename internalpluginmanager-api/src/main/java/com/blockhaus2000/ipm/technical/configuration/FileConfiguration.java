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
package com.blockhaus2000.ipm.technical.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@link FileConfiguration} is used to simplify the usage of
 * configurations. The access is easy, fast and lightweight.
 *
 */
public abstract class FileConfiguration {
    /**
     * The file where the configuration is stored.
     *
     */
    private final File file;

    /**
     * Constructor of FileConfiguration.
     *
     * @param file
     *            The file where the configuration should be stored. It is
     *            possible that the file does not exist, but not that the file
     *            is a directory.
     */
    public FileConfiguration(final File file) {
        assert !file.exists() || file.isFile() : "File has to be a file!";

        this.file = file;
    }

    /**
     * Saves the configuration cache into the config file.
     *
     */
    public abstract void save();

    /**
     * Loads the configuration cache from the config file.
     *
     */
    public abstract void load();

    /**
     * Reloads the configuration. Simple delegates to
     * {@link FileConfiguration#load()}.
     *
     */
    public void reload() {
        this.load();
    }

    // Config Getters
    /**
     *
     * @param path
     *            The "path" to search for the value.
     * @param def
     *            The default value.
     * @return The value, if found. Otherwise, the given default value.
     */
    public abstract String getString(final String path, final String def);

    /**
     * Delegates to {@link FileConfiguration#getString(String, String)} with
     * <code>path = path</code> and <code>def = null</code>.
     *
     * @param path
     *            Is passed into
     *            {@link FileConfiguration#getString(String, String)}.
     * @return See {@link FileConfiguration#getString(String, String)}.
     */
    public String getString(final String path) {
        return this.getString(path, null);
    }

    /**
     *
     * @param path
     *            The "path" to search for the value.
     * @param def
     *            The default value.
     * @return The value, if found. Otherwise, the given default value.
     */
    public abstract List<String> getStringList(final String path, final List<String> def);

    /**
     * Delegates to {@link FileConfiguration#getStringList(String, List)} with
     * <code>path = path</code> and <code>def = new ArrayList()</code>.
     *
     * @param path
     *            Is passed into
     *            {@link FileConfiguration#getStringList(String, List)}.
     * @return See {@link FileConfiguration#getStringList(String, List)}.
     */
    public List<String> getStringList(final String path) {
        return this.getStringList(path, new ArrayList<String>());
    }

    /**
     *
     * @param path
     *            The "path" to search for the value.
     * @param def
     *            The default value.
     * @return The value, if found. Otherwise, the given default value.
     */
    public abstract boolean getBoolean(final String path, final boolean def);

    /**
     * Delegates to {@link FileConfiguration#getBoolean(String, boolean)} with
     * <code>path = path</code> and <code>def = false</code>.
     *
     * @param path
     *            Is passed into
     *            {@link FileConfiguration#getBoolean(String, boolean)}.
     * @return See {@link FileConfiguration#getBoolean(String, boolean)}.
     */
    public boolean getBoolean(final String path) {
        return this.getBoolean(path, false);
    }

    /**
     *
     * @param path
     *            The "path" to search for the value.
     * @param def
     *            The default value.
     * @return The value, if found. Otherwise, the given default value.
     */
    public abstract int getInteger(final String path, final int def);

    /**
     * Delegates to {@link FileConfiguration#getInteger(String, int)} with
     * <code>path = path</code> and <code>def = -1</code>.
     *
     * @param path
     *            Is passed into
     *            {@link FileConfiguration#getInteger(String, int)}.
     * @return See {@link FileConfiguration#getInteger(String, int)}.
     */
    public int getInteger(final String path) {
        return this.getInteger(path, -1);
    }

    /**
     *
     * @param path
     *            The "path" to search for the value.
     * @param def
     *            The default value.
     * @return The value, if found. Otherwise, the given default value.
     */
    public abstract long getLong(final String path, final long def);

    /**
     * Delegates to {@link FileConfiguration#getLong(String, long)} with
     * <code>path = path</code> and <code>def = -1</code>.
     *
     * @param path
     *            Is passed into {@link FileConfiguration#getLong(String, long)}
     *            .
     * @return See {@link FileConfiguration#getLong(String, long)}.
     */
    public long getLong(final String path) {
        return this.getLong(path, -1);
    }

    /**
     *
     * @param path
     *            The "path" to search for the value.
     * @param def
     *            The default value.
     * @return The value, if found. Otherwise, the given default value.
     */
    public abstract double getDouble(final String path, final double def);

    /**
     * Delegates to {@link FileConfiguration#getDouble(String, double)} with
     * <code>path = path</code> and <code>def = -1</code>.
     *
     * @param path
     *            Is passed into
     *            {@link FileConfiguration#getDouble(String, double)}.
     * @return See {@link FileConfiguration#getDouble(String, double)}.
     */
    public double getDouble(final String path) {
        return this.getDouble(path, -1);
    }

    // Config setters
    /**
     * Sets the value of the given <code>path</code> to <code>value</code>.
     *
     * @param path
     *            The "path" to set.
     * @param value
     *            The value to set.
     */
    public abstract void set(final String path, final String value);

    /**
     * Sets the value of the given <code>path</code> to <code>value</code>.
     *
     * @param path
     *            The "path" to set.
     * @param value
     *            The value to set.
     */
    public abstract void set(final String path, final List<String> value);

    /**
     * Sets the value of the given <code>path</code> to <code>value</code>.
     *
     * @param path
     *            The "path" to set.
     * @param value
     *            The value to set.
     */
    public abstract void set(final String path, final boolean value);

    /**
     * Sets the value of the given <code>path</code> to <code>value</code>.
     *
     * @param path
     *            The "path" to set.
     * @param value
     *            The value to set.
     */
    public abstract void set(final String path, final int value);

    /**
     * Sets the value of the given <code>path</code> to <code>value</code>.
     *
     * @param path
     *            The "path" to set.
     * @param value
     *            The value to set.
     */
    public abstract void set(final String path, final long value);

    /**
     * Sets the value of the given <code>path</code> to <code>value</code>.
     *
     * @param path
     *            The "path" to set.
     * @param value
     *            The value to set.
     */
    public abstract void set(final String path, final double value);

    // Config Default Setters
    /**
     * If the given <code>path</code> is not set yet, it will be setted to
     * <code>def</code>. If it was set, nothing happens.
     *
     * @param path
     *            The "path" to set if no value was set before.
     * @param def
     *            The default value.
     */
    public abstract void add(final String path, final String def);

    /**
     * If the given <code>path</code> is not set yet, it will be setted to
     * <code>def</code>. If it was set, nothing happens.
     *
     * @param path
     *            The "path" to set if no value was set before.
     * @param def
     *            The default value.
     */
    public abstract void add(final String path, final List<String> def);

    /**
     * If the given <code>path</code> is not set yet, it will be setted to
     * <code>def</code>. If it was set, nothing happens.
     *
     * @param path
     *            The "path" to set if no value was set before.
     * @param def
     *            The default value.
     */
    public abstract void add(final String path, final boolean def);

    /**
     * If the given <code>path</code> is not set yet, it will be setted to
     * <code>def</code>. If it was set, nothing happens.
     *
     * @param path
     *            The "path" to set if no value was set before.
     * @param def
     *            The default value.
     */
    public abstract void add(final String path, final int def);

    /**
     * If the given <code>path</code> is not set yet, it will be setted to
     * <code>def</code>. If it was set, nothing happens.
     *
     * @param path
     *            The "path" to set if no value was set before.
     * @param def
     *            The default value.
     */
    public abstract void add(final String path, final long def);

    /**
     * If the given <code>path</code> is not set yet, it will be setted to
     * <code>def</code>. If it was set, nothing happens.
     *
     * @param path
     *            The "path" to set if no value was set before.
     * @param def
     *            The default value.
     */
    public abstract void add(final String path, final double def);

    // Other Config Operations
    /**
     * Removes the given <code>path</code>.
     *
     * @param path
     *            The "path" to remove.
     */
    public abstract void remove(final String path);

    /**
     * Removes all given pathes.
     *
     * @param pathes
     *            The "pathes" to remove.
     */
    public void remove(final List<String> pathes) {
        for (final String path : pathes) {
            this.remove(path);
        }
    }

    // Getters
    /**
     *
     * @return All setted values.
     */
    public abstract Map<String, String> getValues();

    /**
     *
     * @return {@link FileConfiguration#file}
     */
    public File getFile() {
        return this.file;
    }
}
