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
 */
package com.blockhaus2000.ipm.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link NullsafeMap}.
 *
 */
public class NullsafeMapTest {
    /**
     * Tests {@link NullsafeMap}.
     *
     */
    @Test
    public void testUncloneable() {
        final Map<Integer, String> map0 = new NullsafeMap<Integer, String>("Hello, World!");
        Assert.assertEquals("Hello, World!", map0.get(4));
    }

    /**
     * Tests {@link NullsafeMap}.
     *
     */
    @Test
    public void testCloneable() {
        final Map<Integer, StringBuilder> map = new NullsafeMap<Integer, StringBuilder>(new StringBuilder("Hello, World!"));
        Assert.assertEquals("Hello, World!", map.get(4).toString());
        Assert.assertEquals("Hello, World!", map.get(10).toString());
        map.get(4).delete(5, 12);
        Assert.assertEquals("Hello!", map.get(4).toString());
        Assert.assertEquals("Hello!", map.get(10).toString());
    }

    /**
     * Tests {@link NullsafeMap}.
     *
     */
    @Test
    public void testNested() {
        final Map<Integer, List<String>> map = new NullsafeMap<Integer, List<String>>(new ArrayList<String>(Arrays.asList("e0",
                "e1", "e2", "e3", "e4")));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("e0", "e1", "e2", "e3", "e4")), map.get(4));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("e0", "e1", "e2", "e3", "e4")), map.get(10));
        map.get(4).remove(1);
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("e0", "e2", "e3", "e4")), map.get(4));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("e0", "e1", "e2", "e3", "e4")), map.get(10));
    }

    /**
     * Tests {@link NullsafeMap}.
     *
     */
    @Test
    public void testSelfNested() {
        final Map<Integer, Map<Integer, List<String>>> map3 = new NullsafeMap<Integer, Map<Integer, List<String>>>(
                new NullsafeMap<Integer, List<String>>(new ArrayList<String>(Arrays.asList("e0", "e1", "e2", "e3", "e4"))));
        Assert.assertEquals("e0", map3.get(4).get(6).get(0));
        Assert.assertEquals("e1", map3.get(10).get(3).get(1));
        map3.get(4).get(6).set(3, "Hello");
        map3.get(10).get(3).set(0, "Foo");
        Assert.assertEquals("Hello", map3.get(4).get(6).get(3));
        Assert.assertEquals("Foo", map3.get(10).get(3).get(0));
        Assert.assertEquals("e2", map3.get(11).get(33).get(2));
        map3.get(5).get(3).set(0, "Hello");
        map3.get(4).get(3).set(0, "World");
        Assert.assertEquals("Hello", map3.get(5).get(3).get(0));
        Assert.assertEquals("World", map3.get(4).get(3).get(0));
        Assert.assertEquals("Hello", map3.get(4).get(6).get(3));
        Assert.assertEquals("Foo", map3.get(10).get(3).get(0));
        Assert.assertEquals("e2", map3.get(11).get(33).get(2));
    }
}
