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
package com.blockhaus2000.ipm.base.injection;

import org.junit.Assert;
import org.junit.Test;

import com.blockhaus2000.ipm.base.exception.IllegalStaticAccessException;
import com.blockhaus2000.ipm.base.injection.exception.NotAddedInjectionException;

/**
 * Tests the {@link InjectionManager}.
 *
 */
public class InjectionManagerTest {
    /**
     * Test the null-injection check.
     *
     */
    @Test(expected = NotAddedInjectionException.class)
    public void testInitFailureNotAdded() {
        InjectionManager.init(new InjectionManagerTestClass3());
    }

    /**
     * Test the static access check.
     *
     */
    @Test(expected = IllegalStaticAccessException.class)
    public void testInitFailureIllegalStaticAcces() {
        InjectionManager.init(InjectionManagerTestClass3.class);
    }

    /**
     * Test injection of non-static fields.
     *
     */
    @Test
    public void testInitObjectAccess() {
        final InjectionManagerTestClass1.InjectionManagerTestInterface obj = new InjectionManagerTestClass1.InjectionManagerTestInterfaceImpl();

        Assert.assertTrue(InjectionManager.addResource(obj, InjectionManagerTestClass1.InjectionManagerTestInterface.class));
        Assert.assertFalse(InjectionManager.addResource(obj, InjectionManagerTestClass1.InjectionManagerTestInterface.class));

        final InjectionManagerTestClass1 testClass = new InjectionManagerTestClass1();
        InjectionManager.init(testClass);
        Assert.assertTrue(testClass.getInjectionField() == obj);
    }

    /**
     * Test injection of static fields.
     *
     */
    @Test
    public void testInitStaticAccess() {
        final InjectionManagerTestClass2.InjectionManagerTestInterface obj = new InjectionManagerTestClass2.InjectionManagerTestInterfaceImpl();

        Assert.assertTrue(InjectionManager.addResource(obj, InjectionManagerTestClass2.InjectionManagerTestInterface.class));
        Assert.assertFalse(InjectionManager.addResource(obj, InjectionManagerTestClass2.InjectionManagerTestInterface.class));

        InjectionManager.init(InjectionManagerTestClass2.class);
        Assert.assertTrue(InjectionManagerTestClass2.getInjectionField() == obj);
    }

    @SuppressWarnings("javadoc")
    private static class InjectionManagerTestClass1 {
        @Inject
        private InjectionManagerTestInterface injectionField;

        private InjectionManagerTestInterface getInjectionField() {
            return this.injectionField;
        }

        private static interface InjectionManagerTestInterface {
            // Nothing to do.
        }

        private static class InjectionManagerTestInterfaceImpl implements InjectionManagerTestInterface {
            // Nothing to do.
        }
    }

    @SuppressWarnings("javadoc")
    private static class InjectionManagerTestClass2 {
        @Inject
        private static InjectionManagerTestInterface injectionField;

        private static InjectionManagerTestInterface getInjectionField() {
            return InjectionManagerTestClass2.injectionField;
        }

        private static interface InjectionManagerTestInterface {
            // Nothing to do.
        }

        private static class InjectionManagerTestInterfaceImpl implements InjectionManagerTestInterface {
            // Nothing to do.
        }
    }

    @SuppressWarnings("javadoc")
    private static class InjectionManagerTestClass3 {
        @Inject
        private InjectionManagerTestInterface injectionField;

        @SuppressWarnings("unused")
        private InjectionManagerTestInterface getInjectionField() {
            return this.injectionField;
        }

        private static interface InjectionManagerTestInterface {
            // Nothing to do.
        }

        @SuppressWarnings("unused")
        private static class InjectionManagerTestInterfaceImpl implements InjectionManagerTestInterface {
            // Nothing to do.
        }
    }
}
