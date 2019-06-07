package com.artemchernikov.g144;

/**A template class describing singly linked list, which doesn't contain the same elements*/
public class UniqueList<T> extends List<T> {

    /**
     * {@inheritDoc}
     * @throws AlreadyExistsElementException if the element already exists in the list
     * */
    @Override
    public void addElement(T value) {
        if (exists(value)) {
            throw new AlreadyExistsElementException("List already contains this value: " + value);
        }
        super.addElement(value);
    }

    /**
     * {@inheritDoc}
     * @throws NoElementException if the element doesn't exist in the list
     * */
    @Override
    public void removeElement(T value) {
        if (!exists(value)) {
            throw new NoElementException("List doesn't contain this value: " + value);
        }
        super.removeElement(value);
    }
}
