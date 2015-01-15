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
package com.blockhaus2000.ipm.minecraft;

import com.blockhaus2000.ipm.minecraft.world.WorldType;

/**
 * The properties of this server (located in the <code>server.properties</code>
 * file).
 *
 * <p>
 * See <a href='http://minecraft.gamepedia.com/Server.properties'>here</a> for a
 * full explanation of the <code>server.properties</code> file.
 * </p>
 */
public interface ServerProperties {
    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>allow-flight</code>
     */
    boolean allowFlight();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>allow-nether</code>
     */
    boolean allowNether();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>announce-player-achivements</code>
     */
    boolean announcePlayerAchievements();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>difficulty</code>
     */
    Difficulty difficulty();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>enable-query</code>
     */
    boolean enableQuery();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>enable-rcon</code>
     */
    boolean enableRcon();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>enable-command-block</code>
     */
    boolean enableCommandBlock();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>force-gamemode</code>
     */
    boolean forceGamemode();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>gamemode</code>
     */
    GameMode gamemode();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>generate-structures</code>
     */
    boolean generateStructutes();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>generator-settings</code>
     */
    String generatorSettings();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>hardcore</code>
     */
    boolean hardcore();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>level-name</code>
     */
    String levelName();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>level-seed</code>
     */
    String levelSeed();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>level-type</code>
     */
    WorldType levelType();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>max-build-heigth</code>
     */
    int maxBuildHeight();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>max-players</code>
     */
    int maxPlayers();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>max-tick-time</code>
     */
    int maxTickTime();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>max-world-size</code>
     */
    int maxWorldSize();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>motd</code>
     */
    String motd();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>network-compression-threshold</code>
     */
    int networkCompressionTreshold();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>online-mode</code>
     */
    boolean onlineMode();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>op-permission-level</code>
     */
    int opPermissionLevel();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>player-idle-timeout</code>
     */
    int playerIdleTimeout();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>pvp</code>
     */
    boolean pvp();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>query.port</code>
     */
    int queryPort();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>rcon-password</code>
     */
    String rconPassword();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>rcon.port</code>
     */
    int rconPort();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>resource-pack</code>
     */
    String resourcePack();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>resource-pack-hash</code>
     */
    String resourcePackHash();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>server-ip</code>
     */
    String serverIp();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>server-port</code>
     */
    int serverPort();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>snooper-enabled</code>
     */
    boolean snooperEnabled();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>spawn-animals</code>
     */
    boolean spawnAnimals();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>spawn-mosters</code>
     */
    boolean spawnMonsters();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>spawn-npcs</code>
     */
    boolean spawnNpcs();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>spawn-protection</code>
     */
    int spawnProtection();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>view-distance</code>
     */
    int viewDistance();

    /**
     * For a full explanation, see <a
     * href='http://minecraft.gamepedia.com/Server.properties'>here</a>.
     *
     * @return <code>white-list</code>
     */
    boolean whiteList();
}
