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

public enum OperatorLevel {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4);

    private final int level;

    private OperatorLevel(final int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public static OperatorLevel getByLevel(final int level) {
        for (final OperatorLevel operatorLevel : OperatorLevel.values()) {
            if (operatorLevel.getLevel() == level) {
                return operatorLevel;
            }
        }
        return null;
    }
}
