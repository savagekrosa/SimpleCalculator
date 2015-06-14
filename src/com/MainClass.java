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
				CalculatedExpression calculatedExpression = new CalculatedExpression(expressionText);
				expression = calculatedExpression;
				frame.setParseResult("Parse succesful!", 1);
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

	public void drawGraph() {
		if (expression != null) {
			FunctionDrawingData funcData = new FunctionDrawingData(expression);
			MainGraph graph = new MainGraph(funcData);
			graph.setVisible(true);
		} else {
			showErrorMessage("There is no parsed exception!");
		}
	}

}
