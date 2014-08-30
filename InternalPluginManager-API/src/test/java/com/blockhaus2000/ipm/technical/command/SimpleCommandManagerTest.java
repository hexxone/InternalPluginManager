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
package com.blockhaus2000.ipm.technical.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blockhaus2000.ipm.minecraft.api.command.CommandSenderType;
import com.blockhaus2000.ipm.technical.command.util.CommandContext;
import com.blockhaus2000.ipm.technical.command.util.exception.CommandException;

@SuppressWarnings("javadoc")
public class SimpleCommandManagerTest {
    @Before
    public void init() {
        SimpleCommandManager.getInstance().register(new SimpleCommandManagerTestClass());
    }

    @Test
    public void testCommand1() {
        Assert.assertTrue(testCommand("command1", CommandSenderType.CONSOLE));
        Assert.assertTrue(testCommand("cmd1", CommandSenderType.CONSOLE));
    }

    @Test
    public void testCommand2() {
        Assert.assertTrue(testCommand("command2", CommandSenderType.CONSOLE, "arg0", "arg1", "arg2"));
    }

    private boolean testCommand(final String label, final CommandSenderType sender, final String... args) {
        try {
            SimpleCommandManager.getInstance().execute(label, sender, args);
        } catch (final CommandException ex) {
            if (ex.getCause() != null && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().equals(TestSuccessfullException.class)) {
                return true;
            }

            throw ex;
        }

        return false;
    }

    private static class SimpleCommandManagerTestClass {
        @Command(aliases = { "command1", "cmd1" },
                description = "")
        public void command1(@SuppressWarnings("unused") final CommandContext context) {
            throw new TestSuccessfullException();
        }

        @Command(aliases = { "command2", "cmd2" },
                 description = "")
        public void command2(final CommandContext context) {
            if (context.getArgs().get(0).getData().equals("arg0") && context.getArgs().get(1).getData().equals("arg1")
                    && context.getArgs().get(2).getData().equals("arg2")) {
                throw new TestSuccessfullException();
            }
        }
    }

    private static class TestSuccessfullException extends RuntimeException {
        private static final long serialVersionUID = 4840109500629712996L;
    }
}
