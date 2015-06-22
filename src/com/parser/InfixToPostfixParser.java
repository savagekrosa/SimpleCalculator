package com.parser;

import java.io.*;

import com.exception.InfixParseException;

public final class InfixToPostfixParser {

	private InfixToPostfixParser() {
	};

	public static String parse(String infix) throws InfixParseException, IOException {
		Parser yyparser = new Parser(new StringReader(infix+"\n"));
        yyparser.yyparse();
		return yyparser.postfix;
	}
}
