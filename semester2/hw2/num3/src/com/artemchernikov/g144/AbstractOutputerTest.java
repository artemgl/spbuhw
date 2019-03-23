package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractOutputerTest {

    @Test
    void getString() {
        int[][] numbers = {
                {0,  1,  2,  3,  4},
                {5,  6,  7,  8,  9},
                {10, 11, 12, 13, 14},
                {15, 16, 17, 18, 19},
                {20, 21, 22, 23, 24}};

        String exceptedString = "12, 13, 18, 17, 16, 11, 6, 7, 8, 9, 14, 19, 24, 23, 22, 21, 20, 15, 10, 5, 0, 1, 2, 3, 4, ";

        AbstractOutputer outputer = new AbstractOutputer() {
            @Override
            public void output(int[][] numbers) {

            }
        };

        assertEquals(exceptedString, outputer.getString(numbers));
    }
}