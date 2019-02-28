package com.artemchernikov.g144;

public class Main {
    public static void main(String[] argc) {

        System.out.println("Enter:");
        System.out.println("0 - exit");
        System.out.println("1 - add the value");
        System.out.println("2 - remove the value");
        System.out.println("3 - check if the value exists in the list");
        System.out.println("4 - print the list");

        Stack NewList = new List();

        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter the value to add");
                    int value = in.nextInt();
                    NewList.addElement(value);
                    break;
                case 2:
                    System.out.println("Enter the value to remove");
                    int value = in.nextInt();
                    NewList.removeElement(value);
                    break;
                case 3:
                    System.out.println("Enter the value");
                    int value = in.nextInt();
                    System.out.println("This value" + (NewList.exists(value) ? "" : " doesn't") + " exists in the list");
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
