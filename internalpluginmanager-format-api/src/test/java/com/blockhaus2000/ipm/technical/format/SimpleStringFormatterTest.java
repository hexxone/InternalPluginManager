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
package com.blockhaus2000.ipm.technical.format;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the {@link SimpleStringFormatter}.
 *
 */
public class SimpleStringFormatterTest {
    /**
     * Tests
     * {@link SimpleStringFormatter#format(String, StringFormatMappable...)}.
     *
     */
    @Test
    public void format() {
        Assert.assertEquals("Hello, Percent-A!",
                new SimpleStringFormatter().format("Hello, :%a:!", SimpleStringFormatterTestEnum.TEST));
        Assert.assertEquals(":Hello:, Percent-A!",
                new SimpleStringFormatter().format(":Hello:, :%a:!", SimpleStringFormatterTestEnum.TEST));
        Assert.assertEquals("Hello, Percent-A! Here is Ampersand-B.", new SimpleStringFormatter().format(
                "Hello, :%a:! Here is :&b:.", SimpleStringFormatterTestEnum.TEST, SimpleStringFormatterTestEnum.TEST_2));
    }

    /**
     * An enum that provides some string format mappings for testing.
     *
     */
    private static enum SimpleStringFormatterTestEnum implements StringFormatMappable {
        /**
         * Maps <code>%a</code> to <code>Percent-A</code>.
         *
         */
        TEST {
            /**
             * {@inheritDoc}
             *
             * @see com.blockhaus2000.ipm.technical.format.StringFormatMappable#getStringFormatMapping()
             */
            @Override
            public StringFormatMapping getStringFormatMapping() {
                return new StringFormatMapping(new StringFormatMappingKey(StringFormatKeyPrefix.PERCENT_SIGN, 'a'), "Percent-A");
            }
        },
        /**
         * Maps <code>&b</code> to <code>Ampersand-B</code>.
         *
         */
        TEST_2 {
            /**
             * {@inheritDoc}
             *
             * @see com.blockhaus2000.ipm.technical.format.StringFormatMappable#getStringFormatMapping()
             */
            @Override
            public StringFormatMapping getStringFormatMapping() {
                return new StringFormatMapping(new StringFormatMappingKey(StringFormatKeyPrefix.AMPERSAND, 'b'), "Ampersand-B");
            }
        };
    }
}
