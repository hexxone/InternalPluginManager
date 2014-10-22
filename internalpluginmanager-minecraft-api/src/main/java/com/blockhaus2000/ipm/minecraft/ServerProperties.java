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
package com.blockhaus2000.ipm.minecraft;

import java.net.URL;

import com.blockhaus2000.ipm.minecraft.world.WorldType;

public interface ServerProperties {
    boolean allowFlight();

    boolean allowNether();

    boolean announcePlayerAchievements();

    Difficulty difficulty();

    boolean enableQuery();

    boolean enableRcon();

    boolean enableCommandBlock();

    boolean forceGamemode();

    GameMode gamemode();

    boolean generateStructutes();

    String generatorSettings();

    boolean hardcore();

    String levelName();

    String levelSeed();

    WorldType levelType();

    int maxBuildHeight();

    int maxPlayers();

    String motd();

    int networkCompressionThreshold();

    boolean onlineMode();

    OperatorLevel opPermissionLevel();

    int playerIdleTimeout();

    boolean pvp();

    int queryPort();

    URL resourcePack();

    String rconPassword();

    int rconPort();

    String serverIp();

    int serverPort();

    boolean snooperEnabled();

    boolean spawnAnimals();

    boolean spawnMonsters();

    boolean spanNpcs();

    int spawnProtection();

    int viewDistance();

    boolean whiteList();
}
