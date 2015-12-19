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

/**
 * All operator levels.
 *
 */
public enum OperatorLevel {
    /**
     * Level 1.
     *
     * <p>
     * Operators with this level can
     * <ul>
     * <li>build within the protected spawn area.</li>
     * </ul>
     * </p>
     *
     */
    LEVEL_1(1),
    /**
     * Level 2.
     *
     * <p>
     * Operators with this level can
     * <ul>
     * <li>everything that Level 1 operators can</li>
     * <li>use <code>/clear</code></li>
     * <li>use <code>/difficulty</code></li>
     * <li>use <code>/effect</code></li>
     * <li>use <code>/gamemode</code></li>
     * <li>use <code>/gamerule</code></li>
     * <li>use <code>/give</code></li>
     * <li>use <code>/tp</code></li>
     * <li>edit command blocks</li>
     * </ul>
     * </p>
     *
     */
    LEVEL_2(2),
    /**
     * Level 3.
     *
     * <p>
     * Operators with this level can
     * <ul>
     * <li>everything that Level 2 operators can</li>
     * <li>use <code>/ban</code></li>
     * <li>use <code>/deop</code></li>
     * <li>use <code>/kick</code></li>
     * <li>use <code>/op</code></li>
     * </ul>
     * </p>
     *
     */
    LEVEL_3(3),
    /**
     * Level 4.
     *
     * <p>
     * Operators with this level can
     * <ul>
     * <li>everything that Level 3 operators can</li>
     * <li>use <code>/stop</code></li>
     * </ul>
     * </p>
     *
     */
    LEVEL_4(4);

    /**
     * The operator level.
     *
     */
    private final int level;

    /**
     * Constructor of OperatorLevel.
     *
     * @param level
     *            The operator level.
     */
    private OperatorLevel(final int level) {
        this.level = level;
    }

    /**
     *
     * @return {@link OperatorLevel#level}
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Searchs for the {@link OperatorLevel} with the given level.
     *
     * @param level
     *            The level to search for.
     * @return The {@link OperatorLevel} associated with the given level. If not
     *         found, <code>null</code>.
     */
    public static OperatorLevel getByLevel(final int level) {
        for (final OperatorLevel operatorLevel : OperatorLevel.values()) {
            if (operatorLevel.getLevel() == level) {
                return operatorLevel;
            }
        }
        return null;
    }
}
