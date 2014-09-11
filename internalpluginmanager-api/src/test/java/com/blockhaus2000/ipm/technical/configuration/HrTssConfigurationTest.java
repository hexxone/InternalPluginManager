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
package com.blockhaus2000.ipm.technical.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the {@link HrTssConfiguration}.
 *
 */
public class HrTssConfigurationTest {
    /**
     * Tests the {@link HrTssConfiguration}.
     *
     */
    @Test
    public void test() {
        final File dir = new File("target/dist");
        dir.mkdirs();
        final File file = new File(dir, "test.hrtss");

        final String p0 = "test.one";
        final String p1 = "test.two";
        final String p2 = "test.three";
        final String p3 = "test.four";
        final String p4 = "test.five";

        final String d0 = "Test String 1";
        final int d1 = 1234;
        final long d2 = 12345678987654321L;
        final double d3 = 1234.5678;
        final List<String> d4 = new ArrayList<String>(Arrays.asList("e0", "e1.1;e1.2", "e2", "e3", "e4", "e5"));

        final FileConfiguration configWrite;
        try {
            configWrite = new HrTssConfiguration(file);
        } catch (final IOException cause) {
            throw new RuntimeException(cause);
        }
        configWrite.set(p0, d0);
        configWrite.set(p1, d1);
        configWrite.set(p2, d2);
        configWrite.set(p3, d3);
        configWrite.set(p4, d4);

        configWrite.save();

        final FileConfiguration configRead;
        try {
            configRead = new HrTssConfiguration(file);
        } catch (final IOException cause) {
            throw new RuntimeException(cause);
        }
        configRead.load();

        Assert.assertEquals(d0, configRead.getString(p0));
        Assert.assertEquals(d1, configRead.getInteger(p1));
        Assert.assertEquals(d2, configRead.getLong(p2));
        Assert.assertEquals(d3, configRead.getDouble(p3), 0);
        Assert.assertEquals(d4, configRead.getStringList(p4));
        Assert.assertEquals("unknown", configRead.getString("unknown.path", "unknown"));
    }
}
