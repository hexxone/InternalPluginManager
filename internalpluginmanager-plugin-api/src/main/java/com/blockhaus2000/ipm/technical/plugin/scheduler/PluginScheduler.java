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
package com.blockhaus2000.ipm.technical.plugin.scheduler;

import java.util.UUID;

import com.blockhaus2000.ipm.technical.plugin.Plugin;
import com.blockhaus2000.ipm.technical.scheduler.Scheduler;
import com.blockhaus2000.ipm.technical.scheduler.task.Task;

public interface PluginScheduler extends Scheduler {
    public UUID run(final Plugin plugin, final Task task);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use {@link PluginScheduler#run(Plugin, Task)} instead to
     *             enable auto-disabling of tasks.
     * @see com.blockhaus2000.ipm.technical.scheduler.Scheduler#run(com.blockhaus2000.ipm.technical.scheduler.task.Task)
     * @see com.blockhaus2000.ipm.technical.plugin.scheduler.PluginScheduler#run(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      com.blockhaus2000.ipm.technical.scheduler.task.Task)
     */
    @Deprecated
    @Override
    public UUID run(Task task);
}
