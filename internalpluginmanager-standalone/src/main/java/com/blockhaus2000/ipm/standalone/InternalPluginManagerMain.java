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
package com.blockhaus2000.ipm.standalone;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import com.blockhaus2000.ipm.technical.command.Command;
import com.blockhaus2000.ipm.technical.command.CommandSender;
import com.blockhaus2000.ipm.technical.command.SimpleCommandManager;
import com.blockhaus2000.ipm.technical.command.event.CommandEvent;
import com.blockhaus2000.ipm.technical.command.event.CommandEventData;
import com.blockhaus2000.ipm.technical.command.event.CommandEventListener;
import com.blockhaus2000.ipm.technical.command.event.CommandEventManager;
import com.blockhaus2000.ipm.technical.command.util.CommandContext;
import com.blockhaus2000.ipm.technical.plugin.SimplePluginManager;

public class InternalPluginManagerMain implements CommandEventListener {
    private static final String MAIN_COMMAND = "internalpluginmanager";
    private static final String MAIN_COMMAND_ALIAS = "ipm";

    public static void main(final String[] dummy) {
        SimplePluginManager.getInstance().start(new File("plugins"));

        CommandEventManager.getInstance().register(new InternalPluginManagerMain());
        SimpleCommandManager.getInstance().register(InternalPluginManagerMain.class);

        final Scanner scanner = new Scanner(System.in);
        while (true) {
            final String[] args = scanner.nextLine().split(" +");
            SimpleCommandManager.getInstance().execute(args[0], new CommandSender() {
                @Override
                public boolean hasPermission(final String permission) {
                    return true;
                }
            }, args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);
        }
    }

    @Command(aliases = { InternalPluginManagerMain.MAIN_COMMAND, InternalPluginManagerMain.MAIN_COMMAND_ALIAS },
            secondLevelCommand = "stop")
    public static void internalpluginmanagerCommand(@SuppressWarnings("unused") final CommandContext context) {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.event.CommandEventListener#onCommandEvent(com.blockhaus2000.ipm.technical.command.event.CommandEvent)
     */
    @Override
    public void onCommandEvent(final CommandEvent commandEvent) {
        final CommandEventData commandEventData = commandEvent.getCommandEventData().get(0);
        if (Arrays.equals(commandEventData.getCommandInfo().getCommandAnot().aliases(), new String[] {
            InternalPluginManagerMain.MAIN_COMMAND, InternalPluginManagerMain.MAIN_COMMAND_ALIAS })) {
            switch (commandEventData.getEventType()) {
            case UNAVAILABLE_SECOND_LEVEL_COMMAND:
            case UNKNOWN_SECOND_LEVEL_COMMAND:
                System.out.println("Usage: <internalpluginmanager|ipm> <stop>");
                break;
            default:
                break;
            }
        }
    }
}
