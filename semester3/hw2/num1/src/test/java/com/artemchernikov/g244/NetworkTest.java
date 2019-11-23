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

    @Test
    public void computerShouldNotBeInfectedIfKeyEqualsOne() {
        Computer computer = new Computer("computer", new OperationSystem(0.5f));
        computer.infect(1.0f);

        assertFalse(computer.isInfected());
    }

    @Test
    public void computerShouldBeInfectedIfInfectionChanceEqualsOne() {
        Computer computer = new Computer("computer", new OperationSystem(1.0f));
        computer.infect(1.0f);

        assertTrue(computer.isInfected());
    }

    @Test
    public void computerInfectionWithNoOneHundredPercentChance() {
        Computer firstComputer = new Computer("First", new OperationSystem(0.5f));
        Computer secondComputer = new Computer("Second", new OperationSystem(1.0f / 3.0f));
        Computer thirdComputer = new Computer("Third", new OperationSystem(0.2f));

        firstComputer.infect(0.0f);

        ArrayList<Computer> computers =  new ArrayList<>();
        computers.add(firstComputer);
        computers.add(secondComputer);
        computers.add(thirdComputer);

        Graph<String> connection = new Graph<>();
        connection.addRelation(firstComputer.getName(), secondComputer.getName());
        connection.addRelation(firstComputer.getName(), thirdComputer.getName());

        //[Third (uninfected)]-[First (infected)]-[Second (uninfected)]
        Network network = new Network(computers, connection);

        network.takeStep(0.6f);
        assertFalse(secondComputer.isInfected());

        network.takeStep(0.3f);
        assertTrue(secondComputer.isInfected());

        network.takeStep(0.3f);
        assertFalse(thirdComputer.isInfected());

        network.takeStep(0.2f);
        assertTrue(thirdComputer.isInfected());
    }

}