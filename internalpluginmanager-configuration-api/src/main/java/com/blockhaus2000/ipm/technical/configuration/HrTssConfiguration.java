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
package com.blockhaus2000.ipm.technical.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.blockhaus2000.ipm.base.CommonStringConstants;
import com.blockhaus2000.tagstoragesystem.human.HRTSS;

/**
 * A {@link AbstractFileConfiguration}, based on the human-readable tss (HRTSS)
 * configuration file.
 *
 */
public class HrTssConfiguration extends AbstractFileConfiguration {
    /**
     * The String that a semicol (<code>CommonStringConstants.SEMICOLON</code>)
     * is escaped with. The semicolon is used to split elements in a serialized
     * list.
     *
     */
    public static final String ESCAPED_SEMICOLON = "&sc";

    /**
     * The raw human-readable tss configuration.
     *
     */
    private final HRTSS config;

    /**
     * Constructor of HrTssConfiguration.
     *
     * @param file
     *            The configuration file. Do not has to exist.
     * @throws IOException
     *             Is thrown if the <code>super</code> call throws it.
     */
    public HrTssConfiguration(final File file) throws IOException {
        super(file);

        this.config = new HRTSS(file);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#save()
     */
    @Override
    public synchronized void save() {
        this.config.save();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#load()
     */
    @Override
    public synchronized void load() {
        this.config.load();
    }

    // Config Getters
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getString(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public String getString(final String path, final String def) {
        assert path != null : "Path cannot be null!";

        String result = this.config.get(path);
        if (result == null || result.isEmpty()) {
            result = def;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getStringList(java.lang.String,
     *      java.util.List)
     */
    @Override
    public List<String> getStringList(final String path, final List<String> def) {
        assert path != null : "Path cannot be null!";

        final String value = this.getString(path);
        if (value == null) {
            return def;
        }

        final List<String> result = new ArrayList<String>();

        for (final String str : Arrays.asList(value.split(CommonStringConstants.SEMICOLON))) {
            result.add(str.replace(HrTssConfiguration.ESCAPED_SEMICOLON, CommonStringConstants.SEMICOLON).trim());
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getBoolean(java.lang.String,
     *      boolean)
     */
    @Override
    public boolean getBoolean(final String path, final boolean def) {
        assert path != null : "Path cannot be null!";

        return Boolean.valueOf(this.getString(path, Boolean.toString(def))).booleanValue();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getInteger(java.lang.String,
     *      int)
     */
    @Override
    public int getInteger(final String path, final int def) {
        assert path != null : "Path cannot be null!";

        return Integer.valueOf(this.getString(path, Integer.toString(def))).intValue();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getLong(java.lang.String,
     *      long)
     */
    @Override
    public long getLong(final String path, final long def) {
        assert path != null : "Path cannot be null!";

        return Long.valueOf(this.getString(path, Long.toString(def))).longValue();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getDouble(java.lang.String,
     *      double)
     */
    @Override
    public double getDouble(final String path, final double def) {
        assert path != null : "Path cannot be null!";

        return Double.valueOf(this.getString(path, Double.toString(def))).doubleValue();
    }

    // Config Setters
    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#set(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void set(final String path, final String value) {
        assert path != null : "Path cannot be null!";

        this.config.set(path, value);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#set(java.lang.String,
     *      java.util.List)
     */
    @Override
    public void set(final String path, final List<String> value) {
        assert path != null : "Path cannot be null!";

        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            if (i != 0) {
                builder.append(CommonStringConstants.SEMICOLON);
            }
            builder.append(value.get(i).trim().replace(CommonStringConstants.SEMICOLON, HrTssConfiguration.ESCAPED_SEMICOLON));
        }
        this.set(path, builder.toString());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#set(java.lang.String,
     *      boolean)
     */
    @Override
    public void set(final String path, final boolean value) {
        assert path != null : "Path cannot be null!";

        this.config.set(path, Boolean.toString(value));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#set(java.lang.String,
     *      int)
     */
    @Override
    public void set(final String path, final int value) {
        assert path != null : "Path cannot be null!";

        this.config.set(path, Integer.toString(value));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#set(java.lang.String,
     *      long)
     */
    @Override
    public void set(final String path, final long value) {
        assert path != null : "Path cannot be null!";

        this.config.set(path, Long.toString(value));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#set(java.lang.String,
     *      double)
     */
    @Override
    public void set(final String path, final double value) {
        assert path != null : "Path cannot be null!";

        this.config.set(path, Double.toString(value));
    }

    // Config Default Setters
    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#add(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void add(final String path, final String def) {
        assert path != null : "Path cannot be null!";

        if (this.getString(path) == null) {
            this.set(path, def);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#add(java.lang.String,
     *      java.util.List)
     */
    @Override
    public void add(final String path, final List<String> def) {
        assert path != null : "Path cannot be null!";

        if (this.getString(path) == null) {
            this.set(path, def);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#add(java.lang.String,
     *      boolean)
     */
    @Override
    public void add(final String path, final boolean def) {
        assert path != null : "Path cannot be null!";

        if (this.getString(path) == null) {
            this.set(path, def);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#add(java.lang.String,
     *      int)
     */
    @Override
    public void add(final String path, final int def) {
        assert path != null : "Path cannot be null!";

        if (this.getString(path) == null) {
            this.set(path, def);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#add(java.lang.String,
     *      long)
     */
    @Override
    public void add(final String path, final long def) {
        assert path != null : "Path cannot be null!";

        if (this.getString(path) == null) {
            this.set(path, def);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#add(java.lang.String,
     *      double)
     */
    @Override
    public void add(final String path, final double def) {
        assert path != null : "Path cannot be null!";

        if (this.getString(path) == null) {
            this.set(path, def);
        }
    }

    // Other Config Operations
    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is NOT thread-save!. </b>
     * </p>
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#remove(java.lang.String)
     */
    @Override
    public void remove(final String path) {
        assert path != null : "Path cannot be null!";

        this.config.remove(path);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration#getValues()
     */
    @Override
    public Map<String, String> getValues() {
        return this.config.getValues();
    }
}
