package com.artemchernikov.g144;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**A class describing quick sort implemented by Fork/Join Framework*/
public class QuickSortFJP implements Sort {

    private int[] numbers;

    /**{@inheritDoc}*/
    @Override
    public void sort(int[] numbers) {
        this.numbers = numbers;
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new RecursiveSort(0, numbers.length - 1));
    }

    /**A class describing recursive action, which sorts the part of array given by two extreme indexes*/
    private class RecursiveSort extends RecursiveAction {

        private int from;
        private int to;

        private RecursiveSort(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected void compute() {
            if (from < to) {
                int mainIndex = from;
                int extraIndex = to;

                while (mainIndex != extraIndex) {
                    if (mainIndex < extraIndex && numbers[mainIndex] > numbers[extraIndex] ||
                            extraIndex < mainIndex && numbers[extraIndex] > numbers[mainIndex]) {
                        //swap
                        int buff = mainIndex;
                        mainIndex = extraIndex;
                        extraIndex = buff;

                        //swap
                        buff = numbers[mainIndex];
                        numbers[mainIndex] = numbers[extraIndex];
                        numbers[extraIndex] = buff;
                    }
                    //get close to mainIndex
                    extraIndex += mainIndex > extraIndex ? 1 : -1;
                }

                invokeAll(new RecursiveSort(from, mainIndex - 1), new RecursiveSort(mainIndex + 1, to));
            }
        }
    }

}
