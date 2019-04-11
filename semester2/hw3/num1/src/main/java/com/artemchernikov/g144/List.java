package com.artemchernikov.g144;

/**A class describing singly linked list*/
public class List {

    private ListElement first;

    private int size;
    public void addElement(int value) {
        first = new ListElement(value, first);
        this.size++;
    }

    public void removeElement(int value) {
        if (!isEmpty()) {
            if (first.value == value) {
                first = first.next;
                this.size--;
                return;
            }

            ListElement previous = first;
            ListElement current = first.next;

            while (current != null) {
                if (current.value == value) {
                    previous.next = current.next;
                    this.size--;
                    return;
                }

                previous = current;
                current = current.next;
            }
        }

        throw new IllegalArgumentException("List doesn't contain this value: " + value);
    }

    public int getElement(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < getSize()) {
            ListElement current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            return current.value;
        } else {
            throw new IndexOutOfBoundsException();
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

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void print() {
        ListElement current = first;

        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    /**A class describing element of singly linked list*/
    private class ListElement {
        private ListElement(int value, ListElement next) {
            this.value = value;
            this.next = next;
        }

        private int value;
        private ListElement next;
    }
}
