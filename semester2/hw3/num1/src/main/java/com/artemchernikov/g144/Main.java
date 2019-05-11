package com.artemchernikov.g144;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

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

        HashTable<Integer> hashTable = new HashTable<>(256);

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
                    System.out.println("Enter the number of hash-function");
                    System.out.println("0 - cancel");
                    System.out.println("1 - \n" +
                            "value ^= (value << 13);\n" +
                            "value ^= (value >>> 17);\n" +
                            "value ^= (value << 5);");
                    System.out.println("2 - \n" +
                            "value ^= (value * 7);\n" +
                            "value ^= ~(value << 11);\n" +
                            "value ^= (value >> 29);");
                    System.out.println("3 - \n" +
                            "value += value");
                    int numberOfHashFunction = in.nextInt();
                    Function<Integer, Integer> function = null;
                    switch (numberOfHashFunction) {
                        case 0:
                            break;
                        case 1:
                            function = n -> {
                                n ^= (n << 13);
                                n ^= (n >>> 17);
                                n ^= (n << 5);
                                return n;
                            };
                            break;
                        case 2:
                            function = n -> {
                                n ^= (n * 7);
                                n ^= ~(n << 11);
                                n ^= (n >> 29);
                                return n;
                            };
                            break;
                        case 3:
                            function = n -> n + n;
                            break;
                        default:
                            function = n -> n;
                    }
                    hashTable.changeHashFunction(function);
                    System.out.println("The hash-function is changed");
                    break;
                }
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);
    }
}
