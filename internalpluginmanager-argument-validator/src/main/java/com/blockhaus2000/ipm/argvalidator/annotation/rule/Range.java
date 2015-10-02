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
package com.blockhaus2000.ipm.argvalidator.annotation.rule;

/**
 * A rule to check whether a integer (<code>byte</code>, <code>short</code>,
 * <code>int</code>, <code>long</code>) is in a given range.
 *
 */
public @interface Range {
    /**
     *
     * @return The minimal value (Default: {@link Long#MIN_VALUE}).
     */
    long min() default Long.MIN_VALUE;

    /**
     *
     * @return The minimal value (Default: {@link Long#MIN_VALUE}).
     */
    long max() default Long.MAX_VALUE;
}
