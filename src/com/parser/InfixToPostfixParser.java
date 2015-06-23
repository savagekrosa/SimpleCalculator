package com.parser;

import java.io.*;

import com.exception.InfixParseException;

public final class InfixToPostfixParser {

	private InfixToPostfixParser() {
	};

	public static String parse(String infix) throws InfixParseException {
		String postfix;
		try {
			Parser yyparser = new Parser(new StringReader(infix+"\n"));
			yyparser.yyparse();
			postfix = yyparser.postfix;
		} catch (Exception e) {
			throw new InfixParseException("Infix to postfix parse failed",e);
		}
		if (postfix == null || postfix.isEmpty()) {
			throw new InfixParseException("Infix to postfix parse failed: parse aborted");
		}
		return postfix;
	}
}
