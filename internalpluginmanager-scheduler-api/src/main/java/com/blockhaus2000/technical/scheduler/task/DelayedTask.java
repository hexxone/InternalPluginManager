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
package com.blockhaus2000.technical.scheduler.task;

import com.blockhaus2000.technical.scheduler.Scheduler;

/**
 * Marks this task as a delayed task. Delayed tasks will execute
 * {@link Task#run()} after the delay time is over.
 *
 * <p>
 * <b> NOTE: Raw subclasses of this can NOT be scheduled by the
 * {@link Scheduler}. </b>
 * </p>
 *
 */
public interface DelayedTask extends Task {
    // Nothing to do.
}
