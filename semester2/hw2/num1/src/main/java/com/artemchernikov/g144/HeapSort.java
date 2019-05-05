package com.artemchernikov.g144;

/**A class describing heap sort*/
public class HeapSort implements Sort {

    @Override
    public void sort(int[] numbers) {
        int amountOfLevels = 0;
        for (int size = numbers.length; size > 1; size--) {
            amountOfLevels = 1;
            while (!(Math.pow(2, amountOfLevels - 1) <= size && size < Math.pow(2, amountOfLevels))) {
                amountOfLevels++;
            }

            for (int i = amountOfLevels - 1; i >= 1; i--) {
                for (int j = (int)Math.pow(2, i - 1) - 1; j < (int)Math.pow(2, i) - 1; j++) {
                    int leftIndex = 2 * j + 1;
                    int rightIndex = 2 * j + 2;

                    if (leftIndex < size) {
                        if (rightIndex < size) {
                            if (numbers[leftIndex] > numbers[rightIndex]) {
                                if (numbers[leftIndex] > numbers[j]) {
                                    //swap
                                    int buff = numbers[leftIndex];
                                    numbers[leftIndex] = numbers[j];
                                    numbers[j] = buff;
                                }
                            }
                            else {
                                if (numbers[rightIndex] > numbers[j]) {
                                    //swap
                                    int buff = numbers[rightIndex];
                                    numbers[rightIndex] = numbers[j];
                                    numbers[j] = buff;
                                }
                            }
                        }
                        else {
                            if (numbers[leftIndex] > numbers[j]) {
                                //swap
                                int buff = numbers[leftIndex];
                                numbers[leftIndex] = numbers[j];
                                numbers[j] = buff;
                            }
                        }
                    }
                }
            }

            //swap
            int buff = numbers[size - 1];
            numbers[size - 1] = numbers[0];
            numbers[0] = buff;
        }
    }

}
