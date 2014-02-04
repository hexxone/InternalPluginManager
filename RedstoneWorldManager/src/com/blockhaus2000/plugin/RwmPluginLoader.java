/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.blockhaus2000.main.bukkit.Main;
import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.plugin.exception.PluginNotFoundException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class RwmPluginLoader {
    private static RwmPluginLoader instance;

    @MainPluginResource
    private Main main;

    private final String mainPluginPath;

    private RwmPluginLoader() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }

        System.out.println(main);

        mainPluginPath = main.getDataFolder().getAbsolutePath() + "\\plugins\\%pluginName%.jar";

        File mainDir = new File(mainPluginPath.replace("%pluginName%.jar", ""));
        if (!mainDir.exists()) {
            mainDir.mkdirs();
        }
    }

    public RwmPluginDescriptionFile loadPlugin(final String pluginName) throws PluginNotFoundException,
            InvalidPluginDescriptionException, PluginException, InstantiationException, IllegalAccessException, IOException {
        String pluginPath = mainPluginPath.replace("%pluginName%", pluginName);

        File file = new File(pluginPath);

        if (!file.exists()) {
            throw new PluginNotFoundException("The plugin " + pluginName + " cannot be found at " + pluginPath);
        }

        JarFile pluginJar = null;
        InputStream stream = null;

        try {
            pluginJar = new JarFile(pluginPath);

            ZipEntry pluginDescJarEntry = pluginJar.getEntry("plugin.yml");

            if (pluginDescJarEntry == null) {
                pluginJar.close();

                throw new InvalidPluginDescriptionException("The plugin.yml cannot be found in \"" + pluginPath + "\"!");
            }

            stream = pluginJar.getInputStream(pluginDescJarEntry);
        } catch (IOException ex) {
            throw new PluginException(ex);
        }

        RwmPluginDescriptionFile descFile = new RwmPluginDescriptionFile(stream, file);

        stream.close();
        pluginJar.close();

        return descFile;
    }

    public Set<RwmPluginDescriptionFile> loadAllPlugins() throws PluginNotFoundException, InvalidPluginDescriptionException,
            InstantiationException, IllegalAccessException, PluginException, IOException {
        Set<RwmPluginDescriptionFile> plugins = new HashSet<RwmPluginDescriptionFile>();

        for (File target : new File(mainPluginPath.replace("%pluginName%.jar", "")).listFiles()) {
            if (target.isDirectory()) {
                continue;
            }

            plugins.add(loadPlugin(target.getName().replace(".jar", "")));
        }

        return plugins;
    }

    public static RwmPluginLoader getInstance() {
        if (instance == null) {
            instance = new RwmPluginLoader();
        }

        return instance;
    }
}
