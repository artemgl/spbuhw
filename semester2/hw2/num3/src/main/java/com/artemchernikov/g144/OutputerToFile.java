package com.artemchernikov.g144;

import java.io.*;

/**A class describing displaying 2-dimensional array to file*/
public class OutputerToFile extends AbstractOutputer {

    @Override
    public void output(int[][] numbers) {
        try (FileWriter fout = new FileWriter("file.txt")) {
            fout.write(getString(numbers));
            fout.flush();
        } catch(IOException exc){
            System.out.println("Ошибка ввода-вывода: " + exc.getMessage());
        }
    }

}
