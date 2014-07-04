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
package com.blockhaus2000.bukkit.mock;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Blockhaus2000
 */
public class MockConsoleCommandSender implements ConsoleCommandSender {
    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.CommandSender#sendMessage(java.lang.String)
     */
    @Override
    public void sendMessage(final String message) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.CommandSender#sendMessage(java.lang.String[])
     */
    @Override
    public void sendMessage(final String[] messages) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.CommandSender#getServer()
     */
    @Override
    public Server getServer() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.CommandSender#getName()
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#isPermissionSet(java.lang.String)
     */
    @Override
    public boolean isPermissionSet(final String name) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#isPermissionSet(org.bukkit.permissions.Permission)
     */
    @Override
    public boolean isPermissionSet(final Permission perm) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String name) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#hasPermission(org.bukkit.permissions.Permission)
     */
    @Override
    public boolean hasPermission(final Permission perm) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin,
     *      java.lang.String, boolean)
     */
    @Override
    public PermissionAttachment addAttachment(final Plugin plugin, final String name, final boolean value) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin)
     */
    @Override
    public PermissionAttachment addAttachment(final Plugin plugin) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin,
     *      java.lang.String, boolean, int)
     */
    @Override
    public PermissionAttachment addAttachment(final Plugin plugin, final String name, final boolean value, final int ticks) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin,
     *      int)
     */
    @Override
    public PermissionAttachment addAttachment(final Plugin plugin, final int ticks) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#removeAttachment(org.bukkit.permissions.PermissionAttachment)
     */
    @Override
    public void removeAttachment(final PermissionAttachment attachment) {

    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#recalculatePermissions()
     */
    @Override
    public void recalculatePermissions() {

    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#getEffectivePermissions()
     */
    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.ServerOperator#isOp()
     */
    @Override
    public boolean isOp() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.ServerOperator#setOp(boolean)
     */
    @Override
    public void setOp(final boolean value) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.conversations.Conversable#isConversing()
     */
    @Override
    public boolean isConversing() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.conversations.Conversable#acceptConversationInput(java.lang.String)
     */
    @Override
    public void acceptConversationInput(final String input) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.conversations.Conversable#beginConversation(org.bukkit.conversations.Conversation)
     */
    @Override
    public boolean beginConversation(final Conversation conversation) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.conversations.Conversable#abandonConversation(org.bukkit.conversations.Conversation)
     */
    @Override
    public void abandonConversation(final Conversation conversation) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.conversations.Conversable#abandonConversation(org.bukkit.conversations.Conversation,
     *      org.bukkit.conversations.ConversationAbandonedEvent)
     */
    @Override
    public void abandonConversation(final Conversation conversation, final ConversationAbandonedEvent details) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.conversations.Conversable#sendRawMessage(java.lang.String)
     */
    @Override
    public void sendRawMessage(final String message) {
    }
}
