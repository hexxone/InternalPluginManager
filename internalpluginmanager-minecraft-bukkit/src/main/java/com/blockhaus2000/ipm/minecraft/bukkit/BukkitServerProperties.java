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
package com.blockhaus2000.ipm.minecraft.bukkit;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.Difficulty;
import com.blockhaus2000.ipm.minecraft.GameMode;
import com.blockhaus2000.ipm.minecraft.ServerProperties;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.GameModeConverter;
import com.blockhaus2000.ipm.minecraft.world.WorldType;

/**
 * The Bukkit implementation of {@link ServerProperties}.
 *
 */
public class BukkitServerProperties implements ServerProperties {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#allowFlight()
     */
    @Override
    public boolean allowFlight() {
        return Bukkit.getServer().getAllowFlight();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#allowNether()
     */
    @Override
    public boolean allowNether() {
        return Bukkit.getServer().getAllowNether();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#announcePlayerAchievements()
     */
    @Override
    public boolean announcePlayerAchievements() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#difficulty()
     */
    @Override
    public Difficulty difficulty() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#enableQuery()
     */
    @Override
    public boolean enableQuery() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#enableRcon()
     */
    @Override
    public boolean enableRcon() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#enableCommandBlock()
     */
    @Override
    public boolean enableCommandBlock() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#forceGamemode()
     */
    @Override
    public boolean forceGamemode() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#gamemode()
     */
    @Override
    public GameMode gamemode() {
        return GameModeConverter.convertToIpmGameMode(Bukkit.getServer().getDefaultGameMode());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#generateStructures()
     */
    @Override
    public boolean generateStructures() {
        return Bukkit.getServer().getGenerateStructures();
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated The name is misspelled. Please use
     *             {@link ServerProperties#generateStructures()} instead.
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#generateStructutes()
     */
    @Deprecated
    @Override
    public boolean generateStructutes() {
        return this.generateStructures();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#generatorSettings()
     */
    @Override
    public String generatorSettings() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#hardcore()
     */
    @Override
    public boolean hardcore() {
        return Bukkit.getServer().isHardcore();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#levelName()
     */
    @Override
    public String levelName() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#levelSeed()
     */
    @Override
    public String levelSeed() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#levelType()
     */
    @Override
    public WorldType levelType() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#maxBuildHeight()
     */
    @Override
    public int maxBuildHeight() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#maxPlayers()
     */
    @Override
    public int maxPlayers() {
        return Bukkit.getServer().getMaxPlayers();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#maxTickTime()
     */
    @Override
    public int maxTickTime() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#maxWorldSize()
     */
    @Override
    public int maxWorldSize() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#motd()
     */
    @Override
    public String motd() {
        return Bukkit.getServer().getMotd();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#networkCompressionTreshold()
     */
    @Override
    public int networkCompressionTreshold() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#onlineMode()
     */
    @Override
    public boolean onlineMode() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#opPermissionLevel()
     */
    @Override
    public int opPermissionLevel() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#playerIdleTimeout()
     */
    @Override
    public int playerIdleTimeout() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#pvp()
     */
    @Override
    public boolean pvp() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#queryPort()
     */
    @Override
    public int queryPort() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#rconPassword()
     */
    @Override
    public String rconPassword() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#rconPort()
     */
    @Override
    public int rconPort() {
        // TODO Auto-generated method body.
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#resourcePack()
     */
    @Override
    public String resourcePack() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#resourcePackHash()
     */
    @Override
    public String resourcePackHash() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#serverIp()
     */
    @Override
    public String serverIp() {
        return Bukkit.getServer().getIp();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#serverPort()
     */
    @Override
    public int serverPort() {
        return Bukkit.getServer().getPort();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#snooperEnabled()
     */
    @Override
    public boolean snooperEnabled() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#spawnAnimals()
     */
    @Override
    public boolean spawnAnimals() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#spawnMonsters()
     */
    @Override
    public boolean spawnMonsters() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#spawnNpcs()
     */
    @Override
    public boolean spawnNpcs() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#spawnProtection()
     */
    @Override
    public int spawnProtection() {
        return Bukkit.getServer().getSpawnRadius();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#viewDistance()
     */
    @Override
    public int viewDistance() {
        return Bukkit.getServer().getViewDistance();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.ServerProperties#whiteList()
     */
    @Override
    public boolean whiteList() {
        return Bukkit.getServer().hasWhitelist();
    }
}
