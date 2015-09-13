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

import com.blockhaus2000.ipm.argvalidator.exception.RuleViolationException;

public interface RuleChecker {
    /**
     * Executes the check this rule checker is for on the given object.
     *
     * <p>
     * <b> NOTE: A <code>null</code>-reference is never-ever passed to this
     * method! </b>
     * </p>
     *
     * <p>
     * <b> NOTE: If this rule is violated, an exception is thrown! </b>
     * </p>
     *
     * @param obj
     *            The object to check.
     * @throws RuleViolationException
     *             If this rule is violated.
     */
    void check(final Object obj) throws RuleViolationException;
}
