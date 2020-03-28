package com.artemchernikov.g244.operationSystems;

/**A class describing context model of some operation system*/
public class OperationSystem {
    private float infectionChance;

    public OperationSystem(float infectionChance) {
        this.infectionChance = infectionChance;
    }

    public float getInfectionChance() {
        return infectionChance;
    }
}