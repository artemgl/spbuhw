package com.artemchernikov.g144;

/**A class describing stack implemented on array*/
public class StackOnArray implements IStack {

    private int[] numbers;
    private int top;

    public StackOnArray(int maxSize) {
        numbers = new int[maxSize];
        top = -1;
    }

    /**If stack is empty, returns 0 and displays warning message*/
    @Override
    public int pop() {
        if (!isEmpty()) {
            return numbers[top--];
        }

        System.out.println("Stack is underflow");
        return 0;
    }

    @Override
    public void push(int value) {
        if (top < numbers.length) {
            numbers[++top] = value;
        } else {
            System.out.println("Stack is overflow");
        }
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

}
