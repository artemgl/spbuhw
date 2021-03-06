package com.artemchernikov.g144;

/**A template class describing singly linked list*/
public class List<T> {

    private ListElement first;
    private int size;

    /**
     * A method adds element to the head of the list
     * @param value element to add
     * */
    public void addElement(T value) {
        first = new ListElement(value, first);
        this.size++;
    }

    /**
     * A method removes element from the list, if the list contains it
     * @param value element to remove
     * */
    public void removeElement(T value) {
        if (!isEmpty()) {
            if (value.equals(first.value)) {
                first = first.next;
                this.size--;
                return;
            }

            ListElement previous = first;
            ListElement current = first.next;

            while (current != null) {
                if (value.equals(current.value)) {
                    previous.next = current.next;
                    this.size--;
                    return;
                }

                previous = current;
                current = current.next;
            }
        }
    }

    /**
     * A method returns value by index
     * @param index index of needed element
     * @throws IndexOutOfBoundsException if received index is out of bounds
     * */
    public T getElement(int index) throws IndexOutOfBoundsException {
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

    /**
     * A method checks if element exists in the list
     * @param value element to check
     * @return true if the list contains received value and false otherwise
     * */
    public boolean exists(T value) {
        ListElement current = first;
        while (current != null) {
            if (value.equals(current.value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * A method returns number of list elements
     * @return number of list elements
     * */
    public int getSize() {
        return this.size;
    }

    /**
     * A method checks if the list is empty
     * @return true if the list is empty and false otherwise
     * */
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
        private T value;
        private ListElement next;

        private ListElement(T value, ListElement next) {
            this.value = value;
            this.next = next;
        }
    }
}
