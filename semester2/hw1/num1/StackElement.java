package com.artemchernikov.g144;

/**A class describing element of stack*/
public class StackElement {
    public StackElement(int value, StackElement next) {
        this.value = value;
        this.next = next;
    }

    public int value;
    public StackElement next;
}
