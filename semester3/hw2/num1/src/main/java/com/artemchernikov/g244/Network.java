package com.artemchernikov.g244;

import java.util.ArrayList;

/**A class describing context model of local network*/
public class Network {

    private ArrayList<Computer> computers;
    private Graph<String> connection;

    public Network(ArrayList<Computer> computers, Graph<String> connection) {
        this.connection = connection;
        this.computers = computers;
    }

    /**
     * A method checks if all the computers in network are infected
     * @return true if all the computers are infected, and false otherwise
     * */
    public boolean areAllInfected() {
        for (Computer computer : computers) {
            if (!computer.isInfected()) {
                return false;
            }
        }
        return true;
    }

    /**A method chooses one of the uninfected computers, tries to infect it and displays outcome to console*/
    public void takeStep() {
        Computer currentComputer = computers.stream().filter(c -> !c.isInfected()).findAny().orElse(null);
        if (currentComputer == null) {
            System.out.println("All the computers are infected!");
            return;
        }

        computers.stream()
                .filter(c -> connection.areRelated(currentComputer.getName(), c.getName()))
                .filter(Computer::isInfected)
                .findAny()
                .ifPresent(c -> currentComputer.infect());

        System.out.println("Computer " + currentComputer.getName() + " has " + (currentComputer.isInfected() ? "" : "not ") + "been infected");
    }

    /**A method displays state of network to console*/
    public void printState() {
        for (Computer computer : computers) {
            System.out.println("Computer " + computer.getName() + " is " + (computer.isInfected() ? "" : "not ") + "infected");
        }
    }

    /**A method displays connection to console*/
    public void printConnection() {
        connection.print();
    }

}
