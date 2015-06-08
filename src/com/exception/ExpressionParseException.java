package com.exception;

public class ExpressionParseException extends RuntimeException {

	public ExpressionParseException() {
		super();
	}

	public ExpressionParseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExpressionParseException(String arg0) {
		super(arg0);
	}

	public ExpressionParseException(Throwable arg0) {
		super(arg0);
	}

}
