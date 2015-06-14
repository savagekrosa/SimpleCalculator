package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.MainClass;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField expressionTextField;
	private JTextArea parseResultText;
	private MainClass parent;
	
	/**
	 * Create the frame.
	 */
	public MainFrame(MainClass pParent) {
		this.parent = pParent;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		expressionTextField = new JTextField();
		expressionTextField.setBounds(131, 52, 276, 27);
		expressionTextField.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		expressionTextField.setColumns(10);
		
		JLabel lblSimpleCalculator = new JLabel("Simple Calculator");
		lblSimpleCalculator.setBounds(106, 5, 232, 37);
		lblSimpleCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimpleCalculator.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblExpression = new JLabel("Expression:");
		lblExpression.setBounds(12, 57, 74, 19);
		lblExpression.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblY = new JLabel("y =");
		lblY.setBounds(98, 55, 26, 21);
		lblY.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		
		JButton btnParse = new JButton("Parse");
		btnParse.setBounds(185, 86, 81, 25);
		btnParse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				parent.parseExpression();
			}
		});
		
		JButton btnDrawGraph = new JButton("Draw graph");
		btnDrawGraph.setBounds(167, 285, 107, 25);
		btnDrawGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				parent.drawGraph();
			}
		});
		
		JLabel labelPR = new JLabel("Parsing result:");
		labelPR.setBounds(12, 120, 107, 21);
		labelPR.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		parseResultText = new JTextArea("Not yet parsed");
		parseResultText.setRows(2);
		parseResultText.setLineWrap(true);
		parseResultText.setBackground(UIManager.getColor("Button.background"));
		parseResultText.setEditable(false);
		parseResultText.setBounds(131, 120, 276, 69);
		parseResultText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(167, 229, 107, 25);
		contentPane.setLayout(null);
		contentPane.add(lblSimpleCalculator);
		contentPane.add(labelPR);
		contentPane.add(btnParse);
		contentPane.add(lblExpression);
		contentPane.add(lblY);
		contentPane.add(parseResultText);
		contentPane.add(expressionTextField);
		contentPane.add(btnDrawGraph);
		contentPane.add(btnCalculate);
	}

	public String getExpressionText() {
		return expressionTextField.getText();
	}
	
	public void setParseResult(String text, int flag) {
		parseResultText.setText(text);
		if (flag == 1) {
			parseResultText.setForeground(Color.GREEN);
		} else if (flag == -1) {
			parseResultText.setForeground(Color.RED);
		} else {
			parseResultText.setForeground(null);
		}
	}
}
