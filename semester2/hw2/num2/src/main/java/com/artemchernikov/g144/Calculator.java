package com.artemchernikov.g144;

/**A class describing calculating the meaning of expression*/
public class Calculator {

    /**A method returning priority of received operation*/
    private int priority(char operation) {
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

    /**A method receiving expression in infix form and returning it in postfix form*/
    private String transform(String expression) {
        IStack charStack = new StackOnArray(expression.length());
        StringBuffer result = new StringBuffer();

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
                    result.append(currentSymbol);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!charStack.isEmpty()) {
                        char currentOperation = (char)charStack.pop();
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
                    while ((currentOperation = (char)charStack.pop()) != '(') {
                        result.append(currentOperation);
                    }
                    break;
            }
        }

        while (!charStack.isEmpty()) {
            result.append((char)charStack.pop());
        }

        return result.toString();
    }

    /**A method converting an expression to postfix form and returning the meaning of it*/
    public int calculate(String expression) {
        expression = transform(expression);

        IStack intStack = new StackOnLinkedList();

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
                    intStack.push(currentSymbol - '0');
                    break;
                case '+': {
                    int first = intStack.pop();
                    int second = intStack.pop();
                    intStack.push(first + second);
                    break;
                }
                case '-': {
                    int first = intStack.pop();
                    int second = intStack.pop();
                    intStack.push(second - first);
                    break;
                }
                case '*': {
                    int first = intStack.pop();
                    int second = intStack.pop();
                    intStack.push(first * second);
                    break;
                }
                case '/': {
                    int first = intStack.pop();
                    int second = intStack.pop();
                    intStack.push(second / first);
                    break;
                }
            }
        }

        return intStack.pop();
    }

}
