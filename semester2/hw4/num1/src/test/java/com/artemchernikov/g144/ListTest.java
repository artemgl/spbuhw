package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    @Test
    void addElementAndGetElement() {
        List<Integer> list = new List<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            list.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            assertEquals(size - i - 1, (int)list.getElement(i));
        }
    }

    @Test
    void removeElementAndGetSize() {
        List<Integer> list = new List<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            list.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            list.removeElement(i);
            assertEquals(size - 1 - i, list.getSize());
        }
    }

    @Test
    void exists() {
        List<Integer> list = new List<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            list.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            assertTrue(list.exists(i));
            assertFalse(list.exists(size + i));
        }
    }
}