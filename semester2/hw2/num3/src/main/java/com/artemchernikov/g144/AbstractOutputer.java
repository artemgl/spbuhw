package com.artemchernikov.g144;

/**An abstract class describing creating string with 2-dimensional array displayed spirally*/
public abstract class AbstractOutputer implements IOutputer {

    /**An auxiliary method returns true if creating string is over and false in otherwise*/
    private boolean isEnd(int firstCoord, int secondCoord, int size) {
        return firstCoord == 0 && secondCoord == size;
    }

    /**A method creating needed string*/
    protected String getString(int[][] numbers) {
        int n = numbers.length;
        int firstCoord = n / 2;
        int secondCoord = n / 2;

        String result = "";
        result += numbers[firstCoord][secondCoord] + ", ";

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
                result += numbers[firstCoord][secondCoord] + ", ";
            }
        }

        return result;
    }

}
