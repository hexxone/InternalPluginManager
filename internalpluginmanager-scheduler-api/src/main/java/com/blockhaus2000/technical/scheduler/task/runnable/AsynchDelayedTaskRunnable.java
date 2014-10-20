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
package com.blockhaus2000.technical.scheduler.task.runnable;

import java.util.logging.Level;

import com.blockhaus2000.technical.scheduler.task.DelayedTask;

/**
 * An asynch delayed task runnable.
 *
 */
public class AsynchDelayedTaskRunnable extends TaskRunnable<DelayedTask> {
    /**
     * Constructor of AsynchDelayedTaskRunnable.
     *
     * @param task
     *            Is passed into <code>super</code>-call.
     */
    public AsynchDelayedTaskRunnable(final DelayedTask task) {
        super(task);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(this.getTask().getDelay());
            this.getTask().run();
        } catch (final Exception cause) {
            TaskRunnable.LOGGER.log(Level.SEVERE, "An error occurred whilest executing task with id=" + this.getTask().getUUID()
                    + "!", cause);
        }
    }
}
