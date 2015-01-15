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
package com.blockhaus2000.technical.scheduler;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import com.blockhaus2000.ipm.technical.scheduler.Scheduler;
import com.blockhaus2000.ipm.technical.scheduler.SimpleScheduler;
import com.blockhaus2000.ipm.technical.scheduler.task.AsynchDelayedTask;
import com.blockhaus2000.ipm.technical.scheduler.task.AsynchRepeatingTask;
import com.blockhaus2000.ipm.technical.scheduler.task.Task;

/**
 * Tests {@link SimpleScheduler}.
 *
 * <p>
 * <b> NOTE: This tests can fail sometimes if the execution environment is not
 * well synchronized or too slow and so on. </b>
 * </p>
 */
public class SimpleSchedulerTest {
    /**
     * <code>3</code>
     *
     */
    private static final int _3 = 3;
    /**
     * <code>4</code>
     *
     */
    private static final int _4 = 4;
    /**
     * <code>9</code>
     *
     */
    private static final int _90 = 90;
    /**
     * <code>10</code>
     *
     */
    private static final int _100 = 100;

    /**
     * Tests {@link SimpleScheduler}.
     *
     * @throws Exception
     *             If an error occurres.
     */
    @Test
    public void test0() throws Exception {
        final Scheduler scheduler = new SimpleScheduler();

        final AtomicInteger changeIndicator = new AtomicInteger();
        final Task task = new AsynchRepeatingTask() {
            final UUID uuid = UUID.randomUUID();

            boolean enabled = true;

            @Override
            public void run() {
                changeIndicator.incrementAndGet();
            }

            @Override
            public void disable() {
                this.enabled = false;
            }

            @Override
            public boolean isEnabled() {
                return this.enabled;
            }

            @Override
            public long getPeriod() {
                return SimpleSchedulerTest._100;
            }

            @Override
            public long getDelay() {
                return 1;
            }

            @Override
            public UUID getUUID() {
                return this.uuid;
            }
        };

        final UUID uuid = scheduler.run(task);
        scheduler.start();

        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(1, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(2, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(SimpleSchedulerTest._3, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(SimpleSchedulerTest._4, changeIndicator.get());

        scheduler.stop(uuid);

        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(SimpleSchedulerTest._4, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(SimpleSchedulerTest._4, changeIndicator.get());
    }

    /**
     * Tests {@link SimpleScheduler}.
     *
     * @throws Exception
     *             If an error occurres.
     */
    @Test
    public void test1() throws Exception {
        final Scheduler scheduler = new SimpleScheduler();
        scheduler.start();

        final AtomicInteger changeIndicator = new AtomicInteger();
        final Task task = new AsynchDelayedTask() {
            final UUID uuid = UUID.randomUUID();

            boolean enabled = true;

            @Override
            public void run() {
                changeIndicator.incrementAndGet();
            }

            @Override
            public void disable() {
                this.enabled = false;
            }

            @Override
            public boolean isEnabled() {
                return this.enabled;
            }

            @Override
            public long getDelay() {
                return SimpleSchedulerTest._90;
            }

            @Override
            public UUID getUUID() {
                return this.uuid;
            }
        };

        scheduler.run(task);

        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(1, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(1, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(1, changeIndicator.get());
        Thread.sleep(SimpleSchedulerTest._100);
        Assert.assertEquals(1, changeIndicator.get());

    }
}
