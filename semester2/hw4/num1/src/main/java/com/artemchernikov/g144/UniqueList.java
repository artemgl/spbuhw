package com.artemchernikov.g144;

/**A class describing exception that is thrown when such element already exists*/
class AlreadyExistsElementException extends IllegalArgumentException {
    AlreadyExistsElementException(String message) {
        super(message);
    }
}

/**A class describing exception that is thrown when such element doesn't exist*/
class NoElementException extends IllegalArgumentException {
    NoElementException(String message) {
        super(message);
    }
}

/**A template class describing singly linked list*/
public class UniqueList<T> {

    private ListElement first;
    private int size;

    /**
     * A method adds received value to the head of the list
     * If this value already exists in the list, throws exception AlreadyExistsElementException
     * */
    public void addElement(T value) {
        if (!exists(value)) {
            first = new ListElement(value, first);
            this.size++;
        } else {
            throw new AlreadyExistsElementException("List already contains this value: " + value);
        }
    }

    /**
     * A method removes received value from the list
     * If the list doesn't contain it, throws NoElementException
     * */
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

        throw new NoElementException("List doesn't contain this value: " + value);
    }

    /**
     * A method returns value by received index
     * If index is out of bounds, throws IndexOutOfBoundsException
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

    /**A method returns true if the list contains received value and false in otherwise*/
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
        private ListElement(T value, ListElement next) {
            this.value = value;
            this.next = next;
        }

        private T value;
        private ListElement next;
    }
}
