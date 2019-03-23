package com.artemchernikov.g144;

/**A class describing stack*/
public class Stack {

    /**A class describing element of stack*/
    public class StackElement {
        public StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }

        public int value;
        public StackElement next;
    }

    private StackElement first;
    private int size;

    public Stack() {
        this.first = null;
        this.size = 0;
    }

    public int pop() {
        if (!isEmpty()) {
            int result = first.value;
            first = first.next;

            this.size--;
            return result;
        }

        return 0;
    }

    public void push(int value) {
        first = new StackElement(value, first);
        this.size++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return this.size;
    }

}
