package com.exception;

public class ImbalancedBracesException extends InfixParseException {

	public ImbalancedBracesException() {
		super();
	}

	public ImbalancedBracesException(String message) {
		super(message);
	}

}
