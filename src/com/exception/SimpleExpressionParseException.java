package com.exception;

public class SimpleExpressionParseException extends Exception {

	public SimpleExpressionParseException(String message) {
		super(message);
	}

	public SimpleExpressionParseException(Throwable cause) {
		super(cause);
	}

	public SimpleExpressionParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
