package com.artemchernikov.g144;

import java.io.IOException;
import java.io.OutputStream;

/**An abstract class describing creating string with 2-dimensional array displayed spirally*/
public abstract class AbstractOutputer implements IOutputer {

    /**An auxiliary method returns true if outputing is over and false in otherwise*/
    private boolean isEnd(int firstCoord, int secondCoord, int size) {
        return firstCoord == 0 && secondCoord == size;
    }

    /**
     * A method outputs spirally received array to received stream and returns created string
     * If incorrect stream was received, returns empty string
     * */
    protected String output(int[][] numbers, OutputStream out) {
        int n = numbers.length;
        int firstCoord = n / 2;
        int secondCoord = n / 2;

        try {
            StringBuilder result = new StringBuilder(numbers[firstCoord][secondCoord] + ", ");

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
                    result.append(numbers[firstCoord][secondCoord]);
                    result.append(", ");

                    out.write(Integer.toString(numbers[firstCoord][secondCoord]).getBytes());
                    out.write(new byte[]{',', ' '});
                }
            }

            return result.toString();

        } catch (IOException exc) {
            System.out.print("Input-output error: " + exc.getMessage());
            return "";
        }
    }

}
