package com.artemchernikov.g144;

//A class describing merge sort
public class MergeSort implements Sort {

    private void ascending(int[] numbers, int firstIndex, int secondIndex) {

        int[] bufferArray = numbers.clone();

        int leftIndex = firstIndex;
        int rightIndex = secondIndex;

        for (int i = 0; i < (secondIndex - firstIndex) * 2; i++) {
            if (bufferArray[leftIndex] < bufferArray[rightIndex]) {
                numbers[firstIndex + i] = bufferArray[leftIndex++];
            }
            else {
                numbers[firstIndex + i] = bufferArray[rightIndex++];
            }

            if (rightIndex >= numbers.length || rightIndex == 2 * secondIndex - firstIndex) {
                for (int j = i + 1; leftIndex < secondIndex; j++) {
                    numbers[firstIndex + j] = bufferArray[leftIndex++];
                }
                break;
            }

            if (leftIndex == secondIndex) {
                for (int j = i + 1; rightIndex < numbers.length && rightIndex < 2 * secondIndex - firstIndex; j++) {
                    numbers[firstIndex + j] = bufferArray[rightIndex++];
                }
                break;
            }
        }
    }

    public void ascending(int[] numbers) {
        for (int i = 1; i < numbers.length; i *= 2) {
            for (int j = 0; j < numbers.length - i; j += 2 * i) {
                ascending(numbers, j, j + i);
            }
        }
    }

}
