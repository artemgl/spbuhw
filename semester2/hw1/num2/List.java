package com.artemchernikov.g144;

/**
 * A class describing singly linked list
 */
public class List {

    private ListElement first;

    public void addElement(int value) {
        if (first == null) {
            first = new ListElement(value);
            return;
        }

        ListElement current = first;
        first = new ListElement(value);
        first.next = current;
    }

    public void removeElement(int value) {
        if (first.value == value) {
            first = first.next;
            return;
        }

        ListElement current = first;

        while (current.next != null) {
            if (current.next.value == value) {
                current.next = current.next.next;
                return;
            }

            current = current.next;
        }
    }

    public boolean exists(int value) {
        ListElement current = first;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void print() {
        ListElement current = first;

        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
