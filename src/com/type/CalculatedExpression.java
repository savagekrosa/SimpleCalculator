package com.type;

import java.util.Stack;
import java.util.StringTokenizer;

import com.exception.WrongOperatorException;
import com.parser.InfixToPostfixParser;

public class CalculatedExpression {

	private SimpleExpression result;

	public CalculatedExpression(String expression) {
		String postfix = InfixToPostfixParser.parse(expression);
		result = convertToExpressionType(postfix);
	}
	
	protected boolean isOperator(String s) {
		return s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/") || s.equals("^");
	}

	private SimpleExpression convertToExpressionType(String postfix) {
		Stack<SimpleExpression> stack = new Stack<SimpleExpression>();
		StringTokenizer tokenizer = new StringTokenizer(postfix, " ");

		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			if (!isOperator(s)) {
				SimpleExpression value = new SimpleExpression(s);
				stack.push(value);
			} else {
				SimpleExpression value1 = stack.pop();
				SimpleExpression value2 = stack.pop();
				stack.push(simpleCalculate(s.charAt(0), value1, value2));
			}
		}
		
		return stack.pop();
	}

	protected SimpleExpression simpleCalculate(char operator, SimpleExpression value1, SimpleExpression value2) {
		switch (operator) {
			case '*':
				return value2.multiply(value1);
			case '+':
				return value2.add(value1);
			case '-':
				return value2.subtract(value1);
			case '/':
				return value2.divide(value1);
			case '^':
				return value2.exponent(value1);
		}
		throw new WrongOperatorException(operator + " is not a proper operator");
	}

	public SimpleExpression getResult() {
		return result;
	}
	
	@Override
	public String toString() {
		return result.toString();
	}
	
	public double calculate(double x) {
		return result.calculate(x);
	}
}