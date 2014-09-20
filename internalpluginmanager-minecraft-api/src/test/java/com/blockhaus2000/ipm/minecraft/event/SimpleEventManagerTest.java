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
        SimpleEventManagerTest.EVENT_MANAGER.register(null, new SimpleEventManagerTestClass0());

        SimpleEventManagerTest.EVENT_MANAGER.fire(new SimpleEventManagerTestEvent0());
        Assert.assertTrue(SimpleEventManagerTestClass0.hasBeenCallen());
    }

    /**
     * The test event.
     *
     */
    private static class SimpleEventManagerTestEvent0 extends AbstractEvent {
        /**
         * The event handlers.
         *
         */
        private static final HandlerList HANDLERS = new HandlerList();

        /**
         * {@inheritDoc}
         *
         * @see com.blockhaus2000.ipm.minecraft.event.AbstractEvent#getHandlers()
         */
        @Override
        public HandlerList getHandlers() {
            return SimpleEventManagerTestEvent0.HANDLERS;
        }
    }

    /**
     * The class that contains the main test information.
     *
     */
    private static class SimpleEventManagerTestClass0 {
        /**
         * The object that is assigned to the called-identifier by default. Used
         * for test identity.
         *
         */
        private static final Object defaultObject = new Object();
        /**
         * This object identifies whether a listener was called.
         *
         */
        private static Object calledIdentifier = SimpleEventManagerTestClass0.defaultObject;

        /**
         * The listener for the test event {@link SimpleEventManagerTestEvent0}.
         *
         * @param context
         *            The event context.
         */
        @EventListener(SimpleEventManagerTestEvent0.class)
        public void onSimpleEventManagerTestEvent0(final EventContext<SimpleEventManagerTestEvent0> context) {
            if (context.getEvent().getClass().equals(SimpleEventManagerTestEvent0.class)) {
                SimpleEventManagerTestClass0.calledIdentifier = new Object();
            }
        }

        /**
         *
         * @return Whether a listener was called or not. (<code>true</code>/
         *         <code>false</code>).
         */
        private static boolean hasBeenCallen() {
            return SimpleEventManagerTestClass0.defaultObject != SimpleEventManagerTestClass0.calledIdentifier;
        }
    }
}
