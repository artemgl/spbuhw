package com.artemchernikov.g144;

/**
 * A class describing element of stack
 */
public class StackElement {
    public StackElement(int value) {
        this.value = value;
    }

    public int value;
    public StackElement next;
}
