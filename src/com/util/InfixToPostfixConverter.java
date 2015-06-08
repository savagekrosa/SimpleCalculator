package com.util;

import java.util.Stack;
import java.util.StringTokenizer;

import com.exception.ImbalancedBracesException;

public final class InfixToPostfixConverter {

	private InfixToPostfixConverter() {
	};

	public static String convert(String infix) throws ImbalancedBracesException {
		if (!hasBalancedBraces(infix)) {
			throw new ImbalancedBracesException("Imbalanced braces!");
		}
		String postfix = "";
		Stack<String> stack = new Stack<String>();
		StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/()^", true);

		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();

			// Algorytm: http://en.wikipedia.org/wiki/Shunting-yard_algorithm
			// On nie jest kompletnie zrobiony, na razie jest uproszczony i moze nie dzialac poprawnie
			// Konkretnie chodzi o linijke comparePriority(stack, s) >= 0 ktora jest nizej
			
			if (isOperator(s)) {
				while (!stack.empty() && comparePriority(stack, s) >= 0) {
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

	protected static int comparePriority(Stack<String> stack, String s) {
		return getPriority(stack.peek()) - getPriority(s);
	}

	protected static int getPriority(String operator) {
		if (operator.equals("+") || operator.equals("-"))
			return 1;
		else if (operator.equals("*") || operator.equals("/") || operator.equals("^"))
			return 2;
		else
			return 0;
	}
}
