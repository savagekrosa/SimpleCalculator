package com.type;

public class FunctionDrawingData {
	
	private static final int DEFAULT_STEPS = 100;
	private static final int DEFAULT_X = 10;
	
	private final CalculatedExpression expression;
	private final double startX;
	private final double endX;
	private final int steps;

	public FunctionDrawingData(CalculatedExpression pExpression) {
		this(pExpression, -DEFAULT_X, DEFAULT_X, DEFAULT_STEPS);
	}
	
	public FunctionDrawingData(CalculatedExpression pExpression, double pStartX, double pEndX) {
		this(pExpression, pStartX, pEndX, DEFAULT_STEPS);
	}
	
	public FunctionDrawingData(CalculatedExpression pExpression, double pStartX, double pEndX, int pSteps) {
		expression = pExpression;
		
		if (pStartX < pEndX) {
			startX = pStartX;
			endX = pEndX;
		} else if (pStartX > pEndX) {
			startX = pEndX;
			endX = pStartX;
		} else {
			startX = -DEFAULT_X;
			endX = DEFAULT_X;
		}

		if (pSteps >= 2) {
			steps = pSteps;
		} else {
			steps = DEFAULT_STEPS; 
		}
	}
	
	public double getStartX() {
		return startX;
	}

	public double getEndX() {
		return endX;
	}

	public int getSteps() {
		return steps;
	}
	
	public double getY(double x) {
		return expression.calculate(x);
	}
}
