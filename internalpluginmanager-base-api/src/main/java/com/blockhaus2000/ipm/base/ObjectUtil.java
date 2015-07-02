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

public class ObjectUtil {
    public static Object toObject(final Object o) {
        return o;
    }

    public static Object toObject(final byte x) {
        return Byte.valueOf(x);
    }

    public static Object toObject(final short x) {
        return Short.valueOf(x);
    }

    public static Object toObject(final int x) {
        return Integer.valueOf(x);
    }

    public static Object toObject(final long x) {
        return Long.valueOf(x);
    }

    public static Object toObject(final float x) {
        return Float.valueOf(x);
    }

    public static Object toObject(final double x) {
        return Double.valueOf(x);
    }

    public static Object toObject(final boolean x) {
        return Boolean.valueOf(x);
    }

    public static Object toObject(final char x) {
        return Character.valueOf(x);
    }
}
