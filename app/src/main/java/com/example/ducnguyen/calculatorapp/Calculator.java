package com.example.ducnguyen.calculatorapp;

import java.util.Stack;

public class Calculator {

    public static boolean checkSyntaxExpression(String expression) {
        if (expression.length() == 0) return false;
        for (char startCharError : Constant.START_CHAR_ERRORS) {
            if (expression.charAt(0) == startCharError) return false;
        }
        for (String syntaxError : Constant.SYNTAX_ERRORS) {
            if (expression.contains(syntaxError)) return false;
        }
        for (char endCharError : Constant.END_CHAR_ERRORS) {
            if (expression.charAt(expression.length() - 1) == endCharError) return false;
        }
        return true;
    }

    public static float calculate(String expression) {
        String correctedExpression = correctExpression(expression);
        String postFixExpression = convertInfixToPostFix(correctedExpression);
        String[] elements = postFixExpression.split(Constant.SPACE);
        Stack<Float> stack = new Stack<>();
        float temp = 0;
        int i = 0, length = elements.length;
        do {
            if (isOperator(elements[i].charAt(0))) {
                float one = stack.pop();
                float two = stack.pop();
                switch (elements[i].charAt(0)) {
                    case Constant.MUL:
                        temp = one * two;
                        break;
                    case Constant.DIV:
                        temp = two / one;
                        break;
                    case Constant.ADD:
                        temp = one + two;
                        break;
                    case Constant.SUB:
                        temp = two - one;
                        break;
                }
                stack.push(temp);
            } else {
                stack.push(Float.valueOf(elements[i]));
            }
            length--;
            i++;
        } while (length != 0);
        return stack.pop();
    }

    private static String correctExpression(String expression) {
        StringBuilder output = new StringBuilder(expression);
        for (String addOperatorSyntaxError : Constant.OPERATOR_ADD_SYNTAX_ERROR) {
            int index = output.indexOf(addOperatorSyntaxError);
            String operator = String.valueOf(addOperatorSyntaxError.charAt(0));
            while (index != -1) {
                output.replace(index, index + addOperatorSyntaxError.length(), operator);
                index += operator.length();
                index = output.indexOf(addOperatorSyntaxError, index);
            }
        }
        if (output.charAt(0) == Constant.ADD) output.insert(0, Constant.ZERO);
        for (int index = 0; index < output.length(); index++) {
            if (output.charAt(index) == Constant.SUB
                    && (index == 0 || isOperator(output.charAt(index - 1)))) {
                output.insert(index, Constant.CORRECT_NEGATIVE);
                int k = index + 3;
                while (k < output.length() && isNumber(output.charAt(k))) {
                    k++;
                }
                output.insert(k, Constant.CLOSING_BRACKET);
            } else if (output.charAt(index) == Constant.PERCENT) {
                output.replace(index, index + 1, Constant.CORRECT_PERCENT);
                int k = index;
                do {
                    k--;
                } while (k > 0 && isNumber(output.charAt(k)));
                k = k == 0 ? k : ++k;
                output.insert(k, Constant.OPENING_BRACKET);
            }
        }
        return output.toString();
    }

    private static String convertInfixToPostFix(String infix) {
        StringBuilder postfix = new StringBuilder();
        String[] elements = processInput(infix).split(Constant.SPACE);
        Stack<Character> s = new Stack<>();
        s.push(Constant.NUMBER_SIGN);
        for (String element : elements) {
            if (isOperator(element.charAt(0))) {
                while (checkPrecedence(element.charAt(0), s.peek())) {
                    postfix.append(s.pop()).append(Constant.SPACE);
                }
                s.push(element.charAt(0));
            } else switch (element.charAt(0)) {
                case Constant.OPENING_BRACKET:
                    s.push(element.charAt(0));
                    break;
                case Constant.CLOSING_BRACKET:
                    while (s.peek() != Constant.OPENING_BRACKET) {
                        postfix.append(s.pop()).append(Constant.SPACE);
                    }
                    s.pop();
                    break;
                default:
                    postfix.append(element).append(Constant.SPACE);
                    break;
            }
        }
        while (s.peek() != Constant.NUMBER_SIGN) {
            postfix.append(s.pop()).append(Constant.SPACE);
        }
        postfix = new StringBuilder(postfix.toString().trim());
        return postfix.toString();
    }

    private static boolean isNumber(char c) {
        for (char number : Constant.NUMBERS) {
            if (c == number) return true;
        }
        return false;
    }

    private static boolean isOperator(char c) {
        for (char operator : Constant.OPERATORS) {
            if (c == operator) return true;
        }
        return false;
    }

    private static String processInput(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char in = input.charAt(i);
            if (isOperator(in)) {
                builder.append(Constant.SPACE).append(in).append(Constant.SPACE);
            } else switch (in) {
                case Constant.OPENING_BRACKET:
                    builder.append(in).append(Constant.SPACE);
                    break;
                case Constant.CLOSING_BRACKET:
                    builder.append(Constant.SPACE).append(in);
                    break;
                default:
                    builder.append(in);
                    break;
            }
        }
        builder = new StringBuilder(standardize(builder.toString()));
        return builder.toString();
    }

    private static String standardize(String input) {
        input = input.trim();
        input = input.replaceAll(Constant.DOUBLE_SPACE, Constant.SPACE);
        return input;
    }

    private static boolean checkPrecedence(char c1, char c2) {
        return (c1 == Constant.ADD || c1 == Constant.SUB)
                && (c2 == Constant.ADD || c2 == Constant.SUB)
                || (c2 == Constant.MUL || c2 == Constant.DIV) && (isOperator(c1));
    }
}
