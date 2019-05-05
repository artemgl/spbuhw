package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SyntaxTreeTest {

    @Test
    void calculate() {
        SyntaxTree tree = new SyntaxTree();
        assertEquals(9, tree.calculate("(+ 6 3)"));
        assertEquals(15, tree.calculate("(* 3 (- 7 2))"));
        assertEquals(2, tree.calculate("(/ (+ 9 7) (- 8 2))"));
    }
}