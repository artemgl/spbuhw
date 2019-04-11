package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IStackTest {

    @Test
    void popAndPush() {
        popAndPushTest(new StackOnArray(9));
        popAndPushTest(new StackOnLinkedList());
    }

    private void popAndPushTest(IStack stack) {
        int[] exceptedNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actualNumbers = new int[9];

        for (int number : exceptedNumbers) {
            stack.push(number);
        }

        for (int i = actualNumbers.length - 1; i >= 0; i--) {
            actualNumbers[i] = stack.pop();
        }

        assertArrayEquals(exceptedNumbers, actualNumbers);
    }

    @Test
    void popWhenStackIsEmpty() {
        popWhenStackIsEmptyTest(new StackOnLinkedList());
        popWhenStackIsEmptyTest(new StackOnArray(1));
    }

    private void popWhenStackIsEmptyTest(IStack stack) {
        int number = stack.pop();
        assertEquals(0, number);
    }

}