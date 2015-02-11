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
package com.blockhaus2000.ipm.technical.scheduler.task.runnable;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import com.blockhaus2000.ipm.technical.scheduler.task.Task;

/**
 * The base class of task runnables.
 *
 * @param <T>
 *            The specific type of task to use.
 */
public abstract class AbstractTaskRunnable<T extends Task> implements Runnable {
    /**
     * The Logger for abstract class runnables
     *
     * @deprecated The usage of this logger is deprecated. Please create your
     *             own logger via {@link LoggerFactory#getLogger(Class)}
     *             instead.
     */
    @Deprecated
    protected static final Logger LOGGER = Logger.getLogger(AbstractTaskRunnable.class.getName() + ".LOGGER");

    /**
     * The task to run.
     *
     */
    private final T task;

    /**
     * Constructor of TaskRunnable.
     *
     * @param task
     *            The task to run.
     */
    public AbstractTaskRunnable(final T task) {
        this.task = task;
    }

    /**
     *
     * @return {@link AbstractTaskRunnable#task}
     */
    public T getTask() {
        return this.task;
    }
}
