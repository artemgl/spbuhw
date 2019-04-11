package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void sort() {
        sortTest(new BubbleSort());
        sortTest(new QuickSort());
        sortTest(new HeapSort());
        sortTest(new MergeSort());
    }

    private void sortTest(Sort sorter) {
        int[][] actualNumbers = {
                {9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
                {6, 5, 8, 0, 4, 9, 3, 1, 2, 7},
                {1, 8, 3, 7, 6, 2, 5, 0, 9, 4},
                {5, 7, 6, 9, 8, 1, 0, 4, 3, 2}
        };
        int[] expectedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int[] actualArray : actualNumbers) {
            sorter.sort(actualArray);

            assertArrayEquals(expectedArray, actualArray);
        }
    }

}