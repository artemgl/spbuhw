package com.artemchernikov.g144;

/**A class describing element of singly linked list*/
public class ListElement {
    public ListElement(int value, ListElement next) {
        this.value = value;
        this.next = next;
    }

    public int value;
    public ListElement next;
}
