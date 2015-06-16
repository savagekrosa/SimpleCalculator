package com.exception;

public class IncorrectPowerException extends RuntimeException {

	public IncorrectPowerException() {
		super();
	}

	public IncorrectPowerException(String message) {
		super(message);
	}

	public IncorrectPowerException(Number i) {
		super("Unexpected power in exponentiation, should be non-negative integer, got: " + i);
	}
	
	public IncorrectPowerException(Number pow, Integer i) {
		super("Only simple integers can be used as power, instead got " + i + "th factor of " + pow);
	}
}
