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

public class ParameterizedConstructor<T> extends AbstractParameterized implements Parameterized {
    private final Constructor<T> ctor;

    public ParameterizedConstructor(final Constructor<T> ctor) {
        this.ctor = ctor;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.base.parameterized.Parameterized#calculateMatchPropability(java.lang.Class[])
     */
    @Override
    public int calculateMatchPropability(final Class<?>[] testParameterTypes) {
        return this.calculateMatchPropability(this.ctor.getParameterTypes(), testParameterTypes);
    }

    public Constructor<?> getCtor() {
        return this.ctor;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.ctor == null ? 0 : this.ctor.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ParameterizedConstructor)) {
            return false;
        }
        final ParameterizedConstructor other = (ParameterizedConstructor) obj;
        if (this.ctor == null) {
            if (other.ctor != null) {
                return false;
            }
        } else if (!this.ctor.equals(other.ctor)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[ctor=" + this.ctor + "]";
    }
}
