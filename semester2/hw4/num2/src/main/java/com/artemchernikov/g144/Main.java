package com.artemchernikov.g144;

import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter:");
        System.out.println("0 - exit");
        System.out.println("1 - add the value");
        System.out.println("2 - remove the value");
        System.out.println("3 - check if the value exists in the tree");
        System.out.println("4 - check if the tree is empty");
        System.out.println("5 - get size of the tree");

        Collection<Integer> tree = new AVLTree<>();

        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            input = in.nextInt();
            int value = 0;
            switch (input) {
                case 1:
                    System.out.println("Enter the value to add");
                    value = in.nextInt();
                    if (tree.add(value)) {
                        System.out.println("The value successfully added");
                    } else {
                        System.out.println("This value already exists in the tree");
                    }
                    break;
                case 2:
                    System.out.println("Enter the value to remove");
                    value = in.nextInt();
                    if (tree.remove(value)) {
                        System.out.println("The value successfully removed");
                    } else {
                        System.out.println("This value doesn't already exist in the tree");
                    }
                    break;
                case 3:
                    System.out.println("Enter the value");
                    value = in.nextInt();
                    System.out.println("This value" + (tree.contains(value) ? " exists" : " doesn't exist") + " in the tree");
                    break;
                case 4:
                    System.out.println("The tree is" + (tree.isEmpty() ? "" : "n't") + " empty");
                    break;
                case 5:
                    System.out.println("The size of the tree: " + tree.size());
                    break;
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);
    }
}
