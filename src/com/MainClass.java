package com;

import com.gui.MainFrame;
import com.type.CalculatedExpression;

public class MainClass {
	
	private MainFrame frame;
	private CalculatedExpression expression;
	
	public MainClass() {
		frame = new MainFrame(this);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainClass();
	}

	public void parseExpression() {
		String expressionText = frame.getExpressionText();
		if (expressionText.isEmpty()) {
			frame.setParseResult("Not yet parsed", 0);
			expression = null;
		} else {
			try {
				CalculatedExpression calculatedExpression = new CalculatedExpression(expressionText);
				expression = calculatedExpression;
				frame.setParseResult("Parse succesful!", 1);
			} catch (Exception e) {
				frame.setParseResult(e.getMessage(), -1);
				expression = null;
				e.printStackTrace();
			}
			System.out.println("Current expression: " + expression);
		}
	}
	
}
