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
package com.blockhaus2000.ipm.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link Tag}.
 *
 */
public class TagTest {
    /**
     * The default {@link Tag} each test uses.
     *
     */
    private Tag<String> tag;

    /**
     * Setup before each test.
     *
     */
    @Before
    public void setup() {
        this.tag = new Tag<String>("Hello, World!");
    }

    /**
     * Tests {@link Tag#getData()}
     *
     */
    @Test
    public void testGetData() {
        Assert.assertEquals("Hello, World!", this.tag.getData());
    }

    /**
     * Tests the ctor of {@link Tag}.
     *
     */
    @Test
    public void testConstructor() {
        Assert.assertTrue(new Tag<String>().getData() == null);
    }

    /**
     * Tests {@link Tag#setData(Object)}
     *
     */
    @Test
    public void testSetData() {
        this.tag.setData("Hello, there!");
        Assert.assertEquals("Hello, there!", this.tag.getData());
    }
}
