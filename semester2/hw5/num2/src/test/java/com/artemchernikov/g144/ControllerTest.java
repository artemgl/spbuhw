package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static java.lang.Float.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.artemchernikov.g144.Controller.*;

class ControllerTest {

    @Test
    void calculateTest() {
        assertEquals(151.259, calculateExpression(52.947, 98.312, "+"), 0.0001);
        assertEquals(61.968, calculateExpression(82.973, 21.005, "-"), 0.0001);
        assertEquals(51.46, calculateExpression(6.2, 8.3, "*"), 0.0001);
        assertEquals(1.75, calculateExpression(7.0, 4.0, "/"), 0.0001);

        assertEquals(NaN, calculateExpression(0, 0, "/"));
        assertEquals(POSITIVE_INFINITY, calculateExpression(1, 0, "/"));
        assertEquals(NEGATIVE_INFINITY, calculateExpression(-1, 0, "/"));
    }
}