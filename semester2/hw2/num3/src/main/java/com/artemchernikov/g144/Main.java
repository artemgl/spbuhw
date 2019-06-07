package com.artemchernikov.g144;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the dimension of the 2-dimensional array");

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] numbers = new int[n][n];

        System.out.println("Array:");
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                numbers[i][j] = rand.nextInt(30);
                System.out.print(numbers[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Enter to output the array spirally: ");
        System.out.println("1 - to console");
        System.out.println("2 - to file");

        IOutputer outputer;
        switch (in.nextInt()) {
            case 1:
                outputer = new OutputerToConsole();
                break;
            case 2:
            default:
                outputer = new OutputerToFile("file.txt");
                break;
        }

        try {
            outputer.output(numbers);
        } catch (IOException exc) {
            System.out.println("Input-output error: " + exc.getMessage());
        }
    }
}
