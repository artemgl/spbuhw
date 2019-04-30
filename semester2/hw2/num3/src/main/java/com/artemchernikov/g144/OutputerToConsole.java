package com.artemchernikov.g144;

/**A class describing displaying 2-dimensional array to console*/
public class OutputerToConsole extends AbstractOutputer {

    @Override
    public void output(int[][] numbers) {
        System.out.println(getString(numbers));
    }

}
