package com.exception;

public class ExpressionParseException extends Exception {
	
	String postfix;

	public String getPostfix() {
		return postfix;
	}

	public ExpressionParseException(String arg0, Throwable arg1, String postfix) {
		super(arg0, arg1);
		this.postfix = postfix;
	}

	public ExpressionParseException(String arg0, String postfix) {
		super(arg0);
		this.postfix = postfix;
	}

	public ExpressionParseException(Throwable arg0, String postfix) {
		super(arg0);
		this.postfix = postfix;
	}

}
