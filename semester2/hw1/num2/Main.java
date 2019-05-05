package com.artemchernikov.g144;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter:");
        System.out.println("0 - exit");
        System.out.println("1 - add the value");
        System.out.println("2 - remove the value");
        System.out.println("3 - check if the value exists in the list");
        System.out.println("4 - check if the list is empty");
        System.out.println("5 - print the list");

        List newList = new List();

        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            input = in.nextInt();
            int value = 0;
            switch (input) {
                case 1:
                    System.out.println("Enter the value to add");
                    value = in.nextInt();
                    newList.addElement(value);
                    break;
                case 2:
                    System.out.println("Enter the value to remove");
                    value = in.nextInt();
                    newList.removeElement(value);
                    break;
                case 3:
                    System.out.println("Enter the value");
                    value = in.nextInt();
                    System.out.println("This value" + (newList.exists(value) ? " exists" : " doesn't exist") + " in the list");
                    break;
                case 4:
                    System.out.println("The list is" + (newList.isEmpty() ? "" : "n't") + " empty");
                    break;
                case 5:
                    newList.print();
                    break;
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);
    }
}
