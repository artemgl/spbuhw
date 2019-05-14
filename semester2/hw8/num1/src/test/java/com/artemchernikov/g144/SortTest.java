package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void sortTest() {
        sort(new QuickSort());
        sort(new QuickSortFJP());
    }

    void sort(Sort sorter) {
        int sizeOfArray = 1_000_000;
        int amountOfChecks = 5;
        for (int i = 0; i < amountOfChecks; i++) {
            int[] numbers = new int[sizeOfArray];
            fill(numbers);
            sorter.sort(numbers);
            assertTrue(isSorted(numbers));
        }
    }

    private void fill(int[] numbers) {
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
    }

    private boolean isSorted(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }

}