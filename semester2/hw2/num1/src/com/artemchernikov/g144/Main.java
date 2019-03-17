package com.artemchernikov.g144;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Sort[] sorters = new Sort[3];
        sorters[0] = new QuickSort();
        sorters[1] = new HeapSort();
        sorters[2] = new MergeSort();

        int[] numbers = new int[15];

        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = rand.nextInt(30);
            }

            System.out.println("Before:");
            for (int number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println();

            sorters[i].ascending(numbers);

            System.out.println("After:");
            for (int number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println("\n");
        }
    }
}
