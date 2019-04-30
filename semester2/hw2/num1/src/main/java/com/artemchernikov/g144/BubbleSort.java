package com.artemchernikov.g144;

/**A class describing bubble sort*/
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    //swap
                    int buff = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = buff;
                }
            }
        }
    }

}
