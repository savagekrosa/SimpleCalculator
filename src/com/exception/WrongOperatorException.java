package com.exception;

public class WrongOperatorException extends SimpleExpressionParseException {

	public WrongOperatorException(String message, String postfix) {
		super(message);
	}

}
