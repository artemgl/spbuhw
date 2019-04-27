package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate() {
        assertEquals(-1128.6, Calculator.calculate("-2*564.3"));
        assertEquals(5.698504111705881E20, Calculator.calculate("5.698504111705882E18*100"));
        assertEquals(-1692, Calculator.calculate("423*-4"));
        assertEquals(112, Calculator.calculate("(5+23)*4"));
    }
}