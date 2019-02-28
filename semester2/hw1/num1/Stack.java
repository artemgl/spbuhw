package com.artemchernikov.g144;

/**
 * A class describing stack
 */
public class Stack {

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
        if (first == null) {
            first = new StackElement(value);
            return;
        }

        StackElement current = first;
        first = new StackElement(value);
        first.next = current;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int result = 0;
        StackElement current = first;
        while (current != null) {
            result++;
            current = current.next;
        }

        return result;
    }

}
