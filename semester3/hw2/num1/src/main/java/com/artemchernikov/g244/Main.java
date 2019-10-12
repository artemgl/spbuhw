package com.artemchernikov.g244;

import com.artemchernikov.g244.operationSystems.Mac;
import com.artemchernikov.g244.operationSystems.Unix;
import com.artemchernikov.g244.operationSystems.Windows;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayList<Computer> computers = new ArrayList<>();
        Graph<String> connection = new Graph<>();

        Random random = new Random();

        int computersAmount = 6;
        for (int i = 0; i < computersAmount; ) {
            computers.add(new Computer(String.valueOf(i++), new Windows(), random.nextBoolean()));
            computers.add(new Computer(String.valueOf(i++), new Unix(), random.nextBoolean()));
            computers.add(new Computer(String.valueOf(i++), new Mac(), random.nextBoolean()));
        }

        int minAmountOfEdges = (computersAmount * (computersAmount - 3) + 4) / 2;
        for (int i = 0; i < minAmountOfEdges; i++) {
            int first = 0;
            int second = 0;
            do {
                first = Math.abs(random.nextInt() % computersAmount);
                second = Math.abs(random.nextInt() % computersAmount);
            } while (first == second || !connection.addRelation(computers.get(first).getName(), computers.get(second).getName()));
        }

        Network network = new Network(computers, connection);
        network.printConnection();
        network.printState();
        System.out.println();

        while (!network.areAllInfected()) {
            network.takeStep();
        }

    }

}
