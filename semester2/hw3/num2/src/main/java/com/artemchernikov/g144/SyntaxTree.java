package com.artemchernikov.g144;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**A class describing syntax tree*/
public class SyntaxTree {

    private Node root;

    public int calculate() {
        return root.get();
    }

    public int calculate(String expression) {
        root = readTree(expression.substring(1));
        return root.get();
    }

    public void print() {
        root.print();
    }

    private Node readChild(String line) {
        char symbol = line.charAt(0);
        if (symbol == '(') {
            line = line.substring(1);
            return readTree(line);
        } else {
            int lengthOfNumber = 0;
            while (Character.isDigit(line.charAt(++lengthOfNumber)));
            int number = Integer.parseInt(line.substring(0, lengthOfNumber));
            return new Operand(number);
        }
    }

    private Node readTree(String line) {
        char operation = line.charAt(0);
        line = line.substring(2);

        Node leftChild = readChild(line);

        int numberOfExcessSymbols = 1;
        if (line.charAt(0) == '(') {
            for (int bracketsFactor = 1; bracketsFactor > 0; numberOfExcessSymbols++) {
                if (line.charAt(numberOfExcessSymbols) == '(') {
                    bracketsFactor++;
                }
                if (line.charAt(numberOfExcessSymbols) == ')') {
                    bracketsFactor--;
                }
            }
            numberOfExcessSymbols++;
        } else {
            while (line.charAt(numberOfExcessSymbols++) != ' ');
        }
        line = line.substring(numberOfExcessSymbols);

        Node rightChild = readChild(line);

        return new Operator(operation, leftChild, rightChild);
    }

    /**A method building tree by file*/
    public void readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fin = new Scanner(file);
        String line = fin.nextLine().substring(1);
        root = readTree(line);
    }

    /**An interface describing node of the syntax tree*/
    private interface Node {
        int get();
        void print();
    }

    /**A class describing operand of the syntax tree*/
    private class Operand implements Node {

        private Operand(int value) {
            this.value = value;
        }

        private int value;

        @Override
        public int get() {
            return value;
        }

        @Override
        public void print() {
            System.out.print(value);
        }
    }

    /**A class describing operator of the syntax tree*/
    private class Operator implements Node {

        private Operator(char operation, Node leftChild, Node rightChild) {
            this.operation = operation;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        private char operation;
        private Node leftChild;
        private Node rightChild;

        @Override
        public int get() {
            switch (operation) {
                case '+':
                    return leftChild.get() + rightChild.get();
                case '-':
                    return leftChild.get() - rightChild.get();
                case '*':
                    return leftChild.get() * rightChild.get();
                case '/':
                    return leftChild.get() / rightChild.get();
                default:
                    throw new IllegalArgumentException("This operation is not supported");
            }
        }

        @Override
        public void print() {
            System.out.print("(" + operation + " ");
            leftChild.print();
            System.out.print(' ');
            rightChild.print();
            System.out.print(')');
        }
    }
}
