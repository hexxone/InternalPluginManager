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

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link MapUtil}
 *
 */
public class MapUtilTest {
    /**
     * Tests {@link MapUtil#removeOthers(Map, Object)} with regular usage.
     *
     */
    @Test
    public void testRemoveOthers() {
        final Map<Integer, String> actual = new HashMap<Integer, String>();
        actual.put(1, "Test1");
        actual.put(2, "Test2");
        actual.put(3, "Test3");
        actual.put(4, "Test4");
        MapUtil.removeOthers(actual, 2);

        final Map<Integer, String> expected = new HashMap<Integer, String>();
        expected.put(2, "Test2");
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link MapUtil#removeOthers(Map, Object)} with just one element
     * available.
     *
     */
    @Test
    public void testRemoveOthersOneElement() {
        final Map<Integer, String> actual = new HashMap<Integer, String>();
        actual.put(3, "Test3");
        MapUtil.removeOthers(actual, 3);

        final Map<Integer, String> expected = new HashMap<Integer, String>();
        expected.put(3, "Test3");
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link MapUtil#removeOthers(Map, Object)} with an element that does
     * not exist.
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveOthersUnexistingElement() {
        final Map<Integer, String> actual = new HashMap<Integer, String>();
        actual.put(1, "Test1");
        actual.put(3, "Test3");
        MapUtil.removeOthers(actual, 2);
    }
}
