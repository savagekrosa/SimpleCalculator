package com.type;

import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

import com.exception.IncorrectExpressionException;
import com.exception.InfixParseException;
import com.exception.WrongOperatorException;
import com.parser.InfixToPostfixParser;

public class CalculatedExpression {

	private SimpleExpression result;

	public CalculatedExpression(String expression) throws InfixParseException, IOException {
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
			} else if (!s.equals("-") && !s.equals("+")) {
				SimpleExpression value1 = stack.pop();
				SimpleExpression value2 = stack.pop();
				stack.push(simpleCalculate(s.charAt(0), value1, value2));
			} else {
				SimpleExpression value1 = stack.pop();
				SimpleExpression value2;
				if (stack.isEmpty())
					value2 = new SimpleExpression("0");
				else
					value2 = stack.pop();
				stack.push(simpleCalculate(s.charAt(0), value1, value2));
			}
		}

		if (stack.size() != 1) {
			throw new IncorrectExpressionException("There are " + (stack.size() - 1) + " extra symbol(s) "
					+ "in postfix expression: " + postfix);
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