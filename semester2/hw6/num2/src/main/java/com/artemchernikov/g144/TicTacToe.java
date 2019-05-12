package com.artemchernikov.g144;

import java.util.Arrays;

/**A class describing the game "tic-tac-toe"*/
public class TicTacToe {

    private String[] field = new String[]{
            " ", " ", " ",
            " ", " ", " ",
            " ", " ", " "
    };
    // [0] [1] [2]
    // [3] [4] [5]
    // [6] [7] [8]
    private final Line[] lines = new Line[]{
            new Line(0, 1, 2),
            new Line(3, 4, 5),
            new Line(6, 7, 8),
            new Line(0, 3, 6),
            new Line(1, 4, 7),
            new Line(2, 5, 8),
            new Line(0, 4, 8),
            new Line(2, 4, 6)
    };

    private Move crossesMove = new CrossesMove();
    private Move noughtsMove = new NoughtsMove();
    private Move currentMove = crossesMove;
    private boolean crossesWin;
    private boolean noughtsWin;
    private boolean isDraw;

    /**
     * A method returns true if crosses won and false otherwise
     * @return true if crosses won and false otherwise
     * */
    public boolean crossesWin() {
        return crossesWin;
    }

    /**
     * A method returns true if noughts won and false otherwise
     * @return true if noughts won and false otherwise
     * */
    public boolean noughtsWin() {
        return noughtsWin;
    }

    /**
     * A method returns true if it is draw and false otherwise
     * @return true if it is draw and false otherwise
     * */
    public boolean isDraw() {
        return isDraw;
    }

    /**
     * A method returns symbol of the current move
     * @return symbol of the current move
     * */
    public String getCurrentMoveSymbol() {
        return currentMove.symbol;
    }

    /**A method changes currentMove*/
    private void switchTurn() {
        currentMove = currentMove.symbol.equals("X") ? noughtsMove : crossesMove;
    }

    /**A method makes move
     * @param index index of the cell of the field in which we want to make a move
     * @return true if index is correct and false otherwise
     * */
    public boolean makeMove(int index) {
        if (crossesWin || noughtsWin || isDraw || !field[index].equals(" ")) {
            return false;
        }

        field[index] = currentMove.symbol;
        currentMove.updateIsWinner();

        if (currentMove.isWinner) {
            if (currentMove.symbol.equals("X")) {
                crossesWin = true;
            } else {
                noughtsWin = true;
            }
        } else {
            if (Arrays.stream(field).noneMatch(" "::equals)) {
                isDraw = true;
            }
        }

        switchTurn();
        return true;
    }

    /**A class describing move*/
    private class Move {
        protected String symbol;
        protected boolean isWinner;

        /**A method checks if player is winner*/
        void updateIsWinner() {
            isWinner = Arrays.stream(lines).anyMatch(line -> Arrays.stream(line.cells).allMatch(n -> field[n].equals(symbol)));
        }
    }

    /**A class describing move of crosses*/
    private class CrossesMove extends Move {
        private CrossesMove() {
            symbol = "X";
        }
    }

    /**A class describing move of noughts*/
    private class NoughtsMove extends Move {
        private NoughtsMove() {
            symbol = "O";
        }
    }

    /**A class describing line of the cells*/
    private class Line {
        private int[] cells;

        private Line(int firstCellIndex, int secondCellIndex, int thirdCellIndex) {
            cells = new int[]{firstCellIndex, secondCellIndex, thirdCellIndex};
        }
    }

}
