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
package com.blockhaus2000.ipm.technical.command.event;

import org.junit.Assert;
import org.junit.Test;

import com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType;
import com.blockhaus2000.ipm.test.mocked.MockedRawCommandContext;

/**
 * Tests against the {@link CommandEventManager}.
 *
 */
public class CommandEventManagerTest {
    /**
     * Runs the tests against the {@link CommandEventManager}.
     *
     */
    @Test
    public void test() {
        CommandEventManager.getInstance().register(new CommandEventManagerTestClass1());
        Assert.assertTrue(this.testEvent(new CommandEvent(CommandEventManagerTestClass1.getCommandEventData())));
        Assert.assertFalse(this.testEvent(new CommandEvent(CommandEventManagerTestClass2.getCommandEventData())));

        CommandEventManager.getInstance().register(new CommandEventManagerTestClass2());
        CommandEventManager.getInstance().unregister(new CommandEventManagerTestClass1());
        Assert.assertFalse(this.testEvent(new CommandEvent(CommandEventManagerTestClass1.getCommandEventData())));
        Assert.assertTrue(this.testEvent(new CommandEvent(CommandEventManagerTestClass2.getCommandEventData())));

        CommandEventManager.getInstance().unregister(CommandEventManagerTestClass2.class);
        Assert.assertFalse(this.testEvent(new CommandEvent(CommandEventManagerTestClass2.getCommandEventData())));
    }

    /**
     * Tests an event.
     *
     * @param event
     *            The event to test.
     * @return <code>true</code> if the test was successful, <code>false</code>
     *         otherwise.
     */
    private boolean testEvent(final CommandEvent event) {
        try {
            CommandEventManager.getInstance().fire(event);
        } catch (final TestSuccessfulException ex) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("javadoc")
    private static class CommandEventManagerTestClass1 implements CommandEventListener {
        /**
         * {@inheritDoc}
         *
         * @see com.blockhaus2000.ipm.technical.command.event.CommandEventListener#onCommandEvent(com.blockhaus2000.ipm.technical.command.event.CommandEvent)
         */
        @Override
        public void onCommandEvent(final CommandEvent commandEvent) {
            final CommandEventData eventData = commandEvent.getCommandEventData().get(0);
            if (eventData.getCommandInfo().getCommandAnot() != null) {
                return;
            }
            if (eventData.getEventType() == CommandEventType.NO_PERMISSION) {
                throw new TestSuccessfulException();
            }
        }

        private static CommandEventData getCommandEventData() {
            return new CommandEventData(new MockedRawCommandContext(), CommandEventType.NO_PERMISSION);
        }
    }

    @SuppressWarnings("javadoc")
    private static class CommandEventManagerTestClass2 implements CommandEventListener {
        /**
         * {@inheritDoc}
         *
         * @see com.blockhaus2000.ipm.technical.command.event.CommandEventListener#onCommandEvent(com.blockhaus2000.ipm.technical.command.event.CommandEvent)
         */
        @Override
        public void onCommandEvent(final CommandEvent commandEvent) {
            final CommandEventData eventData = commandEvent.getCommandEventData().get(0);
            if (eventData.getCommandInfo().getCommandAnot() != null) {
                return;
            }
            if (eventData.getEventType() == CommandEventType.NOT_ENOUGH_ARGUMENTS) {
                throw new TestSuccessfulException();
            }
        }

        private static CommandEventData getCommandEventData() {
            return new CommandEventData(new MockedRawCommandContext(), CommandEventType.NOT_ENOUGH_ARGUMENTS);
        }
    }

    /**
     * This exception is thrown if an event was successfully catched. This is
     * the easiest and safest way to test it.
     *
     */
    private static class TestSuccessfulException extends RuntimeException {
        /**
         * The serial version uid.
         *
         */
        private static final long serialVersionUID = -764211526106171254L;
    }
}
