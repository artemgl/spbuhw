package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void enqueueAndDequeue() throws Exception {
        int[][] NumbersWithPriorities = {
                {425, 624, 784, 5367, 774, 5, 341, 4523, 82, 534, 99},
                {753, 32, 241, 46, 42, 667, 87, 842, 421, 34, 5}
        };
        int[] expectedNumbers = {4523, 425, 5, 82, 784, 341, 5367, 774, 534, 624, 99};

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < NumbersWithPriorities[0].length; i++) {
            priorityQueue.enqueue(NumbersWithPriorities[0][i], NumbersWithPriorities[1][i]);
        }

        int [] actualNumbers = new int[expectedNumbers.length];
        for (int i = 0; i < actualNumbers.length; i++) {
            actualNumbers[i] = priorityQueue.dequeue();
        }

        assertArrayEquals(expectedNumbers, actualNumbers);
    }

    @Test
    void dequeueIfPriorityQueueIsEmpty() {
        PriorityQueue queue = new PriorityQueue();
        assertThrows(Exception.class, queue::dequeue);
    }
}