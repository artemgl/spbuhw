package com.artemchernikov.g144;

import java.io.*;

/**A class describing displaying 2-dimensional array to file*/
public class OutputerToFile extends AbstractOutputer {

    /**If incorrect stream was received, returns empty string*/
    @Override
    public String output(int[][] numbers) {
        try (OutputStream out = new FileOutputStream("file.txt")) {
            return output(numbers, out);
        } catch (IOException exc) {
            System.out.print(exc.getMessage());
            return "";
        }
    }

}
