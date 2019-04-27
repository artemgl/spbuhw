package com.artemchernikov.g144;

import java.util.LinkedList;

/**A class describing calculating the meaning of expression*/
public class Calculator {

    /**A method returning priority of received operation*/
    private static int priority(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
        }

        return -1;
    }

    /**A method returning true if received symbol is an operand and false otherwise*/
    private static boolean isOperand(char symbol) {
        switch (symbol) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    /**A method correcting expression if it contains negative numbers*/
    private static String correctNegativeNumbers(String expression) {
        expression = expression.replaceAll(" ", "");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        for (int i = 0; i < expression.length(); i++) {
            if (isOperand(expression.charAt(i)) && expression.charAt(i + 1) == '-') {
                i++;
                int j = i + 1;
                for ( ; j < expression.length() && Character.isDigit(expression.charAt(j)); j++);
                String negativeNumber = expression.substring(i, j);
                expression = expression.substring(0, i) + expression.substring(i).replaceFirst(negativeNumber, "(0" + negativeNumber + ")");
                i = j + 2;
            }
        }
        return expression;
    }

    /**A method receiving expression in infix form and returning it in postfix form*/
    private static String transform(String expression) {
        LinkedList<Character> charStack = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        expression = correctNegativeNumbers(expression);

        for (int i = 0; i < expression.length(); i++) {
            char currentSymbol = expression.charAt(i);
            switch (currentSymbol) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '.':
                    for ( ; i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'); i++) {
                        result.append(expression.charAt(i));
                    }
                    result.append(' ');
                    i--;
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!charStack.isEmpty()) {
                        char currentOperation = charStack.pop();
                        if (priority(currentOperation) >= priority(currentSymbol)) {
                            result.append(currentOperation);
                        } else {
                            charStack.push(currentOperation);
                            break;
                        }
                    }
                    charStack.push(currentSymbol);
                    break;
                case '(':
                    charStack.push(currentSymbol);
                    break;
                case ')':
                    char currentOperation;
                    while ((currentOperation = charStack.pop()) != '(') {
                        result.append(currentOperation);
                    }
                    break;
                default:
                    result.append(currentSymbol);
                    break;
            }
        }

        while (!charStack.isEmpty()) {
            result.append(charStack.pop());
        }

        return result.toString().replaceAll(" E", "E");
    }

    /**A method converting an expression to postfix form and returning the meaning of it*/
    public static double calculate(String expression) {
        expression = transform(expression);

        LinkedList<Double> doubleStack = new LinkedList<>();

        StringBuilder currentNumber = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char currentSymbol = expression.charAt(i);
            switch (currentSymbol) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '.':
                case 'E':
                    currentNumber.append(currentSymbol);
                    break;
                case '+': {
                    double first = doubleStack.pop();
                    double second = doubleStack.pop();
                    doubleStack.push(first + second);
                    break;
                }
                case '-': {
                    double first = doubleStack.pop();
                    double second = doubleStack.pop();
                    doubleStack.push(second - first);
                    break;
                }
                case '*': {
                    double first = doubleStack.pop();
                    double second = doubleStack.pop();
                    doubleStack.push(first * second);
                    break;
                }
                case '/': {
                    double first = doubleStack.pop();
                    double second = doubleStack.pop();
                    doubleStack.push(second / first);
                    break;
                }
                case ' ':
                    doubleStack.push(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                    break;
            }
        }

        return doubleStack.pop();
    }

}