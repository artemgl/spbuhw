package com.artemchernikov.g144;

import java.io.*;

/**A class describing displaying 2-dimensional array to file*/
public class OutputerToFile extends AbstractOutputer {

    private String filePath;

    public OutputerToFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method prints array spirally to file
     * @param numbers array to print
     * @throws IOException if writing was unsuccessful
     * */
    @Override
    public void output(int[][] numbers) throws IOException {
        try (OutputStream out = new FileOutputStream(filePath)) {
            output(numbers, out);
        } catch (FileNotFoundException exc) {
            System.out.print(exc.getMessage());
        }
    }

}
