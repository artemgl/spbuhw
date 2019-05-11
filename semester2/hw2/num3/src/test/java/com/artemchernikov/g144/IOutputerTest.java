package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class IOutputerTest {

    @Test
    void outputerToConsoleTest() throws IOException {
        System.setOut(new PrintStream(outContent));

        IOutputer outputer = new OutputerToConsole();
        outputer.output(numbers);

        System.setOut(null);

        assertEquals(exceptedString, outContent.toString());
    }

    @Test
    void outputerToFileTest() throws IOException {
        String filePath = "file.txt";

        IOutputer outputer = new OutputerToFile(filePath);
        outputer.output(numbers);

        FileReader in = new FileReader(filePath);
        Scanner scanner = new Scanner(in);

        assertEquals(exceptedString, scanner.nextLine());
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final int[][] numbers = {
            {0,  1,  2,  3,  4},
            {5,  6,  7,  8,  9},
            {10, 11, 12, 13, 14},
            {15, 16, 17, 18, 19},
            {20, 21, 22, 23, 24}};
    private final String exceptedString = "12, 13, 18, 17, 16, 11, 6, 7, 8, 9, 14, 19, 24, 23, 22, 21, 20, 15, 10, 5, 0, 1, 2, 3, 4, ";

}

