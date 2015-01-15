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
 */
package com.blockhaus2000.ipm.technical.scheduler.task;

import java.util.UUID;

import com.blockhaus2000.ipm.technical.scheduler.Scheduler;

/**
 * The main interface of a task that can be scheduled by the {@link Scheduler}.
 * Normal tasks will be started in a new thread, but synchronized.
 *
 * <p>
 * <b> NOTE: Raw subclasses of this can NOT be scheduled by the
 * {@link Scheduler}. </b>
 * </p>
 *
 */
public interface Task {
    /**
     * The main method of a task.
     *
     */
    void run();

    /**
     * Disables this task.
     *
     * <p>
     * <b> NOTE: If this method has been callen, {@link Task#isEnabled()} MUST
     * return <code>false</code>. </b>
     * </p>
     *
     */
    void disable();

    /**
     *
     * @return Whether this task is enabled.
     */
    boolean isEnabled();

    /**
     *
     * @return The delay (in milliseconds) before {@link Task#run()} is callen
     *         (after the task start!).
     */
    long getDelay();

    /**
     *
     * @return The UUID of this task.
     */
    UUID getUUID();
}
