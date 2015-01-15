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
package com.blockhaus2000.test.dummy;

import org.junit.Ignore;
import org.junit.Test;

/**
 * This dummy test is used to force JUnit test output. This is useful for
 * Jenkins builds that always "think" there are tests.
 *
 * <p>
 * To force JUnit/Surefire (and JaCoCo) output, it is enough to provide one test
 * that will be skipped.
 * </p>
 *
 */
public class DummyTest {
    /**
     * The actual dummy test. Please read the Javadoc of the class to understand
     * why this test must be available and why it is skipped.
     *
     */
    @Ignore
    @Test
    public void test() {
        // Nothing to do. Please read the Javadoc of the class.
    }
}
