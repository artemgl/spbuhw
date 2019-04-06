package com.artemchernikov.g144;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter:");
        System.out.println("0 - exit");
        System.out.println("1 - add the value to the hash-table");
        System.out.println("2 - remove the value from the hash-table");
        System.out.println("3 - check if the value exists in the hash-table");
        System.out.println("4 - print statistics of the hash-table");
        System.out.println("5 - fill the hash-table with file content");
        System.out.println("6 - change the hash-function");

        HashTable hashTable = new HashTable();

        Scanner in = new Scanner(System.in);
        int input = 0;
        do {

            input = in.nextInt();
            switch (input) {
                case 1: {
                    System.out.println("Enter the value to add");
                    int value = in.nextInt();
                    try {
                        hashTable.addElement(value);
                        System.out.println("The value is added");
                    } catch (IllegalArgumentException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter the value to remove");
                    int value = in.nextInt();
                    try {
                        hashTable.removeElement(value);
                        System.out.println("The value is removed");
                    } catch (IllegalArgumentException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter the value");
                    int value = in.nextInt();
                    System.out.println("The value " + (hashTable.exists(value) ? "exists" : "doesn't exist") + " in the hash-table");
                    break;
                }
                case 4:
                    hashTable.showStatistics();
                    break;
                case 5:
                    String fileName = "text.txt";
                    try {
                        hashTable.fillFromFile(fileName);
                        System.out.println("Hash-table is filled");
                    } catch (FileNotFoundException exc) {
                        System.out.println("File not found");
                    } catch (IllegalArgumentException exc) {
                        System.out.println("Value which already exists in hash-table is met, filling had break");
                    } catch (IOException exc) {
                        System.out.println("Input/Output error");
                    }
                    break;
                case 6: {
                    System.out.println("Enter the number of hash-function (0 or 1)");
                    int value = in.nextInt();
                    try {
                        hashTable.changeHashFunction(value);
                        System.out.println("The hash-function is changed");
                    } catch (IllegalArgumentException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);
    }
}
