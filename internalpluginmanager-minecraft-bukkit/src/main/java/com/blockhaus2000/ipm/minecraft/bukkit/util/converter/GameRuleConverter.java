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
package com.blockhaus2000.ipm.minecraft.bukkit.util.converter;

import com.blockhaus2000.ipm.minecraft.GameMode;
import com.blockhaus2000.ipm.minecraft.GameRule;

/**
 * A utility class to convert a Bukkit gamerules into an IPM {@link GameRule}s
 * and the other way.
 *
 */
public class GameRuleConverter {
    /**
     * Constructor of GameModeConverter.
     *
     */
    private GameRuleConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit gamerule into an IPM {@link GameRule}.
     *
     * @param bukkitGameRule
     *            The Bukkit gamerule to convert.
     * @return The converted IPM {@link GameRule}.
     */
    public static GameRule<?> convertToIpmGameRule(final BukkitGameRule bukkitGameRule) {
        assert bukkitGameRule != null : "BukkitGameRule cannot be null!";

        final GameRule<?> result;
        final String name = bukkitGameRule.getName();
        if (name.equalsIgnoreCase("commandBlockOutput")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("doDaylightCycle")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("doEntityDrops")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("doFireTick")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("doMobLoot")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("doMobSpawning")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("doTileDrops")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("keepInventory")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("logAdminCommands")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("mobGriefing")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("naturalRegenration")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("randomTickSpeed")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("reducedDebugInfo")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("sendCommandFeedback")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else if (name.equalsIgnoreCase("showDeathMessages")) {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        } else {
            // TODO: The IPM GameRule does not support any gamerules right now.
            result = null;
        }

        return result;
    }

    /**
     * Converts the given IPM {@link GameMode} into an Bukkit
     * {@link org.bukkit.GameMode}.
     *
     * @param ipmGameRule
     *            The IPM {@link GameRule} to convert.
     * @return The converted Bukkit gamerule.
     */
    public static BukkitGameRule convertToBukkitGameRule(final GameRule<?> ipmGameRule) {
        assert ipmGameRule != null : "IpmGameRule cannot be null!";

        return new BukkitGameRule(ipmGameRule.getName().getName(), ipmGameRule.getValue().toString());
    }

    /**
     * A BukkitGameRule represents the implementation of gamerules in Bukkit.
     * These are simple key-value associations, where everything is a String.
     *
     */
    public static class BukkitGameRule {
        /**
         * The name of this gamerule.
         *
         */
        private final String name;
        /**
         * The value of this gamerule;
         *
         */
        private final String value;

        /**
         * Constructor of BukkitGameRule.
         *
         * @param name
         *            The name of this gamerule.
         * @param value
         *            The value of this gamerule.
         */
        public BukkitGameRule(final String name, final String value) {
            assert name != null : "Name cannot be null!";
            assert value != null : "Value cannot be null!";

            this.name = name;
            this.value = value;
        }

        /**
         *
         * @return {@link BukkitGameRule#name}.
         */
        public String getName() {
            return this.name;
        }

        /**
         *
         * @return {@link BukkitGameRule#value}.
         */
        public String getValue() {
            return this.value;
        }
    }
}
