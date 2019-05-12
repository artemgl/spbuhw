package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    private TicTacToe ticTacToe;

    @Test
    void crossesShouldWin() {
        ticTacToe = new TicTacToe();

        ticTacToe.makeMove(0);
        ticTacToe.makeMove(4);
        ticTacToe.makeMove(8);
        ticTacToe.makeMove(2);
        ticTacToe.makeMove(6);
        ticTacToe.makeMove(7);
        ticTacToe.makeMove(3);

        // [X] [ ] [O]
        // [X] [O] [ ]
        // [X] [O] [X]
        assertTrue(ticTacToe.crossesWin());
        assertFalse(ticTacToe.noughtsWin());
        assertFalse(ticTacToe.isDraw());
    }

    @Test
    void noughtsShouldWin() {
        ticTacToe = new TicTacToe();

        ticTacToe.makeMove(1);
        ticTacToe.makeMove(4);
        ticTacToe.makeMove(7);
        ticTacToe.makeMove(0);
        ticTacToe.makeMove(8);
        ticTacToe.makeMove(6);
        ticTacToe.makeMove(3);
        ticTacToe.makeMove(2);

        // [O] [X] [O]
        // [X] [O] [ ]
        // [O] [X] [X]
        assertTrue(ticTacToe.noughtsWin());
        assertFalse(ticTacToe.crossesWin());
        assertFalse(ticTacToe.isDraw());
    }

    @Test
    void shouldBeDraw() {
        ticTacToe = new TicTacToe();

        ticTacToe.makeMove(0);
        ticTacToe.makeMove(4);
        ticTacToe.makeMove(5);
        ticTacToe.makeMove(1);
        ticTacToe.makeMove(7);
        ticTacToe.makeMove(6);
        ticTacToe.makeMove(2);
        ticTacToe.makeMove(8);
        ticTacToe.makeMove(3);

        // [X] [O] [X]
        // [X] [O] [X]
        // [O] [X] [O]
        assertFalse(ticTacToe.noughtsWin());
        assertFalse(ticTacToe.crossesWin());
        assertTrue(ticTacToe.isDraw());
    }

    @Test
    void shouldProcessDoubleWin() {
        ticTacToe = new TicTacToe();

        ticTacToe.makeMove(0);
        ticTacToe.makeMove(1);
        ticTacToe.makeMove(2);
        ticTacToe.makeMove(5);
        ticTacToe.makeMove(8);
        ticTacToe.makeMove(7);
        ticTacToe.makeMove(6);
        ticTacToe.makeMove(3);
        ticTacToe.makeMove(4);

        // [X] [O] [X]
        // [O] [X] [O]
        // [X] [O] [X]
        assertTrue(ticTacToe.crossesWin());
        assertFalse(ticTacToe.noughtsWin());
        assertFalse(ticTacToe.isDraw());
    }

}