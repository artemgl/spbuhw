package com.artemchernikov.g144;

/**A class describing stack implemented on array*/
public class StackOnArray implements IStack {

    int[] numbers;
    int top;

    public StackOnArray(int maxSize) {
        numbers = new int[maxSize];
        top = -1;
    }

    public int pop() {
        if (!isEmpty()) {
            return numbers[top--];
        }

        System.out.println("Stack is underflow");
        return 0;
    }

    public void push(int value) {
        if (top < numbers.length) {
            numbers[++top] = value;
        } else {
            System.out.println("Stack is overflow");
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

}
