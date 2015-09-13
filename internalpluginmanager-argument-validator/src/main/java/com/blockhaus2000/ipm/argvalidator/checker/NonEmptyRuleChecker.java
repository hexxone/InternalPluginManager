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
package com.blockhaus2000.ipm.argvalidator.checker;

import java.util.Collection;
import java.util.Map;

import com.blockhaus2000.ipm.argvalidator.exception.RuleViolationException;

public class NonEmptyRuleChecker implements RuleChecker {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.argvalidator.checker.RuleChecker#check(java.lang.Object)
     */
    @Override
    public void check(final Object obj) throws RuleViolationException {
        final boolean isEmpty;
        if (obj instanceof Collection) {
            isEmpty = ((Collection<?>) obj).isEmpty();
        } else if (obj instanceof Map) {
            isEmpty = ((Map<?, ?>) obj).isEmpty();
        } else if (obj instanceof CharSequence) {
            isEmpty = ((CharSequence) obj).length() <= 0;
        } else if (obj.getClass().isArray()) {
            final Class<?> componentType = obj.getClass().getComponentType();
            if (componentType.isPrimitive()) {
                final String name = componentType.getName();
                if (name.equals("byte")) {
                    isEmpty = ((byte[]) obj).length <= 0;
                } else if (name.equals("short")) {
                    isEmpty = ((short[]) obj).length <= 0;
                } else if (name.equals("int")) {
                    isEmpty = ((int[]) obj).length <= 0;
                } else if (name.equals("long")) {
                    isEmpty = ((long[]) obj).length <= 0;
                } else if (name.equals("float")) {
                    isEmpty = ((float[]) obj).length <= 0;
                } else if (name.equals("double")) {
                    isEmpty = ((double[]) obj).length <= 0;
                } else if (name.equals("char")) {
                    isEmpty = ((char[]) obj).length <= 0;
                } else if (name.equals("boolean")) {
                    isEmpty = ((boolean[]) obj).length <= 0;
                } else {
                    throw new IllegalArgumentException("There are no more primitive types! What have you done???");
                }
            } else {
                isEmpty = ((Object[]) obj).length <= 0;
            }
        } else {
            throw new IllegalArgumentException("Unsupported class type: " + obj.getClass() + " (Supported: "
                    + Collection.class.getName() + "; " + Map.class.getName() + ")");
        }
        if (isEmpty) {
            throw new RuleViolationException(obj + " must not be empty!");
        }
    }
}
