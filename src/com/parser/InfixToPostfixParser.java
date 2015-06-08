package com.parser;

import java.util.Stack;
import java.util.StringTokenizer;

import com.exception.ImbalancedBracesException;

public final class InfixToPostfixParser {

	private InfixToPostfixParser() {
	};

	// Algorithm: http://en.wikipedia.org/wiki/Shunting-yard_algorithm
	public static String parse(String infix) throws ImbalancedBracesException {
		if (!hasBalancedBraces(infix)) {
			throw new ImbalancedBracesException("Imbalanced braces!");
		}
		String postfix = "";
		Stack<String> stack = new Stack<String>();
		StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/()^", true);

		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			
			if (isOperator(s)) {
				while (!stack.empty() && isHigherOrEqualPrecedence(stack.peek(), s)) {
					postfix += stack.pop() + " ";
				}
				stack.push(s);
			} else if (s.equals("(")) {
				stack.push(s);
			} else if (s.equals(")")) {
				while (!stack.peek().equals("(")) {
					postfix += stack.pop() + " ";
				}
				stack.pop();
			} else {
				postfix += s + " ";
			}
		}
		while (!stack.empty()) {
			postfix += stack.pop() + " ";
		}

		return postfix;
	}

	protected static boolean isHigherOrEqualPrecedence(String op1, String op2) {
		int priorityCompare = getPriority(op1) - getPriority(op2);
		if (op2.equals("^")) {
			return priorityCompare > 0;
		}
		return priorityCompare >= 0;
	}

	public static boolean hasBalancedBraces(String infix) {
		Stack<String> stack = new Stack<String>();

		StringTokenizer tokenizer = new StringTokenizer(infix, "()", true);

		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();

			if (s.equals("(")) {
				stack.push(s);
			}
			if (s.equals(")")) {
				if (stack.isEmpty())
					return false;
				if (!stack.pop().equals("("))
					return false;
			}
		}

		return stack.isEmpty();
	}

	protected static boolean isOperator(String s) {
		return s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/") || s.equals("^");
	}

	protected static int getPriority(String operator) {
		if (operator.equals("+") || operator.equals("-"))
			return 1;
		else if (operator.equals("*") || operator.equals("/"))
			return 2;
		else if (operator.equals("^"))
			return 3;
		else
			return 0;
	}
}
