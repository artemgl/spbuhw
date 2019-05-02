package com.artemchernikov.g144;

/**An interface describing stack*/
public interface IStack {

    /**A method returns top element and removes it from the stack*/
    int pop();

    /**A method adds received element to the top of the stack*/
    void push(int value);

    /**A method returns true if stack is empty and false in otherwise*/
    boolean isEmpty();

}
