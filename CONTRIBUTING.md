Contributing
============

Thank you for your interest in contributing for the InternalPluginManager project! But please follow our contributing conventions (including code and commit conventions).


General contributing conventions
--------------------------------

	- Pull requests are only accepted if you have a ticket to work on.
	- Test your code.
	- Write a short breakdown of you changes in your pull request.
	- Link to your test materials (can be JUnit-Tests).
	- Before you commit your changes, run "check.sh", respectively "check.bat", if you're a windows user. You you get errors, read the error/fail message and change the things that you were shown (like "missing header in file XXX").
	- If you're an Eclipse user, please import "cleanup.xml", "codetemplates.xml" and "formatter.xml"  to follow our contributing conventions easily.


Code conventions
----------------

	- Follow the Oracle code conventions, excluding the maximum line width. Use a maximum line width of 130 insted.
	- Document every public and protected method and check for spelling. The apidocs will be generated out of the method documention.
	- Document everything (including @param, @return, @throws, etc.).
	- Tag overriding methods with @Override.
	- Place curly brackets in the same line as the expression.
	- Use curly brackets every time, also if there's only one expression.
	- Place an empty line at the end of each file.
	- Utility classes shouldn't have a visible constructor (create a private one).
	- Don't test for boolean values with the "==" operator. Use direct expressions instead.
	- Use as less as possible brackets in expressions.
	- Don't use to many "return", "continue" and "break" expressions.
	- Do NOT use labels.
	- Use four blanks instead of one tab.
	- Indent your expressions/lines correct.
	- Within a method, try to keep below three indentions.
	- Class names has to match the regex "^[A-Z][a-zA-Z0-9]*$".
	- Method names has to match the regex "^(_INVALID_)?[a-z][a-zA-Z0-9]*$".
	- Variable names has to match the regex "^[a-z_][a-zA-Z0-9]*$".
	- If you concat more than five Strings to one String, use a StringBuilder/-Buffer instead.
	- Don't tag classes with @author.
	- Include the header file (https://github.com/Blockhaus2000/InternalPluginManager/blob/master/src/main/build/header.txt) at the top of each file you create.


Commit conventions
------------------

	- Write the following types of commit parts in the listed order in the format "<char> <name> <description>"-
		+ Use "+" for "added"
		+ Use "-" for "removed"
		+ Use "?" for "fixed"
		+ Use "~" for "changed"
		+ Use "^" for "improved" or "updated"
	- In the headerline of each commit (which is the first line), write which tickets do you have worked on. Seperate types with a semicolon (";") and seperate tickets with the same type with a comma (",") and the last with a "and". Sort types in the following order and tickets within one type ina rising structure.
		+ "ADDS IPM-####"
		+ "REMOVES IPM-####"
		+ "FIXES IPM-####"
		+ "CHANGES IPM-####"
		+ "IMPROVES IPM-####"
	- This is an example commit message ("XXXXX" will be your message"):
		1. Line: "ADDS IPM-1 and IPM-2; FIXES IPM-5, IPM-8 and IPM-10"
		2. Line: "+ added XXXXX"
		3. Line: "+ added XXXXX"
		4. Line: "? fixed XXXXX"
		5. Line: "? fixed XXXXX"
		6. Line: "? fixes XXXXX"
