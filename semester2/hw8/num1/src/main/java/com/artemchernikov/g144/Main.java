package com.artemchernikov.g144;

import java.util.Random;

public class Main {
    private static final int SIZE_OF_ARRAY = 1_000_000;

    public static void main(String[] args) {
        QuickSort singleThreadedSort = new QuickSort();
        QuickSortFJP multiThreadedSort = new QuickSortFJP();

        int[] numbers1 = new int[SIZE_OF_ARRAY];
        Random r = new Random();
        for (int i = 0; i < numbers1.length; i++) {
            numbers1[i] = r.nextInt();
        }
        int[] numbers2 = numbers1.clone();

        long startAt = System.nanoTime();
        singleThreadedSort.sort(numbers1);
        long singleThreadedTime = System.nanoTime() - startAt;

        startAt = System.nanoTime();
        multiThreadedSort.sort(numbers2);
        long multiThreadedTime = System.nanoTime() - startAt;

        System.out.printf("The multithreaded version run %.1f times faster than single-threaded",
                (double)singleThreadedTime / multiThreadedTime);
    }
}
