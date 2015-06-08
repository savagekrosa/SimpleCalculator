package com.exception;

public class WrongFactorException extends RuntimeException {

	public WrongFactorException(int current) {
		super("You can't set negative factors, got: " + current);
	}
	
	public WrongFactorException(int current, int max) {
		super("Got non-zero " + current + "nth factor, maximum factor is: " + max);
	}

}
