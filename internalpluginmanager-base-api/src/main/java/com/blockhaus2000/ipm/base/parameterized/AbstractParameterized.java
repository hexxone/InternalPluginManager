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
package com.blockhaus2000.ipm.base.parameterized;

public abstract class AbstractParameterized implements Parameterized {
    protected int calculateMatchPropability(final Class<?>[] parameterTypes, final Class<?>[] testParameterTypes) {
        if (parameterTypes.length > testParameterTypes.length) {
            // If the real parameter count is higher than the parameter count to
            // test, it cannot be correct. You cannot invoke a method
            // foo(java.lang.String) by calling foo() with no parameter.

            return 0;
        }

        if (parameterTypes.length > 0) {
            // The parameter count of the test parameters is higher than the the
            // parameter count of the real parameters. This may occur when using
            // varargs (i.e. a method foo(java.lang.String...) is given, and it
            // is invoked via foo("Hello", "World!")).

            int correctParameterCount = 0;
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].isAssignableFrom(testParameterTypes[i])) {
                    correctParameterCount++;
                } else {
                    break;
                }
            }

            // Calculate matching percentage.
            return 100 / parameterTypes.length * correctParameterCount;
        }

        // The real parameter count is 0, the test parameter count is 0. The
        // methods MUST match.
        return 100;
    }
}
