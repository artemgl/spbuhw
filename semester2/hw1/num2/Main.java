package com.artemchernikov.g144;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {

        System.out.println("Enter:");
        System.out.println("0 - exit");
        System.out.println("1 - add the value");
        System.out.println("2 - remove the value");
        System.out.println("3 - check if the value exists in the list");
        System.out.println("4 - print the list");

        List NewList = new List();

        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            input = in.nextInt();
            int value = 0;
            switch (input) {
                case 1:
                    System.out.println("Enter the value to add");
                    value = in.nextInt();
                    NewList.addElement(value);
                    break;
                case 2:
                    System.out.println("Enter the value to remove");
                    value = in.nextInt();
                    NewList.removeElement(value);
                    break;
                case 3:
                    System.out.println("Enter the value");
                    value = in.nextInt();
                    System.out.println("This value" + (NewList.exists(value) ? " exists" : " doesn't exist") + " in the list");
                    break;
                case 4:
                    NewList.print();
                    break;
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);
    }
}
