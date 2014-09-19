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
package com.blockhaus2000.ipm.minecraft.event;

import org.junit.Assert;
import org.junit.Test;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.mocked.MockedServer;

/**
 * Tests the {@link SimpleEventManager}.
 *
 */
public class SimpleEventManagerTest {
    /**
     * The {@link EventManager} to use.
     *
     */
    private static final EventManager EVENT_MANAGER;

    static {
        InjectionManager.addResource(new MockedServer(), Server.class);

        EVENT_MANAGER = new SimpleEventManager();
    }

    /**
     * Tests the {@link SimpleEventManager}.
     *
     */
    @Test
    public void test() {
        SimpleEventManagerTest.EVENT_MANAGER.register(new SimpleEventManagerTestClass0());
        Assert.assertTrue(this.testEventFireing(new SimpleEventManagerTestEvent0()));
    }

    private boolean testEventFireing(final Event event) {
        try {
            SimpleEventManagerTest.EVENT_MANAGER.fire(event);
        } catch (final EventException ex) {
            if (ex.getCause() == null || ex.getCause().getCause() == null
                    || !ex.getCause().getCause().getClass().equals(TestSuccessfulException.class)) {
                throw ex;
            }
            return true;
        }
        return false;
    }

    private static class SimpleEventManagerTestEvent0 extends Event {
        /**
         * The event handlers.
         *
         */
        private static final HandlerList HANDLERS = new HandlerList();

        /**
         * {@inheritDoc}
         *
         * @see com.blockhaus2000.ipm.minecraft.event.Event#getHandlers()
         */
        @Override
        public HandlerList getHandlers() {
            return SimpleEventManagerTestEvent0.HANDLERS;
        }
    }

    private static class SimpleEventManagerTestClass0 {
        @EventListener(SimpleEventManagerTestEvent0.class)
        public void onSimpleEventManagerTestEvent0(final EventContext context) {
            if (context.getEvent().getClass().equals(SimpleEventManagerTestEvent0.class)) {
                throw new TestSuccessfulException();
            }
        }
    }

    private static class TestSuccessfulException extends RuntimeException {
        private static final long serialVersionUID = -501917487315429530L;
    }
}
