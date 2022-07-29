package com.pao.availity.exercisefour;

import java.util.Stack;

public class ParenthesesChecker {

	private static boolean isValidParentheses(String data) {
		if (null == data || data.length() <= 1) return false;

		//Create Stack of Character to store those characters from input
		Stack<Character> stack = new Stack<Character>();

		//Look through the input
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i) == '{' || data.charAt(i) == '[' || data.charAt(i) == '(') {
				stack.push(data.charAt(i));
			}

			if (stack.isEmpty()) {
				return false;
			}

			switch (data.charAt(i)) {
			case ')': {
				if (stack.peek() == '{' || stack.peek() == '[') {
					return false;
				}
				stack.pop();
				break;
			}
			case '}': {
				if (stack.peek() == '(' || stack.peek() == '[') {
					return false;
				}
				stack.pop();
				break;
			}
			case ']': {
				if (stack.peek() == '{' || stack.peek() == '(') {
					return false;
				}
				stack.pop();
				break;
			}
			}
		}
		return stack.isEmpty();
	}
	
	private static void print(boolean flag) {
		System.out.println("" + flag);
	}

	private static void testWithNullInput() {
		print(isValidParentheses(null));//Should be false
	}

	private static void testWithEmptyInput() {
		print(isValidParentheses(""));//Should be false
	}

	private static void testWithInvalidInput() {
		print(isValidParentheses("("));//Should be false
	}

	private static void testWithValidInput() {
		String expression = "([{()}])";
		print(isValidParentheses(expression));//Should be true
	}

	public static void main(String[] args) {
		testWithNullInput();
		testWithEmptyInput();
		testWithInvalidInput();
		testWithValidInput();
	}
}
