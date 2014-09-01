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
import org.junit.Test;

import com.blockhaus2000.ipm.technical.command.util.CommandContext;
import com.blockhaus2000.ipm.technical.command.util.exception.CommandException;
import com.blockhaus2000.ipm.test.mocked.MockedCommandSender;
import com.blockhaus2000.ipm.util.exception.IllegalMethodSignatureException;

/**
 * Executes some test against the {@link SimpleCommandManager} (the heart of the
 * command system).
 *
 */
public class SimpleCommandManagerTest {
    /**
     * The mocked console.
     *
     */
    private static final CommandSender MOCKED_COMMAND_SENDER = new MockedCommandSender();

    /**
     * Executes some executing tests that should not end with an exception.
     *
     */
    @Test
    public void executeSuccessful() {
        SimpleCommandManager.getInstance().register(new SimpleCommandManagerTestClass());

        Assert.assertTrue(testCommand("tc_cmd1", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER));
        Assert.assertTrue(testCommand("tc_command1", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER));
        Assert.assertTrue(testCommand("tc_cmd2", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER, "arg0", "arg1", "arg2"));
        Assert.assertTrue(testCommand("tc_cmd3", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER));
        Assert.assertTrue(testCommand("tc_cmd4", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER));
        Assert.assertTrue(testCommand("tc_cmd5", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER, "-a"));
        Assert.assertTrue(testCommand("tc_cmd6", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER, "\\-a"));
        Assert.assertTrue(testCommand("tc_cmd7", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER, "-a TEST"));
        Assert.assertTrue(testCommand("tc_cmd8", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER, "-a 1234.5678"));
        Assert.assertTrue(testCommand("tc_cmd9", SimpleCommandManagerTest.MOCKED_COMMAND_SENDER, "-a \"Hello World!\""));

        Assert.assertFalse(SimpleCommandManager.getInstance().execute("unknown_command",
                SimpleCommandManagerTest.MOCKED_COMMAND_SENDER));
    }

    @Test(expected = IllegalMethodSignatureException.class)
    public void registerIllegalMethodSignatureException1() {
        SimpleCommandManager.getInstance().register(new SimpleCommandManagerTestClass1());
    }

    @Test(expected = IllegalMethodSignatureException.class)
    public void registerIllegalMethodSignatureException2() {
        SimpleCommandManager.getInstance().register(new SimpleCommandManagerTestClass2());
    }

    @Test(expected = IllegalMethodSignatureException.class)
    public void registerIllegalMethodSignatureException3() {
        SimpleCommandManager.getInstance().register(new SimpleCommandManagerTestClass3());
    }

    /**
     * Tests the given command.
     *
     * @param label
     *            The command label to pass into the {@link CommandManager}.
     * @param sender
     *            The {@link CommandSender} to pass into the
     *            {@link CommandManager}.
     * @param args
     *            The arguments to pass into the {@link CommandManager}.
     * @return <code>true</code> if the test has successful, <code>false</code>
     *         otherwise.
     */
    private boolean testCommand(final String label, final CommandSender sender, final String... args) {
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

    /**
     * Contains the (most of the) commands that has to be executed without an
     * exception.
     *
     */
    private static class SimpleCommandManagerTestClass {
        /**
         * Tests aliases and basic execution.
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd1", "tc_command1" },
                description = "")
        public void command1(final CommandContext context) {
            throw new TestSuccessfullException();
        }

        /**
         * Tests argument passing.
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd2" },
                description = "")
        public void command2(final CommandContext context) {
            if (context.getArgs().get(0).getData().equals("arg0") && context.getArgs().get(1).getData().equals("arg1")
                    && context.getArgs().get(2).getData().equals("arg2")) {
                throw new TestSuccessfullException();
            }
        }

        /**
         * Test permission.
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd3" },
                 description = "",
                 permission = MockedCommandSender.JUNIT_TEST_PERIMSSION)
        public void command3(final CommandContext context) {
            throw new TestSuccessfullException();
        }

        /**
         * Test toggle flag (<code>false</code>).
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd4" },
                 description = "",
                 flags = { "a" })
        public void command4(final CommandContext context) {
            if (!(Boolean) context.getFlags().get('a').getData()) {
                throw new TestSuccessfullException();
            }
        }

        /**
         * Test toggle flag (<code>true</code>).
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd5" },
                 description = "",
                 flags = { "a" })
        public void command5(final CommandContext context) {
            if ((Boolean) context.getFlags().get('a').getData()) {
                throw new TestSuccessfullException();
            }
        }

        /**
         * Test flag escaping.
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd6" },
                 description = "",
                 flags = { "a" })
        public void command6(final CommandContext context) {
            if (!(Boolean) context.getFlags().get('a').getData()) {
                throw new TestSuccessfullException();
            }
        }

        /**
         * Test value flag (string).
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd7" },
                 description = "",
                 flags = { "a:string" })
        public void command7(final CommandContext context) {
            if (((String) context.getFlags().get('a').getData()).equals("TEST")) {
                throw new TestSuccessfullException();
            }
        }

        /**
         * Test value flag.
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd8" },
                 description = "",
                 flags = { "a:double" })
        public void command8(final CommandContext context) {
            if (((Double) context.getFlags().get('a').getData()).doubleValue() == 1234.5678) {
                throw new TestSuccessfullException();
            }
        }

        /**
         * Test value flag.
         *
         * @param context
         *            The {@link CommandContext}.
         */
        @Command(aliases = { "tc_cmd9" },
                 description = "",
                 flags = { "a:string_vararg" })
        public void command9(final CommandContext context) {
            if (((String) context.getFlags().get('a').getData()).equals("Hello World!")) {
                throw new TestSuccessfullException();
            }
        }
    }

    @SuppressWarnings("javadoc")
    private static class SimpleCommandManagerTestClass1 {
        /**
         * This method has an invalid argument signature - should throw an
         * exception whilest registering this class.
         *
         * @param str
         *            "Creates" the wrong signature.
         */
        @Command(aliases = { "tc1_cmd1" },
                description = "")
        public void command1(final String str) {
            throw new TestSuccessfullException();
        }
    }

    @SuppressWarnings("javadoc")
    private static class SimpleCommandManagerTestClass2 {
        /**
         * This method has not enough arguments - should throw an exception
         * whilest registering this class.
         *
         */
        @Command(aliases = { "tc2_cmd1" },
                description = "")
        public void command1() {
            throw new TestSuccessfullException();
        }
    }

    @SuppressWarnings("javadoc")
    private static class SimpleCommandManagerTestClass3 {
        /**
         * This method has too many arguments - should throw an exception
         * whilest registering this class.
         *
         * @param context
         *            The {@link CommandContext}.
         * @param context2
         *            This argument is too much.
         */
        @Command(aliases = { "tc3_cmd1" },
                description = "")
        public void command1(final CommandContext context, final CommandContext context2) {
            throw new TestSuccessfullException();
        }
    }

    /**
     * This exception will be thrown if a command test ends successful. That is
     * because of the reflection use in the {@link CommandManager}. This is the
     * safest way to test the correct execution
     *
     */
    private static class TestSuccessfullException extends RuntimeException {
        /**
         * The serial version uid.
         *
         */
        private static final long serialVersionUID = 4840109500629712996L;
    }
}
