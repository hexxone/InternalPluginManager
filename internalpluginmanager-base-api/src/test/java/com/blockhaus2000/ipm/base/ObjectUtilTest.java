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

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link ObjectUtil}.
 *
 */
public class ObjectUtilTest {
    /**
     * Tests {@link ObjectUtil#toObject(Object)},
     * {@link ObjectUtil#toObject(byte)}, {@link ObjectUtil#toObject(short)},
     * {@link ObjectUtil#toObject(int)}, {@link ObjectUtil#toObject(long)},
     * {@link ObjectUtil#toObject(float)}, {@link ObjectUtil#toObject(double)},
     * {@link ObjectUtil#toObject(boolean)} and
     * {@link ObjectUtil#toObject(char)}.
     *
     */
    @Test
    public void toObject() {
        final Object obj = new Object();
        Assert.assertSame(obj, ObjectUtil.toObject(obj));

        Assert.assertEquals(new Byte((byte) 1234), ObjectUtil.toObject((byte) 1234));
        Assert.assertEquals(new Short((short) 1234), ObjectUtil.toObject((short) 1234));
        Assert.assertEquals(new Integer(1234), ObjectUtil.toObject(1234));
        Assert.assertEquals(new Long(1234), ObjectUtil.toObject((long) 1234));
        Assert.assertEquals(new Float(1234), ObjectUtil.toObject((float) 1234));
        Assert.assertEquals(new Double(1234), ObjectUtil.toObject((double) 1234));
        Assert.assertEquals(new Boolean(true), ObjectUtil.toObject(true));
        Assert.assertEquals(new Character((char) 1234), ObjectUtil.toObject((char) 1234));
    }
}
