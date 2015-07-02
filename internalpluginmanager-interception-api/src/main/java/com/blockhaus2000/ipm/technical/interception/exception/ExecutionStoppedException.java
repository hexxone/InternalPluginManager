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
package com.blockhaus2000.ipm.technical.interception.exception;

import java.util.List;

public class ExecutionStoppedException extends InterceptionException {
    private static final long serialVersionUID = 7405889519590713154L;

    private final List<Exception> executionStoppingCauses;

    public ExecutionStoppedException(final List<Exception> executionStoppingCauses) {
        assert executionStoppingCauses != null && !executionStoppingCauses.isEmpty() : "ExecutionStoppingCauses must not be null or empty!";

        this.executionStoppingCauses = executionStoppingCauses;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        final StringBuilder builder = new StringBuilder("The execution was stopped because of: ");
        for (final Exception cause : this.executionStoppingCauses) {
            builder.append("{");
            builder.append(cause.getClass().getName());
            builder.append(": ");
            builder.append(cause.getLocalizedMessage());
            builder.append("},");
        }
        return builder.toString();
    }
}
