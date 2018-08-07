package com.example.ducnguyen.calculatorapp;

public class Constant {
    public static final char ZERO = '0';
    public static final char ONE = '1';
    public static final char TWO = '2';
    public static final char THREE = '3';
    public static final char FOUR = '4';
    public static final char FIVE = '5';
    public static final char SIX = '6';
    public static final char SEVEN = '7';
    public static final char EIGHT = '8';
    public static final char NINE = '9';
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char PERCENT = '%';
    public static final char NEGATIVE_POSITIVE = '-';
    public static final char DOT = '.';
    public static final char OPENING_BRACKET = '(';
    public static final char CLOSING_BRACKET = ')';
    public static final char[] NUMBERS = {ZERO, ONE, TWO, THREE, FOUR, FIVE
            , SIX, SEVEN, EIGHT, NINE, DOT};
    public static final char[] OPERATORS = {ADD, SUB, MUL, DIV};
    public static final String SPACE = " ";
    public static final String DOUBLE_SPACE = "  ";
    public static final char[] START_CHAR_ERRORS = {ADD, DIV, PERCENT};
    public static final String[] SYNTAX_ERRORS = {"+*", "+/", "-*", "-/", "*/", "/*", "**", "//"
            , "+%", "-%", "*%", "/%", "%%"};
    public static final char[] END_CHAR_ERRORS = {ADD, SUB, MUL, DIV};
    public static final String[] OPERATOR_ADD_SYNTAX_ERROR = {"++", "-+", "*+", "/-"};
    public static final String CORRECT_NEGATIVE = "(0";
    public static final String CORRECT_PERCENT = "/100)";
    public static final char NUMBER_SIGN = '#';
}
