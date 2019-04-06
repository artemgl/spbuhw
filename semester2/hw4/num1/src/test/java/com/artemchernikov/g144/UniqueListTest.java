package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueListTest {

    @Test
    void addElementAndGetElement() {
        UniqueList<Integer> list = new UniqueList<>();

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
        UniqueList<Integer> list = new UniqueList<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            list.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            list.removeElement(i);
            assertEquals(size - 1 - i, list.getSize());
        }

        assertEquals(0, list.getSize());
    }

    @Test
    void exists() {
        UniqueList<Integer> list = new UniqueList<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            list.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            assert(list.exists(i));
            assert(!list.exists(size + i));
        }
    }
}