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
package com.blockhaus2000.ipm.technical.command;

/**
 * The {@link CommandPriority} is the priority for a command. If two or more
 * commands are associated with the same alias, they are executed in the
 * following order, depent on the {@link CommandPriority}:
 * <ol>
 * <li>{@link CommandPriority#LOWEST}</li>
 * <li>{@link CommandPriority#LOW}</li>
 * <li>{@link CommandPriority#NORMAL}</li>
 * <li>{@link CommandPriority#HIGH}</li>
 * <li>{@link CommandPriority#HIGHEST}</li>
 * <li>{@link CommandPriority#MONITOR}</li>
 * </ol>
 *
 * <p>
 * <b> NOTE: Only use {@link CommandPriority#MONITOR} to monitor the command. Do
 * NOT change or execute ANYTHING if you are using that priority. </b>
 * </p>
 *
 */
public enum CommandPriority {
    /**
     * Very low priority command.
     *
     */
    LOWEST(0),
    /**
     * Low priority command.
     *
     */
    LOW(10),
    /**
     * Normal priority command.
     *
     */
    NORMAL(20),
    /**
     * High priority command.
     *
     */
    HIGH(30),
    /**
     * Very high priority command.
     *
     */
    HIGHEST(40),
    /**
     * Monitoring command.
     *
     * <p>
     * <b> Do NOT change or execute ANYTHING if you are using this priority.
     * </b>
     * </p>
     *
     */
    MONITOR(50);

    /**
     * The prioritory <code>int</code>. Will be used for save sorting.
     */
    private final int priority;

    /**
     * Constructor of CommandPriority.
     *
     * @param priority
     *            The priority <code>int</code>. Will be used for save sorting.
     */
    private CommandPriority(final int priority) {
        this.priority = priority;
    }

    /**
     *
     * @return {@link CommandPriority#priority}.
     */
    public int getPriority() {
        return this.priority;
    }
}
