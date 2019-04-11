package com.artemchernikov.g144;

/**A class describing quick sort*/
public class QuickSort implements Sort {

    private void sort(int[] numbers, int leftIndex, int rightIndex) {
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

        sort(numbers, leftIndex, mainIndex - 1);
        sort(numbers, mainIndex + 1, rightIndex);
    }

    public void sort(int[] numbers) {
        sort(numbers, 0, numbers.length - 1);
    }

}
