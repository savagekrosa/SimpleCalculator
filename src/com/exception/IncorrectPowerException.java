package com.exception;

public class IncorrectPowerException extends SimpleExpressionParseException {

	public IncorrectPowerException(Number i) {
		super("Unexpected power in exponentiation, should be non-negative integer, got: " + i);
	}

	public IncorrectPowerException(Number pow, Integer i, String postfix) {
		super("Only simple integers can be used as power, instead got " + i + "th factor of " + pow);
	}
}
