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
package com.blockhaus2000.util.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blockhaus2000.minecraft.util.command.Command;

/**
 * The {@link CommandSyntax} for internal use of {@link Command#syntax()}.
 *
 */
public class CommandSyntax implements Iterable<CommandSyntaxType> {
    private final List<CommandSyntaxType> syntax;

    /**
     * Instances a new {@link CommandSyntax} with the given syntax string to
     * parse.
     *
     * @param syntaxString
     *            The syntax string to parse.
     */
    public CommandSyntax(final String syntaxString) {
        syntax = syntaxString == null || syntaxString.length() == 0 ? null : parseCommandSyntax(syntaxString);
    }

    private List<CommandSyntaxType> parseCommandSyntax(final String syntaxString) throws IllegalArgumentException {
        String[] syntax = syntaxString.split(",* +");

        List<CommandSyntaxType> syntaxClasses = new ArrayList<CommandSyntaxType>();

        for (String target : syntax) {
            CommandSyntaxType syntaxType = CommandSyntaxType.getTypeByString(target);

            if (syntaxType == null) {
                throw new IllegalArgumentException("The syntax element " + target + " is not available!");
            }

            syntaxClasses.add(syntaxType);
        }

        return syntaxClasses;
    }

    /**
     *
     * @return If this returns <code>true</code>, the syntax is valid (only the
     *         last argument is a var arg, for example).
     */
    public boolean isSyntaxValid() {
        if (syntax.size() == 0) {
            return true;
        }

        if (syntax == null) {
            return false;
        }

        for (int i = 0; i < syntax.size(); i++) {
            CommandSyntaxType target = syntax.get(i);

            if (target.isVarArg() && i != syntax.size() - 1) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @return If this returns <code>true</code>, the last syntax element is a
     *         var arg.
     */
    public boolean endsWithVarArg() {
        return syntax == null || syntax.get(syntax.size() - 1).isVarArg();
    }

    /**
     *
     * @return The size of the syntax.
     */
    public int size() {
        return syntax == null ? 0 : syntax.size();
    }

    /**
     *
     * @return If this returns <code>true</code>, the syntax is
     *         <code>null</code>.
     */
    public boolean isNull() {
        return syntax == null;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<CommandSyntaxType> iterator() {
        return syntax.iterator();
    }

    /**
     *
     * @return A {@link List} of {@link CommandSyntaxType}s.
     */
    public List<CommandSyntaxType> getSyntax() {
        return syntax;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[syntax=" + syntax + "]";
    }
}
