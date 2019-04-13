package com.artemchernikov.g144;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        SyntaxTree tree = new SyntaxTree();
        try {
            tree.readFromFile("text.txt");
        } catch (FileNotFoundException exc) {
            System.out.println("File not found");
        }
        tree.print();
        System.out.println();
        System.out.println("The meaning of this expression is " + tree.calculate());
    }
}
