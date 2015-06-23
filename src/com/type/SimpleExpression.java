package com.type;

import com.exception.IncorrectPowerException;
import com.exception.SimpleExpressionParseException;
import com.exception.WrongFactorException;

public class SimpleExpression implements Cloneable {

	private double[] factors;
	private static final int MAX_FACTORS = 10;

	public SimpleExpression(double[] factors) {
		this.factors = factors.clone();
	}

	public SimpleExpression(String s) throws SimpleExpressionParseException {
		factors = new double[MAX_FACTORS];
		try {
			if (s.endsWith("x")) {
				String numberString = s.substring(0, s.length() - 1);
				if (numberString.isEmpty()) {
					factors[1] = 1;
				} else if (numberString.equals("-")) {
					factors[1] = -1;
				} else {
					factors[1] = Double.parseDouble(numberString);
				}
			} else {
				factors[0] = Double.parseDouble(s);
			}
		} catch (Exception e) {
			throw new SimpleExpressionParseException("Couldn't parse " + s + " to simple expression", e);
		}
	}

	public double calculate(double x) {
		double result = 0.0;
		for (int i = 0; i < factors.length; i++) {
			result += factors[i] * Math.pow(x, i);
		}
		return result;
	}

	public SimpleExpression add(SimpleExpression exp) {
		double[] factors2 = exp.getFactors();
		for (int i = 0; i < factors.length; i++)
			factors[i] += factors2[i];
		return this;
	}

	public SimpleExpression subtract(SimpleExpression exp) {
		double[] factors2 = exp.getFactors();
		for (int i = 0; i < factors.length; i++)
			factors[i] -= factors2[i];
		return this;
	}

	public SimpleExpression multiply(SimpleExpression exp) throws WrongFactorException {
		double[] factors2 = exp.getFactors();
		double[] newFactors = new double[MAX_FACTORS];
		for (int i = 0; i < factors.length; i++)
			for (int j = 0; j < factors2.length; j++) {
				double outcome = factors[i] * factors2[j];
				if (outcome != 0 && i + j >= MAX_FACTORS) {
					throw new WrongFactorException(i + j, MAX_FACTORS - 1);
				}
				if (i + j < MAX_FACTORS) {
					newFactors[i + j] += outcome;
				}
			}
		return new SimpleExpression(newFactors);
	}

	public SimpleExpression divide(SimpleExpression exp) throws WrongFactorException {
		double[] factors2 = exp.getFactors();
		double[] newFactors = new double[MAX_FACTORS];
		for (int i = 0; i < factors.length; i++)
			for (int j = 0; j < factors2.length; j++) {
				if (factors2[j] != 0) {
					double outcome = factors[i] / factors2[j];
					if (outcome != 0 && i - j < 0) {
						throw new WrongFactorException(i - j);
					}
					if (i - j >= 0) {
						newFactors[i - j] += outcome;
					}
				}
			}
		return new SimpleExpression(newFactors);
	}

	public SimpleExpression exponent(SimpleExpression exp) throws IncorrectPowerException, WrongFactorException {
		assertCorrectPower(exp.getFactors());
		int pow = (int) exp.getFactors()[0];
		if (pow == 0) {
			return getSingleIntegerExpression(1);
		} else if (pow == 1) {
			return this;
		} else if (pow > 1) {
			SimpleExpression newExpression = this.clone();
			while (pow > 1) {
				newExpression = newExpression.multiply(this);
				pow--;
			}
			return newExpression;
		}
		throw new IncorrectPowerException(pow);
	}

	private void assertCorrectPower(double[] factors) throws IncorrectPowerException {
		if (factors[0] < 0 || factors[0] % 1 != 0) {
			throw new IncorrectPowerException(factors[0]);
		}
		for (int i = 1; i < MAX_FACTORS; i++) {
			if (factors[i] != 0)
				throw new IncorrectPowerException(factors[i]);
		}
	}

	private SimpleExpression getSingleIntegerExpression(int i) {
		double[] newFactors = new double[MAX_FACTORS];
		newFactors[0] = i;
		return new SimpleExpression(newFactors);
	}

	public double[] getFactors() {
		return factors;
	}

	@Override
	public SimpleExpression clone() {
		return new SimpleExpression(factors);
	}

	@Override
	public String toString() {
		String expression = "";
		for (int i = factors.length - 1; i >= 0; i--) {
			if (factors[i] > 0) {
				if (!expression.isEmpty())
					expression += " + ";
				if (i == 0 || factors[i] != 1)
					expression += factors[i];
			} else if (factors[i] < 0) {
				if (!expression.isEmpty())
					expression += " - ";
				else
					expression += "-";
				if (i == 0 || factors[i] != 1)
					expression += (-factors[i]);
			}
			if (factors[i] != 0 && i > 0) {
				expression += "x";
				if (i > 1) {
					expression += "^" + i;
				}
			}
		}
		if (expression.isEmpty()) {
			return "0";
		}
		return expression;
	}
}
