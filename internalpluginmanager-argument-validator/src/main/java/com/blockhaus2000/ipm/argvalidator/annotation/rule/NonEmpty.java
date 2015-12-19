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
package com.blockhaus2000.ipm.argvalidator.annotation.rule;

import com.blockhaus2000.ipm.argvalidator.checker.NonEmptyRuleChecker;
import com.blockhaus2000.ipm.argvalidator.checker.RuleChecker;

/**
 * A rule to check whether an object is not empty. Please lookup
 * {@link NonEmptyRuleChecker} for more information.
 *
 * @see com.blockhaus2000.ipm.argvalidator.checker.NonEmptyRuleChecker
 */
public @interface NonEmpty {
    /**
     * <p>
     * <b> NOTE: Do _not_ overwrite this or you will break the whole process!
     * </b>
     * </p>
     *
     * @return The rule checker.
     */
    Class<? extends RuleChecker> value() default NonEmptyRuleChecker.class;
}
