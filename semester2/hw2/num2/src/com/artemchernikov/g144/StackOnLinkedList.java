package com.artemchernikov.g144;

/**A class describing stack implemented on linked list*/
public class StackOnLinkedList implements IStack {

    /**A class describing an element of stack*/
    public class StackElement {
        public StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }

        public int value;
        public StackElement next;
    }

    private StackElement first;

    public int pop() {
        if (!isEmpty()) {
            int result = first.value;
            first = first.next;

            return result;
        }

        return 0;
    }

    public void push(int value) {
        first = new StackElement(value, first);
    }

    public boolean isEmpty() {
        return first == null;
    }

}
