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
package com.blockhaus2000.minecraft.util.tabcompl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.command.CommandInfo;
import com.blockhaus2000.util.tabcompl.TabCompleterContext;
import com.blockhaus2000.util.tabcompl.TabCompleterInfo;

/**
 * The {@link SimpleTabCompletionManager} is an implementation of the
 * {@link TabCompletionManager}.
 *
 * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager
 */
public class SimpleTabCompletionManager implements TabCompletionManager {
    private static final TabCompletionManager instance = new SimpleTabCompletionManager();

    // We have to store the tab completers seperatly for each command and
    // second-level-command. And then, we can have multible tab completers. So,
    // the organization is like: (M = Map; L = List)
    // M<CommandName, M<SecondLevelCommandName,
    // L<TabCompleters>>
    private final Map<String, HashMap<String, ArrayList<TabCompleterInfo>>> tabCompleters = new HashMap<String, HashMap<String, ArrayList<TabCompleterInfo>>>();

    private SimpleTabCompletionManager() {
        // nothing to do here (only to provide singleton)
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public void register(final Class<?> clazz, final Object obj) {
        // Here, "meth" is a shortcut of "Method", not for "Crystal Meth" ;)
        for (Method meth : clazz.getMethods()) {
            if (!meth.isAnnotationPresent(TabCompleter.class)) {
                continue;
            }

            assert Modifier.isStatic(meth.getModifiers()) || obj != null : "The method \"" + meth
                    + "\" is non-static and the given Object is null.";
            assert meth.toString().split("\\(")[1].equals("com.blockhaus2000.util.tabcompl.TabCompleterContext)") : "The "
            + "arguments of the method \"" + meth
            + "\" are not correct. The only argument has to be \"TabCompleterContext\"";
            assert meth.getReturnType() == List.class : "The return type of the method has to be java.util.List!";

            final TabCompleter tabCompleter = meth.getAnnotation(TabCompleter.class);

            for (String rawLabel : tabCompleter.labels()) {
                final String label = rawLabel.toLowerCase().trim();

                if (label.length() == 0) {
                    continue;
                }

                if (tabCompleters.get(label) == null) {
                    tabCompleters.put(label, new HashMap<String, ArrayList<TabCompleterInfo>>());
                }

                for (String rawSecondLevelCommand : tabCompleter.secondLevelCommands()) {
                    String secondLevelCommand = rawSecondLevelCommand.toLowerCase().trim();

                    if (secondLevelCommand.length() == 0) {
                        secondLevelCommand = null;
                    }

                    if (tabCompleters.get(label).get(secondLevelCommand) == null) {
                        tabCompleters.get(label).put(secondLevelCommand, new ArrayList<TabCompleterInfo>());
                    }

                    tabCompleters.get(label).get(secondLevelCommand).add(new TabCompleterInfo(tabCompleter, clazz, obj, meth));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#register(java.lang.Class)
     */
    @Override
    public void register(final Class<?> clazz) {
        register(clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#register(java.lang.Object)
     */
    @Override
    public void register(final Object obj) {
        register(obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#performTabComplete(com.blockhaus2000.util.command.CommandInfo,
     *      CommandSender, Command, java.lang.String, java.lang.String[])
     */
    // We want to do this cast:
    // (java.util.Collection<java.lang.Object>) java.lang.Object
    @SuppressWarnings("unchecked")
    @Override
    public List<String> performTabComplete(final CommandInfo info, final CommandSender sender, final Command command,
            final String rawLabel, final String[] args) {
        final String label = rawLabel.toLowerCase().trim();

        String secondLevelCommand = info.getCommandAnot().secondLevelCommand();
        if (secondLevelCommand != null) {
            secondLevelCommand = secondLevelCommand.toLowerCase().trim();
        }

        if (tabCompleters.get(label) == null || tabCompleters.get(label).get(secondLevelCommand) == null
                && tabCompleters.get(label).get(null) == null) {
            return new ArrayList<String>();
        }

        final List<String> secLevCmds = new ArrayList<String>();
        if (secondLevelCommand != null && secondLevelCommand.length() != 0
                && tabCompleters.get(label).get(secondLevelCommand) != null) {
            secLevCmds.add(secondLevelCommand);
        }
        if (tabCompleters.get(label).get(null) != null) {
            secLevCmds.add(null);
        }

        final List<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();

        for (String secLevCmd : secLevCmds) {
            for (TabCompleterInfo tabCompleterInfo : tabCompleters.get(label).get(secLevCmd)) {
                try {
                    results.add(new ArrayList<Object>((Collection<Object>) tabCompleterInfo.getMethod().invoke(
                            tabCompleterInfo.getObj(), new TabCompleterContext(tabCompleterInfo, sender, command, label, args))));
                } catch (IllegalArgumentException ex) {
                    ExceptionHandler.handle(ex);
                } catch (IllegalAccessException ex) {
                    ExceptionHandler.handle(ex);
                } catch (InvocationTargetException cause) {
                    throw new TabCompleteException("An exception is thrown in the called method!", cause);
                }
            }
        }

        final List<String> result = new ArrayList<String>();
        for (List<Object> strLst : results) {
            for (Object obj : strLst) {
                result.add(obj.toString());
            }
        }

        return result;
    }

    /**
     *
     * @return A singleton instance of the {@link SimpleTabCompletionManager}.
     */
    public static TabCompletionManager getInstance() {
        return SimpleTabCompletionManager.instance;
    }
}
