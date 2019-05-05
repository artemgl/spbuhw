package com.artemchernikov.g144;

/**A class describing stack*/
public class Stack {

    private StackElement first;
    private int size;

    public Stack() {
        this.first = null;
        this.size = 0;
    }

    /**
     * A method returns top element and removes it from the stack
     * If the stack is empty, returns 0
     * */
    public int pop() {
        if (!isEmpty()) {
            int result = first.value;
            first = first.next;

            this.size--;
            return result;
        }

        return 0;
    }

    /**A method adds received element to the top of the stack*/
    public void push(int value) {
        first = new StackElement(value, first);
        this.size++;
    }

    /**A method returns true if stack is empty and false in otherwise*/
    public boolean isEmpty() {
        return first == null;
    }

    /**A method returns amount of stack elements*/
    public int size() {
        return this.size;
    }

    /**A class describing element of stack*/
    private class StackElement {
        private StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }

        private int value;
        private StackElement next;
    }
}
