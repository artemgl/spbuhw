package com.artemchernikov.g144;

//A class describing quick sort
public class QuickSort implements Sort {

    private void ascending(int[] numbers, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int mainIndex = leftIndex;
        int extraIndex = rightIndex;

        while (mainIndex != extraIndex) {
            if (mainIndex < extraIndex && numbers[mainIndex] > numbers[extraIndex] ||
                    extraIndex < mainIndex && numbers[extraIndex] > numbers[mainIndex]) {
                //swap
                numbers[mainIndex] += numbers[extraIndex] - (numbers[extraIndex] = numbers[mainIndex]);
                mainIndex += extraIndex - (extraIndex = mainIndex);
            }
            //get close to mainIndex
            extraIndex += (int)Math.signum(mainIndex - extraIndex);
        }

        ascending(numbers, leftIndex, mainIndex - 1);
        ascending(numbers, mainIndex + 1, rightIndex);
    }

    public void ascending(int[] numbers) {
        ascending(numbers, 0, numbers.length - 1);
    }

}
