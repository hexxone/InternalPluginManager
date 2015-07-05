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
package com.blockhaus2000.ipm.base;

/**
 * The {@link OperatingSystem} is used to detect the current OS where this
 * program is running on.
 *
 */
public enum OperatingSystem {
    /**
     * Windows.
     *
     */
    WINDOWS,
    /**
     * Mac.
     *
     */
    MAC,
    /**
     * Unix.
     *
     */
    UNIX,
    /**
     * Solaris.
     *
     */
    SOLARIS,
    /**
     * Any other, not supported, OS.
     *
     */
    OTHER;

    /**
     * Detects the current OS by using the OS name as returned by
     * {@link OperatingSystem#get()}.
     *
     * @return The current OS or {@link OperatingSystem#OTHER} if the OS cannot
     *         be detected or is not supported.
     */
    public static OperatingSystem detect() {
        final String os = OperatingSystem.get();
        if (os.contains("win")) {
            return WINDOWS;
        } else if (os.contains("mac")) {
            return MAC;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return UNIX;
        } else if (os.contains("sunos")) {
            return SOLARIS;
        } else {
            return OTHER;
        }
    }

    /**
     * Retrieves the name of the OS this program is currently running on (the
     * value of the system property <code>os.name</code>).
     *
     * @return The name of the OS this program is currently running on.
     */
    public static String get() {
        return System.getProperty("os.name");
    }
}
