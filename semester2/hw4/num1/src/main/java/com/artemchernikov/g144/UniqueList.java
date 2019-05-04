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
public class UniqueList<T> extends List<T> {

    /**
     * A method adds received value to the head of the list
     * If this value already exists in the list, throws exception AlreadyExistsElementException
     * */
    @Override
    public void addElement(T value) {
        if (exists(value)) {
            throw new AlreadyExistsElementException("List already contains this value: " + value);
        }
        super.addElement(value);
    }

    /**
     * A method removes received value from the list
     * If the list doesn't contain it, throws NoElementException
     * */
    @Override
    public void removeElement(T value) {
        if (!exists(value)) {
            throw new NoElementException("List doesn't contain this value: " + value);
        }
        super.removeElement(value);
    }
}
