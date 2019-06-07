package com.artemchernikov.g144;

import java.io.IOException;
import java.io.OutputStream;

/**An abstract class describing essence displaying 2-dimensional array spirally*/
public abstract class AbstractOutputer implements IOutputer {

    /**An auxiliary method returns true if outputing is over and false otherwise*/
    private boolean isEnd(int firstCoord, int secondCoord, int size) {
        return firstCoord == 0 && secondCoord == size;
    }

    /**
     * A method outputs array spirally to stream
     * @param numbers array to print
     * @param out stream to write to
     * @throws IOException if writing was unsuccessful
     * */
    protected void output(int[][] numbers, OutputStream out) throws IOException {
        int n = numbers.length;
        int firstCoord = n / 2;
        int secondCoord = n / 2;

        out.write(Integer.toString(numbers[firstCoord][secondCoord]).getBytes());
        out.write(new byte[]{',', ' '});

        for (int i = 0; !isEnd(firstCoord, secondCoord, n); i++) {
            int step = (i + 2) / 2;

            for (int j = 0; j < step; j++) {
                switch (i % 4) {
                    case 0:
                        secondCoord += 1;
                        break;
                    case 1:
                        firstCoord += 1;
                        break;
                    case 2:
                        secondCoord -= 1;
                        break;
                    case 3:
                        firstCoord -= 1;
                        break;
                }

                if (isEnd(firstCoord, secondCoord, n)) {
                    break;
                }

                out.write(Integer.toString(numbers[firstCoord][secondCoord]).getBytes());
                out.write(new byte[]{',', ' '});
            }
        }
    }

}
