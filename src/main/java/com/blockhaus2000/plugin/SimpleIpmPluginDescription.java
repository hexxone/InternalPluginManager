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
import java.util.Arrays;
import java.util.Map;

import org.bukkit.plugin.PluginDescriptionFile;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;

/**
 * An implementation of {@link IpmPluginDescription}.
 *
 * @author Blockhaus2000
 */
public class SimpleIpmPluginDescription implements IpmPluginDescription {
    private final Yaml yaml = new Yaml(new SafeConstructor());

    private String name;
    private String version;
    private String main;

    private String[] authors;
    private String[] depends;

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#load(InputStream)
     */
    @Override
    public void load(final InputStream stream) throws PluginException {
        assert stream != null : "Stream cannot be null!";

        Map<?, ?> data = asMap(yaml.load(stream));

        // load name
        String name = data.get("name").toString();

        if (name == null || name.length() == 0 || !name.matches("^[A-Za-z0-9_.-]+$")) {
            throw new InvalidPluginDescriptionException(
                    "The name is not set, empty or does not match the regex \"^[A-Za-z0-9_.-]+$\"!");
        }

        this.name = name;

        // load version
        String version = data.get("version").toString();

        if (version == null || version.length() == 0) {
            throw new InvalidPluginDescriptionException("The version is not set or empty!");
        }

        this.version = version;

        // load main
        String main = data.get("main").toString();

        if (main == null || version.length() == 0) {
            throw new InvalidPluginDescriptionException("The main class is not set or empty!");
        }

        this.main = main;

        // load authors
        Object authorsObj = data.get("authors");
        if (authors != null) {
            String[] authors = authorsObj.toString().split(", +");
            this.authors = authors == null || authors.length == 0 ? null : authors;
        } else {
            authors = null;
        }

        // load depends
        Object dependsObj = data.get("depends");
        if (depends != null) {
            String[] depends = dependsObj.toString().split(", +");
            this.depends = depends == null || depends.length == 0 ? null : depends;
        } else {
            depends = null;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#convertToBukkit()
     */
    @Override
    public PluginDescriptionFile convertToBukkit() {
        return new PluginDescriptionFile(name, version, main);
    }

    private Map<?, ?> asMap(final Object obj) {
        assert obj instanceof Map : "Obj has to be an instance of Map!";
    return (Map<?, ?>) obj;
    }

    // Getter
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getVersion()
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getMain()
     */
    @Override
    public String getMain() {
        return main;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getAuthors()
     */
    @Override
    public String[] getAuthors() {
        return authors;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getDepends()
     */
    @Override
    public String[] getDepends() {
        return depends;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ", version=" + version + ", main=" + main + ", authors="
                + Arrays.toString(authors) + ", depends=" + Arrays.toString(depends) + "]";
    }
}
