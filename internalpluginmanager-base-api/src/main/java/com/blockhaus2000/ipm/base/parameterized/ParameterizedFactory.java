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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class ParameterizedFactory {
    private ParameterizedFactory() {
        // Nothing to do.
    }

    public static ParameterizedMethod create(final Method method) {
        return new ParameterizedMethod(method);
    }

    public static <T> ParameterizedConstructor<T> create(final Constructor<T> ctor) {
        return new ParameterizedConstructor<T>(ctor);
    }

    public static ParameterizedMethod[] create(final Method... methods) {
        final ParameterizedMethod[] result = new ParameterizedMethod[methods.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = ParameterizedFactory.create(methods[i]);
        }
        return result;
    }

    public static ParameterizedConstructor<?>[] create(final Constructor<?>... ctors) {
        final ParameterizedConstructor<?>[] result = new ParameterizedConstructor[ctors.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = ParameterizedFactory.create(ctors[i]);
        }
        return result;
    }
}
