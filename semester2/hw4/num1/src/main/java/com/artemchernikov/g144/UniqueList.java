package com.artemchernikov.g144;

/**A template class describing singly linked list*/
public class UniqueList<T> {

    /**A class describing element of singly linked list*/
    private class ListElement {
        private ListElement(T value, ListElement next) {
            this.value = value;
            this.next = next;
        }

        private T value;
        private ListElement next;
    }

    private ListElement first;
    private int size;

    public void addElement(T value) {
        if (!exists(value)) {
            first = new ListElement(value, first);
            this.size++;
        } else {
            throw new IllegalArgumentException("List already contains this value: " + value);
        }
    }

    public void removeElement(T value) {
        if (!isEmpty()) {
            if (first.value.equals(value)) {
                first = first.next;
                this.size--;
                return;
            }

            ListElement previous = first;
            ListElement current = first.next;

            while (current != null) {
                if (current.value.equals(value)) {
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

    public boolean exists(T value) {
        ListElement current = first;
        while (current != null) {
            if (current.value.equals(value)) {
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
}
