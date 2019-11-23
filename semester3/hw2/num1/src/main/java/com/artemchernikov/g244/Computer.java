package com.artemchernikov.g244;

import com.artemchernikov.g244.operationSystems.OperationSystem;

import java.util.Random;

/**A class describing context model of computer*/
public class Computer {
    private OperationSystem operationSystem;
    private boolean isInfected;
    private String name;

    public Computer(String name, OperationSystem operationSystem, boolean isInfected) {
        this.name = name;
        this.operationSystem = operationSystem;
        this.isInfected = isInfected;
    }

    public Computer(String name, OperationSystem operationSystem) {
        this(name, operationSystem, false);
    }

    public boolean isInfected() {
        return isInfected;
    }

    /**A method infects computer with chance according to its operation system*/
    public void infect() {
        infect((new Random()).nextDouble());
    }

    /**
     * A method infects computer with chance according to its operation system
     * @param key key of generating chance
     * */
    public void infect(double key) {
        if (isInfected) {
            return;
        }

        if (key <= operationSystem.getInfectionChance()) {
            isInfected = true;
        }
    }

    public String getName() {
        return name;
    }
}