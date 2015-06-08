package com.exception;

public class InfixParseException extends Exception {

	public InfixParseException() {
		super();
	}

	public InfixParseException(String message) {
		super(message);
	}

	public InfixParseException(Throwable cause) {
		super(cause);
	}

	public InfixParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
