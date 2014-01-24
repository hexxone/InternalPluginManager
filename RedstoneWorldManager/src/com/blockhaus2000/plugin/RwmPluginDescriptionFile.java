/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.ReflectionUtil;

/**
 * 
 * @author Blockhaus2000
 */
public class RwmPluginDescriptionFile {
    private final Yaml yaml = new Yaml(new SafeConstructor());

    private String name;
    private String version;
    private Class<? extends RwmPlugin> main;

    public RwmPluginDescriptionFile(final InputStream stream) throws InvalidPluginDescriptionException, PluginException,
            IOException {
        loadMap(asMap(yaml.load(stream)));
        stream.close();
    }

    @SuppressWarnings("unchecked")
    private void loadMap(final Map<?, ?> map) throws InvalidPluginDescriptionException, PluginException {
        // load name
        final String name = map.get("name").toString();

        if (name == null || !name.matches("^[A-Za-z0-9_.-]+$")) {
            throw new InvalidPluginDescriptionException("The name \"" + name
                    + "\" does not match the regx \"^[A-Za-z0-9_.-]+$\" or is not set!");
        }

        this.name = name;

        // load version
        final String version = map.get("version").toString();

        if (version == null || version.length() <= 0) {
            throw new InvalidPluginDescriptionException("The version is empty or not set!");
        }

        this.version = version;

        // load man class
        final String mainClassPath = map.get("main").toString();

        if (mainClassPath == null) {
            throw new InvalidPluginDescriptionException("The main class is not set!");
        }

        Class<?> main = null;

        try {
            main = Class.forName(mainClassPath);
        } catch (ClassNotFoundException ex) {
            throw new InvalidPluginDescriptionException("The setted main class cannot be found!", ex);
        }

        if (!ReflectionUtil.hasSuperclass(main, RwmPlugin.class)) {
            throw new PluginException("The main class \"" + main + "\" does not extends \"" + RwmPlugin.class + "\"");
        }

        this.main = (Class<? extends RwmPlugin>) main;
    }

    private Map<?, ?> asMap(final Object obj) throws InvalidPluginDescriptionException {
        if (!(obj instanceof Map)) {
            throw new InvalidPluginDescriptionException(obj + " is not correct structured!");
        }

        return (Map<?, ?>) obj;
    }

    // Getter
    public Yaml getYaml() {
        return yaml;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Class<? extends RwmPlugin> getMain() {
        return main;
    }
}
