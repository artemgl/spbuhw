package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueListTest {

    @Test
    void shouldThrowAlreadyExistsElementExceptionTest() {
        UniqueList<Integer> list = new UniqueList<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            list.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            final int j = i;
            assertThrows(AlreadyExistsElementException.class, () -> list.addElement(j));
        }
    }

    @Test
    void shouldNotAddAlreadyExistingElementTest() {
        UniqueList<Integer> list = new UniqueList<>();

        int size = 20;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    list.addElement(j);
                } catch (AlreadyExistsElementException ignore) {}
            }
        }

        assertEquals(20, list.getSize());
    }

    @Test
    void shouldThrowNoElementExceptionTest() {
        UniqueList<Integer> list = new UniqueList<>();

        int size = 20;
        for (int i = 0; i < size; i++) {
            final int j = i;
            assertThrows(NoElementException.class, () -> list.removeElement(j));
        }
    }

}