package com.artemchernikov.g144;

import java.io.IOException;

/**An interface describing displaying 2-dimensional array*/
public interface IOutputer {

    /**
     * A method prints array
     * @param numbers array to print
     * @throws IOException if writing was unsuccessful
     * */
    void output(int[][] numbers) throws IOException;

}
