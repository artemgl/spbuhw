package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate() {
        Calculator calculator = new Calculator();
        assertEquals(9, calculator.calculate("6+3"));
        assertEquals(15, calculator.calculate("3*(7-2)"));
        assertEquals(2, calculator.calculate("(9+7)/(8-2)"));
    }
}