package com.artemchernikov.g144;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Calculator calc = new Calculator();

        System.out.println("Enter an expression");

        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();

        System.out.print("Answer is ");
        System.out.println(calc.calculate(expression));

    }
}
