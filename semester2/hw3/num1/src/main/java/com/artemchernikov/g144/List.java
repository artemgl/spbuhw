package com.artemchernikov.g144;

/**A class describing singly linked list*/
public class List {

    private ListElement first;
    private int size;

    /**A method adds received value to the head of the list*/
    public void addElement(int value) {
        first = new ListElement(value, first);
        this.size++;
    }

    /**
     * A method removes received value from the list
     * If the list doesn't contain it, throws IllegalArgumentException
     * */
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

    /**
     * A method returns value by received index
     * If index is out of bounds, throws IndexOutOfBoundsException
     * */
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

    /**A method returns true if the list contains received value and false in otherwise*/
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

    /**A method returns amount of list elements*/
    public int getSize() {
        return this.size;
    }

    /**A method returns true if the list is empty and false in otherwise*/
    public boolean isEmpty() {
        return first == null;
    }

    /**A method displays the list*/
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
