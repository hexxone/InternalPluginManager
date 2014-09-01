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
package com.blockhaus2000.ipm.util.injection;

import org.junit.Assert;
import org.junit.Test;

import com.blockhaus2000.ipm.util.exception.IllegalStaticAccessException;
import com.blockhaus2000.ipm.util.injection.exception.NotAddedInjectionException;

@SuppressWarnings("javadoc")
public class InjectionManagerTest {
    @Test(expected = NotAddedInjectionException.class)
    public void initNotAddedInjectionException() {
        InjectionManager.init(new InjectionManagerTestClass3());
    }

    @Test(expected = IllegalStaticAccessException.class)
    public void initIllegalStaticAccessException() {
        InjectionManager.init(InjectionManagerTestClass3.class);
    }

    @Test
    public void initObjectAccess() {
        final InjectionManagerTestClass1.InjectionManagerTestInterface obj = new InjectionManagerTestClass1.InjectionManagerTestInterfaceImpl();

        Assert.assertTrue(InjectionManager.addResource(obj, InjectionManagerTestClass1.InjectionManagerTestInterface.class));
        Assert.assertFalse(InjectionManager.addResource(obj, InjectionManagerTestClass1.InjectionManagerTestInterface.class));

        final InjectionManagerTestClass1 testClass = new InjectionManagerTestClass1();
        InjectionManager.init(testClass);
        Assert.assertTrue(testClass.getInjectionField() == obj);
    }

    @Test
    public void initStaticAccess() {
        final InjectionManagerTestClass2.InjectionManagerTestInterface obj = new InjectionManagerTestClass2.InjectionManagerTestInterfaceImpl();

        Assert.assertTrue(InjectionManager.addResource(obj, InjectionManagerTestClass2.InjectionManagerTestInterface.class));
        Assert.assertFalse(InjectionManager.addResource(obj, InjectionManagerTestClass2.InjectionManagerTestInterface.class));

        InjectionManager.init(InjectionManagerTestClass2.class);
        Assert.assertTrue(InjectionManagerTestClass2.getInjectionField() == obj);
    }

    private static class InjectionManagerTestClass1 {
        @Inject
        private InjectionManagerTestInterface injectionField;

        private InjectionManagerTestInterface getInjectionField() {
            return injectionField;
        }

        private static interface InjectionManagerTestInterface {
        }

        private static class InjectionManagerTestInterfaceImpl implements InjectionManagerTestInterface {
        }
    }

    private static class InjectionManagerTestClass2 {
        @Inject
        private static InjectionManagerTestInterface injectionField;

        private static InjectionManagerTestInterface getInjectionField() {
            return InjectionManagerTestClass2.injectionField;
        }

        private static interface InjectionManagerTestInterface {
        }

        private static class InjectionManagerTestInterfaceImpl implements InjectionManagerTestInterface {
        }
    }

    private static class InjectionManagerTestClass3 {
        @Inject
        private InjectionManagerTestInterface injectionField;

        @SuppressWarnings("unused")
        private InjectionManagerTestInterface getInjectionField() {
            return injectionField;
        }

        private static interface InjectionManagerTestInterface {
        }

        @SuppressWarnings("unused")
        private static class InjectionManagerTestInterfaceImpl implements InjectionManagerTestInterface {
        }
    }
}
