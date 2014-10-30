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
package com.blockhaus2000.ipm.technical.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.blockhaus2000.ipm.technical.scheduler.task.AsynchTask;
import com.blockhaus2000.ipm.technical.scheduler.task.DelayedTask;
import com.blockhaus2000.ipm.technical.scheduler.task.RepeatingTask;
import com.blockhaus2000.ipm.technical.scheduler.task.Task;
import com.blockhaus2000.ipm.technical.scheduler.task.runnable.AsynchDelayedTaskRunnable;
import com.blockhaus2000.ipm.technical.scheduler.task.runnable.AsynchRepeatingTaskRunnable;
import com.blockhaus2000.ipm.technical.scheduler.task.runnable.DelayedTaskRunnable;
import com.blockhaus2000.ipm.technical.scheduler.task.runnable.RepeatingTaskRunnable;
import com.blockhaus2000.ipm.technical.scheduler.task.runnable.TaskRunnable;

/**
 * The default implementation of {@link Scheduler}.
 *
 */
public class SimpleScheduler implements Scheduler {
    /**
     * All registered tasks.
     *
     */
    private final Map<UUID, Task> tasks = new HashMap<UUID, Task>();

    /**
     * The current state of this scheduler.
     *
     */
    private SimpleScheduler.State state = SimpleScheduler.State.STOPPED;

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.scheduler.Scheduler#start()
     */
    @Override
    public void start() {
        this.state = SimpleScheduler.State.STARTED;

        for (final Task task : this.tasks.values()) {
            this.run(task);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.scheduler.Scheduler#stop()
     */
    @Override
    public void stop() {
        this.state = SimpleScheduler.State.STOPPED;

        for (final Task task : this.tasks.values()) {
            task.disable();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.scheduler.Scheduler#stop(java.util.UUID)
     */
    @Override
    public void stop(final UUID uuid) {
        final Task task = this.tasks.get(uuid);
        if (task == null) {
            throw new IllegalArgumentException("Cannot find tasks with UUID=" + uuid + "!");
        }

        task.disable();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.scheduler.Scheduler#run(com.blockhaus2000.ipm.technical.scheduler.task.Task)
     */
    @Override
    public UUID run(final Task task) {
        if (task instanceof DelayedTask) {
            return this.internalRun((DelayedTask) task, task instanceof AsynchTask);
        } else if (task instanceof RepeatingTask) {
            return this.internalRun((RepeatingTask) task, task instanceof AsynchTask);
        } else {
            throw new IllegalArgumentException("Only direct or indirect subclasses of " + DelayedTask.class.getName()
                    + " and/or " + RepeatingTask.class.getName() + " are allowed!");
        }
    }

    /**
     * Starts the given delayed task.
     *
     * @param task
     *            The delayed task to start.
     * @param asynch
     *            Whether to start the task regular or synchronized.
     * @return The UUID of the started task.
     */
    private UUID internalRun(final DelayedTask task, final boolean asynch) {
        final TaskRunnable<DelayedTask> taskRunnable;
        if (asynch) {
            taskRunnable = new AsynchDelayedTaskRunnable(task);
        } else {
            taskRunnable = new DelayedTaskRunnable(task);
        }
        return this.internalRun(taskRunnable);
    }

    /**
     * Starts the given repeating task.
     *
     * @param task
     *            The repeating task to start.
     * @param asynch
     *            Whether to start the task regular or synchronized.
     * @return The UUID of the started task.
     */
    private UUID internalRun(final RepeatingTask task, final boolean asynch) {
        final TaskRunnable<RepeatingTask> taskRunnable;
        if (asynch) {
            taskRunnable = new AsynchRepeatingTaskRunnable(task);
        } else {
            taskRunnable = new RepeatingTaskRunnable(task);
        }
        return this.internalRun(taskRunnable);
    }

    /**
     * Starts, if the scheduler is on state
     * {@link SimpleScheduler.State#STARTED}, a new thread or, if the scheduler
     * is not running, only registers the given task for later starting.
     *
     * @param taskRunnable
     *            The task runnable to start.
     * @return The UUID of the given task.
     */
    private UUID internalRun(final TaskRunnable<? extends Task> taskRunnable) {
        final Task task = taskRunnable.getTask();
        final UUID uuid = task.getUUID();

        this.tasks.put(uuid, task);

        if (this.state == SimpleScheduler.State.STARTED) {
            final Thread thread = new Thread(taskRunnable);
            thread.start();
        }

        return uuid;
    }

    /**
     * Possible states of the scheduler.
     *
     */
    private static enum State {
        /**
         * The started-state.
         *
         */
        STARTED,
        /**
         * The stopped-state.
         *
         */
        STOPPED;
    }
}
