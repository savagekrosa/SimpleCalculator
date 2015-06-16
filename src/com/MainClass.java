package com;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.drawer.MainGraph;
import com.gui.MainFrame;
import com.type.CalculatedExpression;
import com.type.FunctionDrawingData;

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
				expression = new CalculatedExpression(expressionText);
				frame.setParseResult(expression.toString(), 1);
			} catch (Exception e) {
				if (e.getMessage() != null) {
					frame.setParseResult(e.getMessage(), -1);
					showErrorMessage(e.getMessage());
				} else {
					frame.setParseResult("Not yet parsed", -1);
					showErrorMessage(e.toString());
				}
				expression = null;
				e.printStackTrace();
			}
			System.out.println("Current expression: " + expression);
		}
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void drawGraph(String start, String end) {
		if (expression != null) {
			FunctionDrawingData funcData;
			try {
				funcData = new FunctionDrawingData(expression, Double.parseDouble(start), Double.parseDouble(end));
			} catch (NumberFormatException e) {
				showErrorMessage("Incorrect range, will draw with default range instead");
				funcData = new FunctionDrawingData(expression);
			}
			MainGraph graph = new MainGraph(funcData);
			graph.setVisible(true);
		} else {
			showErrorMessage("There is no parsed exception!");
		}
	}

	public void showCalculateDialog(String arg) {
		try {
			if (expression != null) {
				double argument = Double.parseDouble(arg);
				String result = "Result for " + argument + " is: " + expression.calculate(argument);
				JOptionPane.showMessageDialog(new JFrame(), result, "Result", JOptionPane.INFORMATION_MESSAGE);
			} else {
				showErrorMessage("There is no parsed exception!");
			}
		} catch (NumberFormatException e) {
			showErrorMessage("Argument needs to be a number!");
		}
	}

}
