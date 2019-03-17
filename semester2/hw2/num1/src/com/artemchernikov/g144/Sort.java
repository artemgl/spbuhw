package com.artemchernikov.g144;

//An interface describing sort
public interface Sort {

    //Bubble sort
    default void ascending(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    //swap
                    numbers[j] += numbers[j + 1] - (numbers[j + 1] = numbers[j]);
                }
            }
        }
    }

}
