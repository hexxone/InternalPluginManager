/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandSyntax {
    private final List<CommandSyntaxType> syntax;

    public CommandSyntax(final String syntaxString) {
        if (syntaxString == null || syntaxString.length() == 0) {
            syntax = null;
        } else {
            syntax = parseCommandSyntax(syntaxString);
        }
    }

    public List<CommandSyntaxType> parseCommandSyntax(final String syntaxString) throws IllegalArgumentException {
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

    public int size() {
        return syntax != null ? syntax.size() : 0;
    }

    // Getter
    public List<CommandSyntaxType> getSyntax() {
        return syntax;
    }
}
