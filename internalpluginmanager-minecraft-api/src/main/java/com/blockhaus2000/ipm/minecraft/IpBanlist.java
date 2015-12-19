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

import java.util.Set;

/**
 * The IP ban list for this server.
 *
 */
public interface IpBanlist {
    /**
     * Bans the given IP with the given reason.
     *
     * @param ip
     *            The IP to ban.
     * @param reason
     *            The reason why the IP should be banned.
     */
    void ban(final String ip, final String reason);

    /**
     * Unbans the given IP.
     *
     * @param ip
     *            The IP to unban.
     * @return Whether the unban was successful (not, if the IP was not banned
     *         yet).
     */
    boolean unban(final String ip);

    /**
     * Checks whether the given IP is banned.
     *
     * @param ip
     *            The IP to check for.
     * @return Whether the given IP is banned or not.
     */
    boolean isBanned(final String ip);

    /**
     * Reloads the bans from disk.
     *
     */
    void reload();

    /**
     * Saves the bans to disk.
     *
     */
    void save();

    /**
     *
     * @return A set of all banned IPs.
     */
    Set<String> getBannedIps();
}
