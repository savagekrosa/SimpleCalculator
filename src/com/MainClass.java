package com;

import com.type.CalculatedExpression;

public class MainClass {

	public static void main(String[] args) {
		System.out.println(new CalculatedExpression("2+7x"));
		System.out.println(new CalculatedExpression("9x/9"));
		System.out.println(new CalculatedExpression("(8x+2)^2-11"));
		System.out.println(new CalculatedExpression("2^3^2"));
	}

}
