package com.artemchernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;


public class Controller {

    private Move crossesMove = new CrossesMove();
    private Move noughtsMove = new NoughtsMove();
    private Move currentMove = crossesMove;
    private boolean gameOver = false;

    @FXML
    private Label headline;

    public void initialize() {
        headline.setText(crossesMove.headlineWhileMove);
    }

    /**A method changes currentMove and headline*/
    private void switchTurn() {
        currentMove = currentMove.symbol.equals("X") ? noughtsMove : crossesMove;
        if (!gameOver) {
            headline.setText(currentMove.headlineWhileMove);
        }
    }

    /**
     * A method updates winFactor in currentMove according with made move
     * The move is determined by received button
     * */
    private void updateWinFactor(Button button) {
        currentMove.winFactor += 1 << getButtonNumber(button);
    }

    /**A method returns number of the received button*/
    private int getButtonNumber(Button button) {
        switch (button.getId()) {
            case "btn0":
                return 0;
            case "btn1":
                return 1;
            case "btn2":
                return 2;
            case "btn3":
                return 3;
            case "btn4":
                return 4;
            case "btn5":
                return 5;
            case "btn6":
                return 6;
            case "btn7":
                return 7;
            case "btn8":
                return 8;
            default:
                throw new IllegalArgumentException("Unknown button");
        }
    }

    /**
     * A method processes made move:
     * checks if current player is winner,
     * if not - checks if it is draw.
     * In case of game over displays suitable headline,
     * in otherwise - switches turn
     * */
    public void makeMove(ActionEvent actionEvent) {
        if (!gameOver) {
            Button pressedButton = (Button)actionEvent.getSource();
            if (pressedButton.getText().isEmpty()) {
                pressedButton.setText(currentMove.symbol);
                updateWinFactor(pressedButton);
                currentMove.updateIsWinner();
                if (currentMove.isWinner) {
                    headline.setText(currentMove.headlineWhenGameOver);
                    gameOver = true;
                } else {
                    if ((crossesMove.winFactor | noughtsMove.winFactor) == 511) {
                        headline.setText("Draw");
                        gameOver = true;
                    }
                }
                switchTurn();
            }
        }
    }

    /**A class describing move*/
    private class Move {
        private Move() {
            winFactor = 0;
            symbol = "";
            isWinner = false;
            headlineWhileMove = "";
            headlineWhenGameOver = "";
        }

        /**
         * A field describing basic cases of win
         *
         * Playing field is represented by binary number
         * If result of bitwise AND of current field named winFactor and the number from this list
         * will not change the number, then player is winner
         * */
        private final List<Integer> winFactors = Arrays.asList(7, 56, 73, 84, 146, 273, 292, 448);
        public int winFactor;
        public String symbol;
        public boolean isWinner;

        public String headlineWhileMove;
        public String headlineWhenGameOver;

        /**A method checks if player is winner*/
        void updateIsWinner() {
            isWinner = winFactors.stream().anyMatch(n -> (n & winFactor) == n);
        }
    }

    /**A class describing move of crosses*/
    private class CrossesMove extends Move {
        private CrossesMove() {
            super();
            symbol = "X";
            headlineWhileMove = "Crosses' move";
            headlineWhenGameOver = "Crosses win!";
        }
    }

    /**A class describing move of noughts*/
    private class NoughtsMove extends Move {
        private NoughtsMove() {
            super();
            symbol = "O";
            headlineWhileMove = "Noughts' move";
            headlineWhenGameOver = "Noughts win!";
        }
    }
}
