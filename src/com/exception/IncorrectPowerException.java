package com.exception;

public class IncorrectPowerException extends RuntimeException {

	public IncorrectPowerException() {
		super();
	}

	public IncorrectPowerException(String message) {
		super(message);
	}

	public IncorrectPowerException(Object message) {
		super("Unexpected power in exponentiation, should be non-negative integer, got: " + message);
	}
}
