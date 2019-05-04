package com.artemchernikov.g144;

/**A class describing displaying 2-dimensional array to console*/
public class OutputerToConsole extends AbstractOutputer {

    @Override
    public String output(int[][] numbers) {
        return output(numbers, System.out);
    }

}
