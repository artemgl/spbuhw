package com.artemchernikov.g244;

import com.artemchernikov.g244.operationSystems.OperationSystem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    public void computerInfection() {
        Computer firstComputer = new Computer("First", new OperationSystem(1.0f));
        Computer secondComputer = new Computer("Second", new OperationSystem(1.0f));
        Computer thirdComputer = new Computer("Third", new OperationSystem(1.0f));

        firstComputer.infect();

        ArrayList<Computer> computers = new ArrayList<>();
        computers.add(firstComputer);
        computers.add(secondComputer);
        computers.add(thirdComputer);

        Graph<String> connection = new Graph<>();
        connection.addRelation(firstComputer.getName(), secondComputer.getName());
        connection.addRelation(firstComputer.getName(), thirdComputer.getName());

        //[Third (uninfected)]-[First (infected)]-[Second (uninfected)]
        Network network = new Network(computers, connection);

        network.takeStep();
        assertTrue(secondComputer.isInfected() | thirdComputer.isInfected());
        assertFalse(secondComputer.isInfected() & thirdComputer.isInfected());

        network.takeStep();
        assertTrue(secondComputer.isInfected());
        assertTrue(thirdComputer.isInfected());
    }

}