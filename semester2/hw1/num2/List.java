package com.artemchernikov.g144;

/**A class describing singly linked list*/
public class List {

    /**A class describing element of singly linked list*/
    public class ListElement {
        public ListElement(int value, ListElement next) {
            this.value = value;
            this.next = next;
        }

        public int value;
        public ListElement next;
    }

    private ListElement first;

    public void addElement(int value) {
        first = new ListElement(value, first);
    }

    public void removeElement(int value) {
        if (!isEmpty()) {
            if (first.value == value) {
                first = first.next;
                return;
            }

            ListElement previous = first;
            ListElement current = first.next;

            while (current != null) {
                if (current.value == value) {
                    previous.next = current.next;
                    return;
                }

                previous = current;
                current = current.next;
            }
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
