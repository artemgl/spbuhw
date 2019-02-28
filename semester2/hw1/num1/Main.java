package com.artemchernikov.g144;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {

        System.out.println("Enter:");
        System.out.println("0 - exit");
        System.out.println("1 - add the value");
        System.out.println("2 - get the value");
        System.out.println("3 - check if the stack is empty");
        System.out.println("4 - get the size of the stack");

        Stack NewStack = new Stack();

        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter the value");
                    int value = in.nextInt();
                    NewStack.push(value);
                    break;
                case 2:
                    System.out.println("The value is " + NewStack.pop());
                    break;
                case 3:
                    System.out.println("Stack is" + (NewStack.isEmpty() ? "" : " not") + " empty");
                    break;
                case 4:
                    System.out.println("The size of the stack is " + NewStack.size());
                    break;
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);



    }
}
