package com;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.drawer.MainGraph;
import com.exception.ExpressionParseException;
import com.exception.InfixParseException;
import com.gui.MainFrame;
import com.parser.InfixToPostfixParser;
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
		String postfix = null;
		if (expressionText.isEmpty()) {
			frame.setParseResult("Not yet parsed", 0);
			expression = null;
		} else {
			try {
				postfix = InfixToPostfixParser.parse(expressionText);
				expression = new CalculatedExpression(expressionText);
				frame.setParseResult(expression.toString(), 1);
				showInfoMessage("Parse successful!\nPostfix expression: " + postfix);
			} catch (InfixParseException e) {
				handleException(e, "Infix parse error", postfix);
			} catch (ExpressionParseException e) {
				handleException(e, "Postfix parse error", postfix);
			} catch (Exception e) {
				handleException(e, "Unhandled error", postfix);
			}
			System.out.println("Current expression: " + expression);
		}
	}

	protected void handleException(Exception e, String pTitle, String pPostfix) {
		if (e.getMessage() != null) {
			String error = "";
			String postfix = "";
			int index = e.getMessage().lastIndexOf("Exception:");
			if (index < 0)
				error = e.getMessage();
			else
				error = e.getMessage().substring(index+11);
			if (pPostfix != null && !pPostfix.isEmpty())
				postfix = "\nPostfix expression: " + pPostfix;
			frame.setParseResult(error, -1);
			showErrorMessage(error + postfix, pTitle);
		} else {
			frame.setParseResult("Not yet parsed", -1);
			showErrorMessage(e.toString(), pTitle);
		}
		expression = null;
		e.printStackTrace();
	}

	private void showInfoMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showErrorMessage(String message, String title) {
		JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
	}

	public void drawGraph(String start, String end) {
		if (expression != null) {
			FunctionDrawingData funcData;
			try {
				funcData = new FunctionDrawingData(expression, Double.parseDouble(start), Double.parseDouble(end));
			} catch (NumberFormatException e) {
				showInfoMessage("Incorrect range, will draw with default range instead");
				funcData = new FunctionDrawingData(expression);
			}
			MainGraph graph = new MainGraph(funcData);
			graph.setVisible(true);
		} else {
			showInfoMessage("There is no parsed exception!");
		}
	}

	public void showCalculateDialog(String arg) {
		try {
			if (expression != null) {
				double argument = Double.parseDouble(arg);
				String result = "Result for " + argument + " is: " + expression.calculate(argument);
				JOptionPane.showMessageDialog(new JFrame(), result, "Result", JOptionPane.INFORMATION_MESSAGE);
			} else {
				showInfoMessage("There is no parsed exception!");
			}
		} catch (NumberFormatException e) {
			showErrorMessage("Argument needs to be a number!", "Error: Not a number");
		}
	}

}
