/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.commands.test;

import com.blockhaus2000.minecraft.util.command.event.CommandEvent;
import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxType;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;
import com.blockhaus2000.util.command.CommandContext;

@SuppressWarnings("javadoc")
public enum TestCase {
    TEST_CASE_1("testcase1") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && context.getArgs().size() == 0 && context.getFlags().size() == 0;
        }
    },
    TEST_CASE_2("testcase2") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof NotEnoughArgumentsCommandEvent;
        }
    },
    TEST_CASE_3("testcase3") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && ((String) context.getArgs().get(0).getData()).equals("arg0")
                    && context.getFlags().size() == 0;
        }
    },
    TEST_CASE_4("testcase4") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof TooManyArgumentsCommandEvent;
        }
    },
    TEST_CASE_5("testcase5") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && ((String) context.getArgs().get(0).getData()).equals("arg0")
                    && context.getFlags().size() == 0;
        }
    },
    TEST_CASE_6("testcase6") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && context.getArgs().size() == 0
                    && ((Boolean) context.getFlags().get('a').getData()).booleanValue() == false
                    && context.getFlags().get('b') == null && context.getFlags().get('c') == null
                    && context.getFlags().get('d') == null;
        }
    },
    TEST_CASE_7("testcase7") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && context.getArgs().size() == 0
                    && ((Boolean) context.getFlags().get('a').getData()).booleanValue() == true
                    && context.getFlags().get('b') == null && context.getFlags().get('c') == null
                    && context.getFlags().get('d') == null;
        }
    },
    TEST_CASE_8("testcase8") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof IllegalSyntaxCommandEvent
                    && ((IllegalSyntaxCommandEvent) event).getIllegalSyntaxType() == IllegalSyntaxType.UNAVAILABLE_FLAG_VALUE;
        }
    },
    TEST_CASE_9("testcase9") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && context.getArgs().size() == 0
                    && ((Boolean) context.getFlags().get('a').getData()).booleanValue() == false
                    && ((String) context.getFlags().get('b').getData()).equals("flag-b") && context.getFlags().get('c') == null
                    && context.getFlags().get('d') == null;
        }
    },
    TEST_CASE_10("testcase10") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && context.getArgs().size() == 0
                    && ((Boolean) context.getFlags().get('a').getData()).booleanValue() == false
                    && context.getFlags().get('b') == null
                    && ((Integer) context.getFlags().get('c').getData()).intValue() == 123456
                    && context.getFlags().get('d') == null;
        }
    },
    TEST_CASE_11("testcase11") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof IllegalSyntaxCommandEvent
                    && ((IllegalSyntaxCommandEvent) event).getIllegalSyntaxType() == IllegalSyntaxType.NUMBER_SYNTAX_IS_STRING;
        }
    },
    TEST_CASE_12("testcase12") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && context.getArgs().size() == 0
                    && ((Boolean) context.getFlags().get('a').getData()).booleanValue() == false
                    && context.getFlags().get('b') == null && context.getFlags().get('c') == null
                    && ((String) context.getFlags().get('d').getData()).equals("Flag value of d");
        }
    },
    TEST_CASE_13("testcase13") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof NotEnoughArgumentsCommandEvent;
        }
    },
    TEST_CASE_14("testcase14") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof NotEnoughArgumentsCommandEvent;
        }
    },
    TEST_CASE_15("testcase15") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event instanceof IllegalSyntaxCommandEvent
                    && ((IllegalSyntaxCommandEvent) event).getIllegalSyntaxType() == IllegalSyntaxType.NUMBER_SYNTAX_IS_STRING;
        }
    },
    TEST_CASE_16("testcase16") {
        @Override
        public boolean asExpected(final CommandContext context, final CommandEvent<?> event) {
            System.out.println(context);
            return event == null && ((String) context.getArgs().get(0).getData()).equals("arg0")
                    && ((Integer) context.getArgs().get(1).getData()).intValue() == 123456
                    && ((String) context.getArgs().get(2).getData()).equals("vararg0 vararg1 vararg2");
        }
    };

    private final String command;

    private TestCase(final String command) {
        this.command = command;
    }

    public abstract boolean asExpected(final CommandContext context, final CommandEvent<?> event);

    public String getCommand() {
        return command;
    }

    public static TestCase getFromString(final String command) {
        for (TestCase targetTestCase : TestCase.values()) {
            if (targetTestCase.getCommand().equalsIgnoreCase(command)) {
                return targetTestCase;
            }
        }

        return null;
    }
}
