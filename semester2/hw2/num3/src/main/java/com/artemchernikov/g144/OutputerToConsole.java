package com.artemchernikov.g144;

import java.io.IOException;

/**A class describing displaying 2-dimensional array to console*/
public class OutputerToConsole extends AbstractOutputer {

    /**
     * A method prints array spirally to console
     * @param numbers array to print
     * @throws IOException if writing was unsuccessful
     * */
    @Override
    public void output(int[][] numbers) throws IOException {
        output(numbers, System.out);
    }

}
