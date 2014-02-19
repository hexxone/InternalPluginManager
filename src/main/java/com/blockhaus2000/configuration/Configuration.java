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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * 
 * @author Blockhaus2000
 */
public class Configuration extends YamlConfiguration {
    @Override
    public Set<String> getKeys(boolean deep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> getValues(boolean deep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSet(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getCurrentPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public org.bukkit.configuration.Configuration getRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConfigurationSection getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object get(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object get(String path, Object def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void set(String path, Object value) {
        // TODO Auto-generated method stub

    }

    @Override
    public ConfigurationSection createSection(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConfigurationSection createSection(String path, Map<?, ?> map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getString(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getString(String path, String def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isString(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInt(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getInt(String path, int def) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isInt(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getBoolean(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getBoolean(String path, boolean def) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBoolean(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getDouble(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getDouble(String path, double def) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isDouble(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long getLong(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getLong(String path, long def) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isLong(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<?> getList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isList(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> getStringList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getIntegerList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Double> getDoubleList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Float> getFloatList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Long> getLongList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Byte> getByteList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Character> getCharacterList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Short> getShortList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<?, ?>> getMapList(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector getVector(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector getVector(String path, Vector def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isVector(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOfflinePlayer(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ItemStack getItemStack(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack getItemStack(String path, ItemStack def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isItemStack(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Color getColor(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Color getColor(String path, Color def) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isColor(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isConfigurationSection(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ConfigurationSection getDefaultSection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addDefault(String path, Object value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addDefaults(Map<String, Object> defaults) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addDefaults(org.bukkit.configuration.Configuration defaults) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setDefaults(org.bukkit.configuration.Configuration defaults) {
        // TODO Auto-generated method stub

    }

    @Override
    public org.bukkit.configuration.Configuration getDefaults() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public YamlConfigurationOptions options() {
        // TODO Auto-generated method stub
        return null;
    }
}
